package ClientServer;

import java.util.*;

public class Server {

    private List<ClientHandler> playersList;
    ServerMain server;
    public String nazwa;
    public String started;

    public Server(ServerMain s, String n) {
        playersList = new ArrayList<ClientHandler>();
        this.nazwa = n;
        this.server = s;
        this.started = "no";
    }

    public boolean addPlayer(ClientHandler c) {
        if (playersList.size() < 4) {
            playersList.add(c);
            c.setLobby(this);
            server.reloadLobbies();
            return true;
        }
        return false;
    }

    public void sendPlayerNick(int x) {
        if (x == 0 || x == 1 || x == 2 || x == 3) {
            String msg = playersList.get(x).getNickname();
            for (int i = 0; i < playersList.size(); i++) {
                if (x != i) {
                    ClientHandler connection = playersList.get(i);
                    connection.send("NICK;" + msg + ";" + x);
                }
            }
        }
    }

    public void sendToPlayer(String msg, String nickName) {
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getNickname() == nickName) {
                ClientHandler connection = playersList.get(i);
                connection.send(msg + ";" + i);
            }
        }
    }

    public List<ClientHandler> getPlayersList() {
        return playersList;
    }

    public void sendToPlayer2(String msg, String nickName) {
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getNickname().equals(nickName)) {
                ClientHandler connection = playersList.get(i);
                connection.send(msg + ";" + nickName);
            }
        }
    }

    public void sendToAllPlayers(String msg) {
        for (ClientHandler connection : playersList) {
            connection.send(msg);
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public synchronized void resetLobby() {
        if (playersList.isEmpty()) return;

        sendToAllPlayers("ENDLOBBY");
        for (ClientHandler x : playersList) {
            x.leaveLobby();
        }
        playersList.clear();
        started = "no";
    }

    public synchronized void setStartedYes() {
        if (!started.equals("yes")) {
            started = "yes";
            server.reloadLobbies();
        }
    }

    public void disconnected(ClientHandler dc) {
        resetLobby();
        server.reloadLobbies();
    }

    public String lobbyInfo() {
        String rtrn = new String();
        for (ClientHandler x : playersList) {
            rtrn += x.getNickname() + ";";
        }
        for (int i = 0; i + playersList.size() < 4; i++) {
            rtrn += " ;";
        }
        return nazwa + ";" + rtrn + started;
    }
}
