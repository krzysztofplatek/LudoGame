package Game.Board;

import Game.Board.Player;
import Game.Enum.PlayerColor;
import Game.Board.Board;

import javax.swing.*;
import java.io.PrintWriter;

public class Game extends JFrame {
    private int tableNumber;
    private Board board;
    private PrintWriter out;
    private Player redPlayer;
    private Player greenPlayer;
    private Player yellowPlayer;
    private Player bluePlayer;

    public Player getBluePlayer() {
        return bluePlayer;
    }

    public Player getGreenPlayer() {
        return greenPlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getYellowPlayer() {
        return yellowPlayer;
    }

    public Board getBoard() {
        return board;
    }

    private void send(String msg) {
        out.println(msg);
    }

    public void checkReady() {
        if (greenPlayer != null && yellowPlayer != null && bluePlayer != null) {
            if (greenPlayer.isReady() && yellowPlayer.isReady() && bluePlayer.isReady() && redPlayer.isReady())
                if (board.getColor() == PlayerColor.RED) {
                    send("START;" + redPlayer.getNickName());
                }
        } else {
            if (greenPlayer != null && yellowPlayer != null) {
                if (greenPlayer.isReady() && yellowPlayer.isReady() && redPlayer.isReady())
                    if (board.getColor() == PlayerColor.RED) {
                        send("START;" + redPlayer.getNickName());
                    }
            } else {
                if (greenPlayer != null) {
                    if (greenPlayer.isReady() && redPlayer.isReady())
                        if (board.getColor() == PlayerColor.RED) {
                            send("START;" + redPlayer.getNickName());
                        }
                } else {
                    if (redPlayer.isReady())
                        if (board.getColor() == PlayerColor.RED) {
                            send("START;" + redPlayer.getNickName());
                        }
                }
            }
        }
    }

    public void setReady(String color) {
        if (redPlayer != null && color.equals("RED")) redPlayer.setReady();
        if (yellowPlayer != null && color.equals("YELLOW")) yellowPlayer.setReady();
        if (greenPlayer != null && color.equals("GREEN")) greenPlayer.setReady();
        if (bluePlayer != null && color.equals("BLUE")) bluePlayer.setReady();
    }

    public Game(PrintWriter out) {
        this.out = out;
        board = new Board(out);
        board.setVisible(true);
    }

    public void setWindowTitle(String txt) {
        board.setTitle(txt);
    }

    public void setPlayerColor(PlayerColor playerColor) {
        if (board.getColor() == null) {
            board.setColor(playerColor);
            if (playerColor == PlayerColor.RED) board.setNickName(redPlayer.getNickName());
            if (playerColor == PlayerColor.GREEN) board.setNickName(greenPlayer.getNickName());
            if (playerColor == PlayerColor.YELLOW) board.setNickName(yellowPlayer.getNickName());
            if (playerColor == PlayerColor.BLUE) board.setNickName(bluePlayer.getNickName());
        }
    }

    public void updateALLPlayersInfoBoard() {
        if (redPlayer != null) {
            if (!redPlayer.isTxtOnTheBoard()) {
                board.updatePlayerInfo(redPlayer.getNickName(), redPlayer.getPlayerColor());
                redPlayer.changeTxt();
            }
        }
        if (greenPlayer != null) {
            if (!greenPlayer.isTxtOnTheBoard()) {
                board.updatePlayerInfo(greenPlayer.getNickName(), greenPlayer.getPlayerColor());
                greenPlayer.changeTxt();
            }
        }
        if (yellowPlayer != null) {
            if (!yellowPlayer.isTxtOnTheBoard()) {
                board.updatePlayerInfo(yellowPlayer.getNickName(), yellowPlayer.getPlayerColor());
                yellowPlayer.changeTxt();
            }
        }
        if (bluePlayer != null) {
            if (!bluePlayer.isTxtOnTheBoard()) {
                board.updatePlayerInfo(bluePlayer.getNickName(), bluePlayer.getPlayerColor());
                bluePlayer.changeTxt();
            }
        }
    }

    public void addPlayer(String nickName, String idx) {
        if (idx.equals("0")) if (redPlayer == null) {
            addPlayer(nickName, PlayerColor.RED);
            setPlayerColor(PlayerColor.RED);
        }
        ;
        if (idx.equals("1")) if (greenPlayer == null) {
            addPlayer(nickName, PlayerColor.GREEN);
            setPlayerColor(PlayerColor.GREEN);
        }
        if (idx.equals("2")) if (yellowPlayer == null) {
            addPlayer(nickName, PlayerColor.YELLOW);
            setPlayerColor(PlayerColor.YELLOW);
        }
        if (idx.equals("3")) if (bluePlayer == null) {
            addPlayer(nickName, PlayerColor.BLUE);
            setPlayerColor(PlayerColor.BLUE);
        }
    }

    public void addPlayer(String nickName, PlayerColor color) {
        if (color == PlayerColor.BLUE) bluePlayer = new Player(nickName, color);
        if (color == PlayerColor.RED) redPlayer = new Player(nickName, color);
        if (color == PlayerColor.GREEN) greenPlayer = new Player(nickName, color);
        if (color == PlayerColor.YELLOW) yellowPlayer = new Player(nickName, color);
    }

    public void close() {
        board.setVisible(false);
    }

}
