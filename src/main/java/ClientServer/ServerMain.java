package ClientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    private List<ClientHandler> playersList;
    private List<Server> lobbyList;
    ServerSocket server;
    public ServerMain() {
        playersList=new ArrayList<ClientHandler>();
        lobbyList=new ArrayList<Server>();
        for(int i=1;i<5;i++)
            lobbyList.add(new Server(this,"LOBBY "+i));
        try {
            server=new ServerSocket(8831);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerMain s=new ServerMain();
        try {
            s.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean joinLobby(ClientHandler c,String nazwa){
        for(Server lobby : lobbyList){
           if(lobby.getNazwa().equals(nazwa)){
               if(lobby.addPlayer(c)) return true;
           }
        }
        return false;
    }
    public void start() throws IOException{
        while(true) {

            System.out.println("Czekam na polaczenie...");
            Socket client = server.accept();
            System.out.println("Polaczono z " + client);

            ClientHandler connected = new ClientHandler(this, client);

            playersList.add(connected);
            connected.start();
        }
    }
    public void infoRequest(ClientHandler c){
        c.send("STARTLOBBYINFO");
        for(Server lobby : lobbyList){
            c.send("LOBBYINFO;"+lobby.lobbyInfo());
        }
        c.send("ENDLOBBYINFO");
    }
    public void reloadLobbies(){
        for(ClientHandler c : playersList) {
            c.send("STARTLOBBYINFO");
            for (Server lobby : lobbyList) {
                c.send("LOBBYINFO;" + lobby.lobbyInfo());
            }
            c.send("RELOADLOBBY");
        }
    }
}
