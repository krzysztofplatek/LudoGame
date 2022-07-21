package ClientServer;

public class LobbyInfoClient {
    String name;
    String red;
    String green;
    String yellow;
    String blue;
    String inGame;
    public LobbyInfoClient(String n,String r,String g,String y,String b,String inG){
        this.name=n;
        this.red=r;
        this.green=g;
        this.yellow=y;
        this.blue=b;
        this.inGame=inG;
    }

    public String getInGame() {
        return inGame;
    }

    public String getBlue() {
        return blue;
    }

    public String getGreen() {
        return green;
    }

    public String getName() {
        return name;
    }

    public String getRed() {
        return red;
    }

    public String getYellow() {
        return yellow;
    }

}
