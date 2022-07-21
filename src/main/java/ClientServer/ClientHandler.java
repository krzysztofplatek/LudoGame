package ClientServer;

import MySql.Score;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private final ServerMain serverMain;
    private Server server;
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String nickname;
    private boolean inLobby;

    public String getNickname() {
        return nickname;
    }

    public void send(String msg) {
        out.println(msg);
    }

    public ClientHandler(ServerMain host, Socket socke) {
        server = null;
        this.inLobby = false;
        this.serverMain = host;
        this.socket = socke;

        try {
            OutputStream oStream = socket.getOutputStream();
            out = new PrintWriter(oStream, true);

            InputStream iStream = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(iStream));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            obslugaKlienta();
        } catch (IOException e) {
            e.printStackTrace();
            server.disconnected(this);
        }
    }

    public void setLobby(Server l) {
        this.server = l;
        this.inLobby = true;
    }

    private void obslugaKlienta() throws IOException {
        System.out.println("czekam na wiadomosci od gracza");
        String msg;
        while (true) {
            while ((msg = in.readLine()) != null) {
                if (!inLobby) {
                    if (msg.startsWith("JOINLOBBY")) {
                        String[] tokens = msg.split(";");
                        String nazwa = tokens[1];
                        if (serverMain.joinLobby(this, nazwa)) {
                            System.out.println(nickname + "dolacza do serwera");
                            send("LOBBYMENU");
                        } else {
                            System.out.println(nickname + "nie udalo sie dolaczyc do " + nazwa);
                        }
                        continue;
                    }
                }
                if (msg.startsWith("SETNAME")) {
                    String[] tokens = msg.split(";");
                    nickname = tokens[1];
                    System.out.println("ustawiono nick gracza na : " + nickname);
                    continue;
                }
                if (msg.equals("LOBBYINFO")) {
                    serverMain.infoRequest(this);
                }
                if (inLobby) {
                    if (msg.startsWith("FINISHGAME")) {
                        Score score = null;
                        List<ClientHandler> playersList = server.getPlayersList();
                        String[] tokens = msg.split(";");
                        switch (server.getPlayersList().size()) {
                            case 1: {
                                score = new Score(playersList.get(0).getNickname(), null, null, null, Integer.parseInt(tokens[1]), 0, 0, 0);
                                break;
                            }
                            case 2: {
                                score = new Score(playersList.get(0).getNickname(), playersList.get(1).getNickname(), null, null, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 0, 0);
                                break;
                            }
                            case 3: {
                                score = new Score(playersList.get(0).getNickname(), playersList.get(1).getNickname(), playersList.get(2).getNickname(), null, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), 0);
                                break;
                            }
                            case 4: {
                                score = new Score(playersList.get(0).getNickname(), playersList.get(1).getNickname(), playersList.get(2).getNickname(), playersList.get(3).getNickname(), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                                break;
                            }
                        }
                        score.addScore();
                        server.resetLobby();
                    }
                    if (msg.startsWith("READY")) {
                        String[] tokens = msg.split(";");
                        String color = tokens[1];
                        server.sendToAllPlayers("READY;" + color);
                        continue;
                    }
                    if (msg.startsWith("START")) {
                        String[] tokens = msg.split(";");
                        String nickname = tokens[1];
                        server.sendToPlayer2("TOUR", nickname);
                        startGame();
                        continue;
                    }
                    if (msg.startsWith("NEXTTURN")) {
                        String[] tokens = msg.split(";");
                        String nickname = tokens[1];
                        server.sendToPlayer2("NEXTTOUR", nickname);
                        continue;
                    }
                    if (msg.startsWith("NEWPLAYER")) {
                        String[] tokens = msg.split(";");
                        nickname = tokens[1];
                        server.sendToPlayer("NEWPLAYER", nickname);
                        continue;
                    }
                    if (msg.startsWith("GREENJOIN")) {
                        server.sendPlayerNick(0);
                        server.sendPlayerNick(1);
                        continue;
                    }
                    if (msg.startsWith("YELLOWJOIN")) {

                        server.sendPlayerNick(0);
                        server.sendPlayerNick(1);
                        server.sendPlayerNick(2);
                        continue;
                    }
                    if (msg.startsWith("BLUEJOIN")) {
                        server.sendPlayerNick(0);
                        server.sendPlayerNick(1);
                        server.sendPlayerNick(2);
                        server.sendPlayerNick(3);
                        continue;
                    }
                    if (msg.startsWith("PROMOTION")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        server.sendToAllPlayers("PROMOTION;" + idx);
                    }
                    if (msg.startsWith("MOVE")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        String rollNumber = tokens[2];
                        server.sendToAllPlayers("MOVE;" + idx + ";" + rollNumber);
                    }
                    if (msg.startsWith("REDUPGRADE")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        server.sendToAllPlayers("REDUPGRADE;" + idx);
                    }
                    if (msg.startsWith("GREENUPGRADE")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        server.sendToAllPlayers("GREENUPGRADE;" + idx);
                    }
                    if (msg.startsWith("YELLOWUPGRADE")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        server.sendToAllPlayers("YELLOWUPGRADE;" + idx);
                    }
                    if (msg.startsWith("BLUEUPGRADE")) {
                        String[] tokens = msg.split(";");
                        String idx = tokens[1];
                        server.sendToAllPlayers("BLUEUPGRADE;" + idx);
                    }
                }

            }
        }
    }

    public synchronized void startGame() {
        this.server.setStartedYes();
    }

    public void leaveLobby() {
        this.server = null;
        this.inLobby = false;
    }
}
