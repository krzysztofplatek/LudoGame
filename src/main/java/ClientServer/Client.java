package ClientServer;

import Game.Board.Game;
import Game.Window.ClientWindow;
import Game.Window.MenuWindow;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    MenuWindow menuWindow;
    private Game game;
    private final String serverName;
    private final int serverPort;
    Socket socket;
    private PrintWriter out;
    private BufferedReader input;
    ClientWindow cWindow;
    private List<LobbyInfoClient> info;

    public Client(String name, int port) {
        this.serverName = name;
        this.serverPort = port;
    }

    public static void main(String[] args) {
        try {
            Client c = new Client("localhost", 8831);
            c.connect();
            c.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        this.socket = new Socket(serverName, serverPort);

        OutputStream oStream = socket.getOutputStream();
        InputStream iStream = socket.getInputStream();

        this.out = new PrintWriter(oStream, true);
        this.input = new BufferedReader(new InputStreamReader(iStream));

        Thread watekOdczytujacy = new Thread() {
            @Override
            public void run() {
                MessageListener();
            }
        };
        watekOdczytujacy.start();
    }

    private void MessageListener() {
        String msg;
        while (true) {
            try {
                while ((msg = input.readLine()) != null) {
                    if (msg.equals("ENDLOBBY")) {
                        game.close();
                        openCWindow();
                    }
                    if (msg.equals("LOBBYMENU")) {
                        cWindow.setVisible(false);
                        game = new Game(out);
                        send("NEWPLAYER;" + menuWindow.getNickname());
                    }
                    if (msg.equals("STARTLOBBYINFO")) {
                        info = new ArrayList<LobbyInfoClient>();
                    }
                    if (msg.equals("RELOADLOBBY")) {
                        cWindow.reloadTables(info);
                    }
                    if (msg.startsWith("LOBBYINFO")) {
                        String[] tokens = msg.split(";");
                        info.add(new LobbyInfoClient(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]));
                    }
                    if (msg.equals("ENDLOBBYINFO")) {  //otwiera okno ClientWindow
                        cWindow = new ClientWindow(out, info, menuWindow.getNickname());
                        cWindow.setVisible(true);
                    }

                    if (msg.startsWith("NEWPLAYER")) {
                        String[] tokens = msg.split(";");
                        String nr = tokens[1];
                        game.addPlayer(menuWindow.getNickname(), nr);
                        game.updateALLPlayersInfoBoard();
                        game.setWindowTitle(menuWindow.getNickname());
                        if (nr.equals("1")) send("GREENJOIN");
                        if (nr.equals("2")) send("YELLOWJOIN");
                        if (nr.equals("3")) send("BLUEJOIN");
                        System.out.println(game.getBoard().getColor() + " dolaczyl do gry");
                        continue;
                    }
                    if (msg.startsWith("NICK")) {
                        String[] tokens = msg.split(";");
                        String nickname = tokens[1];
                        String nr = tokens[2];
                        game.addPlayer(nickname, nr);
                        game.updateALLPlayersInfoBoard();
                        continue;
                    }
                    if (msg.startsWith("READY")) {
                        String[] tokens = msg.split(";");
                        String color = tokens[1];
                        game.setReady(color);
                        game.checkReady();
                        continue;
                    }
                    if (msg.startsWith("TOUR")) {
                        game.getBoard().changeTurn();
                        game.getBoard().changeMark();
                        continue;
                    }
                    if (msg.startsWith("PROMOTION")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        game.getBoard().promotion(idx);
                        continue;
                    }
                    if (msg.startsWith("MOVE")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        int rollNumber = Integer.parseInt(tokens[2]);
                        game.getBoard().movePown(idx, rollNumber);
                        continue;
                    }
                    if (msg.startsWith("REDUPGRADE")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        game.getBoard().upgradeRedTxt(idx);
                        continue;
                    }
                    if (msg.startsWith("GREENUPGRADE")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        game.getBoard().upgradeGreenTxt(idx);
                        continue;
                    }
                    if (msg.startsWith("YELLOWUPGRADE")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        game.getBoard().upgradeYellowTxt(idx);
                        continue;
                    }
                    if (msg.startsWith("BLUEUPGRADE")) {
                        String[] tokens = msg.split(";");
                        int idx = Integer.parseInt(tokens[1]);
                        game.getBoard().upgradeBlueTxt(idx);
                        continue;
                    }
                    if (msg.startsWith("NEXTTOUR")) {
                        String[] tokens = msg.split(";");
                        String nickname = tokens[1];
                        if (nickname.equals(game.getRedPlayer().getNickName())) {
                            if (game.getGreenPlayer() != null) {
                                game.getBoard().changeTurn();
                                send("START;" + game.getGreenPlayer().getNickName());
                            } else {
                                game.getBoard().changeTurn();
                                send("START;" + game.getRedPlayer().getNickName());
                            }
                        } else {
                            if (nickname.equals(game.getGreenPlayer().getNickName())) {
                                if (game.getYellowPlayer() != null) {
                                    game.getBoard().changeTurn();
                                    send("START;" + game.getYellowPlayer().getNickName());
                                } else {
                                    game.getBoard().changeTurn();
                                    send("START;" + game.getRedPlayer().getNickName());
                                }
                            } else {
                                if (nickname.equals(game.getYellowPlayer().getNickName())) {
                                    if (game.getBluePlayer() != null) {
                                        game.getBoard().changeTurn();
                                        send("START;" + game.getBluePlayer().getNickName());
                                    } else {
                                        game.getBoard().changeTurn();
                                        send("START;" + game.getRedPlayer().getNickName());
                                    }
                                } else {
                                    game.getBoard().changeTurn();
                                    send("START;" + game.getRedPlayer().getNickName());
                                }
                            }
                        }
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String msg) {
        out.println(msg);
    }

    private void openCWindow() {
        send("LOBBYINFO");
    }

    private void startGame() {
        menuWindow = new MenuWindow(out);
        menuWindow.setVisible(true);
        while (true) {
            if (!menuWindow.isVisible()) {
                break;
            }
            System.out.println("");
        }
        openCWindow();
    }
}




