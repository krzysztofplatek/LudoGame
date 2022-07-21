package Game.Board;

import Game.Enum.PlayerColor;

public class Player {
    private boolean txtInfo;
    private String nickName;
    private PlayerColor playerColor;
    private Boolean isReady;

    public Boolean isReady() {
        return isReady;
    }

    public void setReady() {
        isReady = true;
    }

    public void changeTxt() {
        txtInfo = true;
    }

    public boolean isTxtOnTheBoard() {
        return txtInfo;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public String getNickName() {
        return nickName;
    }

    public Player(String nickName, PlayerColor playerColor) {
        this.isReady = false;
        this.txtInfo = false;
        this.nickName = nickName;
        this.playerColor = playerColor;
    }
}
