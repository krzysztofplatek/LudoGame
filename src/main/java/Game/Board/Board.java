package Game.Board;

import Game.Enum.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.util.Random;

public class Board extends JFrame {
    private int rollNumberint = 0;
    private Field field[];
    private String nickName;
    private PlayerColor playerColor;
    private PrintWriter out;
    private boolean myTurn = false;
    int x = 46;
    private JPanel panel = new JPanel();
    private JButton rollDiceButton, confirmReadyGameButton;
    private JButton roll1, roll2, roll3, roll4, roll5, roll6;
    private int redScore, blueScore, yellowScore, greenScore;
    private JLabel txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, rollNumber, mark;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PlayerColor getColor() {
        return playerColor;
    }

    public Boolean isMyTurn() {
        return myTurn;
    }

    private void send(String msg) {
        out.println(msg);
    }

    public void changeTurn() {
        if (myTurn) {
            System.out.println(playerColor + " koniec tury");
            myTurn = false;
        } else {
            System.out.println(playerColor + " poczatek tury");
            myTurn = true;
        }
    }

    public void createPlayerInfo() {

        mark = new JLabel("Your Turn");
        mark.setBounds(780, 20, 100, 30);
        mark.setFont(new Font(null, Font.BOLD, 20));
        mark.setVisible(false);

        txt1 = new JLabel("RED         :");
        txt1.setBounds(740, 25, 200, 100);
        txt1.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt1);

        txt2 = new JLabel("GREEN    :");
        txt2.setBounds(740, 75, 200, 100);
        txt2.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt2);
        txt3 = new JLabel("YELLOW :");
        txt3.setBounds(740, 125, 200, 100);
        txt3.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt3);

        txt4 = new JLabel("BLUE       :");
        txt4.setBounds(740, 175, 200, 100);
        txt4.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt4);
        panel.add(mark);
    }

    public void changeMark() {
        if (mark.isVisible())
            mark.setVisible(false);
        else
            mark.setVisible(true);
    }

    public void updatePlayerInfo(String nickname, PlayerColor color) {
        if (color == PlayerColor.RED) {
            String txt = txt1.getText();
            txt1.setText(txt + " " + nickname);
        }
        if (color == PlayerColor.GREEN) {
            String txt = txt2.getText();
            txt2.setText(txt + " " + nickname);
        }
        if (color == PlayerColor.YELLOW) {
            String txt = txt3.getText();
            txt3.setText(txt + " " + nickname);
        }
        if (color == PlayerColor.BLUE) {
            String txt = txt4.getText();
            txt4.setText(txt + " " + nickname);
        }
    }

    public void createRollDiceButton() {
        rollNumber = new JLabel("X");
        rollNumber.setBounds(890, 300, 100, 100);
        rollNumber.setBackground(Color.RED);
        rollNumber.setFont(new Font(null, Font.BOLD, 20));
        panel.add(rollNumber);

        rollDiceButton = new JButton("Roll");
        rollDiceButton.setBounds(745, 300, 100, 100);
        rollDiceButton.addActionListener(this::actionListener);
        panel.add(rollDiceButton);
    }

    public void createTestRollButtons() {
        roll1 = new JButton("Roll 1");
        roll1.setBounds(20, 720, 100, 100);
        panel.add(roll1);
        roll1.addActionListener(this::actionListener);

        roll2 = new JButton("Roll 2");
        roll2.setBounds(140, 720, 100, 100);
        panel.add(roll2);
        roll2.addActionListener(this::actionListener);

        roll3 = new JButton("Roll 3");
        roll3.setBounds(260, 720, 100, 100);
        panel.add(roll3);
        roll3.addActionListener(this::actionListener);

        roll4 = new JButton("Roll 4");
        roll4.setBounds(380, 720, 100, 100);
        panel.add(roll4);
        roll4.addActionListener(this::actionListener);

        roll5 = new JButton("Roll 5");
        roll5.setBounds(500, 720, 100, 100);
        panel.add(roll5);
        roll5.addActionListener(this::actionListener);

        roll6 = new JButton("Roll 6");
        roll6.setBounds(620, 720, 100, 100);
        panel.add(roll6);
        roll6.addActionListener(this::actionListener);
    }

    public void createScoreCounter() {
        txt5 = new JLabel("0");
        txt5.setBounds(350, 370, 50, 50);
        txt5.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt5);

        txt6 = new JLabel("0");
        txt6.setBounds(305, 328, 50, 50);
        txt6.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt6);

        txt7 = new JLabel("0");
        txt7.setBounds(350, 285, 50, 50);
        txt7.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt7);

        txt8 = new JLabel("0");
        txt8.setBounds(395, 328, 50, 50);
        txt8.setFont(new Font(null, Font.BOLD, 17));
        panel.add(txt8);
    }

    public void updateTxt(JLabel txt, int x) {
        txt.setText("" + x);
    }

    public void setRollNumber(String num) {
        rollNumber.setText(num);
    }

    public void createConfirmReadyGameButton() {
        confirmReadyGameButton = new JButton("Ready");
        confirmReadyGameButton.setBounds(745, 450, 200, 200);
        confirmReadyGameButton.setFont(new Font(null, Font.BOLD, 20));
        confirmReadyGameButton.addActionListener(this::actionListener);
        panel.add(confirmReadyGameButton);
    }

    public void pownToHome(int idx) {
        Field tmp = field[idx];
        if (tmp.getPown() == null) return;
        if (tmp.getPown().getColor() == PlayerColor.RED) {
            if (field[72].getPown() == null) {
                field[72].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[73].getPown() == null) {
                field[73].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[74].getPown() == null) {
                field[74].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[75].getPown() == null) {
                field[75].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
        }
        if (tmp.getPown().getColor() == PlayerColor.GREEN) {
            if (field[76].getPown() == null) {
                field[76].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[77].getPown() == null) {
                field[77].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[78].getPown() == null) {
                field[78].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[79].getPown() == null) {
                field[79].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
        }
        if (tmp.getPown().getColor() == PlayerColor.YELLOW) {
            if (field[80].getPown() == null) {
                field[80].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[81].getPown() == null) {
                field[81].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[82].getPown() == null) {
                field[82].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[83].getPown() == null) {
                field[83].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
        }
        if (tmp.getPown().getColor() == PlayerColor.BLUE) {
            if (field[84].getPown() == null) {
                field[84].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[85].getPown() == null) {
                field[85].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[86].getPown() == null) {
                field[86].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
            if (field[87].getPown() == null) {
                field[87].setPown(tmp.getPown());
                tmp.setPown(null);
                return;
            }
        }
    }

    public void promotion(int idx) {
        Field tmp = field[idx];
        if (tmp.getPown().getColor() == PlayerColor.RED) {
            pownToHome(0);
            field[0].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (tmp.getPown().getColor() == PlayerColor.GREEN) {
            pownToHome(13);
            field[13].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (tmp.getPown().getColor() == PlayerColor.YELLOW) {
            pownToHome(26);
            field[26].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (tmp.getPown().getColor() == PlayerColor.BLUE) {
            pownToHome(39);
            field[39].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
    }

    public boolean checkPosibleMove() {
        for (int i = 0; i < 88; i++) {
            if (checkMove(field[i].getIndex()) != 0)
                return true;
        }
        return false;
    }

    public void movePown(int idx, int rollNumber) {
        Field tmp = field[idx];
        PlayerColor pcolor = tmp.getPown().getColor();
        int num = rollNumberint;
        if (num == 0)
            num = rollNumber;
        int x = idx + num;
        if (pcolor == PlayerColor.RED) {
            if (x > 50 && idx < 51) x++;
            pownToHome(x);
            field[x].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (pcolor == PlayerColor.GREEN) {
            if (x > 51 && idx < 57) x = x - 52;
            if (x > 11 && idx < 12) x += 45;
            pownToHome(x);
            field[x].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (pcolor == PlayerColor.YELLOW) {
            if (x > 51 && idx < 62) x = x - 52;
            if (x > 24 && idx < 25) x += 37;
            pownToHome(x);
            field[x].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        if (pcolor == PlayerColor.BLUE) {
            if (x > 51 && idx < 67) x = x - 52;
            if (x > 37 && idx < 38) x += 29;
            pownToHome(x);
            field[x].setPown(tmp.getPown());
            tmp.setPown(null);
            return;
        }
        return;
    }

    public int checkLegalMove(int idx) {
        int num = rollNumberint;
        int x = idx + num;
        if (playerColor == PlayerColor.RED) {
            if (x > 50 && idx < 51) x++;
            if (x == 57) return 3;
            if (x > 57) return 0;
            if (field[x].getPown() == null) {
                return 1;
            } else {
                if (field[x].getPown().getColor() != playerColor)
                    return 1;
            }
        }
        if (playerColor == PlayerColor.GREEN) {
            if (x > 51 && idx < 57) x = x - 52;
            if (x > 11 && idx < 12) x += 45;
            if (x == 62) return 4;
            if (x > 62) return 0;
            if (field[x].getPown() == null) {
                return 1;
            } else {
                if (field[x].getPown().getColor() != playerColor)
                    return 1;
            }
        }
        if (playerColor == PlayerColor.YELLOW) {
            if (x > 51 && idx < 62) x = x - 52;
            if (x > 24 && idx < 25) x += 37;
            if (x == 67) return 5;
            if (x > 67) return 0;
            if (field[x].getPown() == null) {
                return 1;
            } else {
                if (field[x].getPown().getColor() != playerColor)
                    return 1;
            }
        }
        if (playerColor == PlayerColor.BLUE) {
            if (x > 51 && idx < 67) x = x - 52;
            if (x > 37 && idx < 38) x += 29;
            if (x == 72) return 6;
            if (x > 72) return 0;
            if (field[x].getPown() == null) {
                return 1;
            } else {
                if (field[x].getPown().getColor() != playerColor)
                    return 1;
            }
        }
        return 0;
    }

    public int checkMove(int idx) {
        Field tmp = field[idx];
        if (tmp.getPown() == null) return 0;
        if (tmp.getPown().getColor() == playerColor) {
            if (rollNumberint == 1 || rollNumberint == 6) {
                if (playerColor == PlayerColor.RED && (idx == 72 || idx == 73 || idx == 74 || idx == 75)) {
                    if (field[0].getPown() == null) {
                        return 1;
                    } else {
                        if (field[0].getPown().getColor() != playerColor)
                            return 1;
                    }
                }
                if (playerColor == PlayerColor.GREEN && (idx == 76 || idx == 77 || idx == 78 || idx == 79)) {
                    if (field[13].getPown() == null) {
                        return 1;
                    } else {
                        if (field[13].getPown().getColor() != playerColor)
                            return 1;
                    }
                }
                if (playerColor == PlayerColor.YELLOW && (idx == 80 || idx == 81 || idx == 82 || idx == 83)) {
                    if (field[26].getPown() == null) {
                        return 1;
                    } else {
                        if (field[26].getPown().getColor() != playerColor)
                            return 1;
                    }
                }
                if (playerColor == PlayerColor.BLUE && (idx == 84 || idx == 85 || idx == 86 || idx == 87)) {
                    if (field[39].getPown() == null) {
                        return 1;
                    } else {
                        if (field[39].getPown().getColor() != playerColor)
                            return 1;
                    }
                }
            }
            if (0 <= idx && idx <= 71) {
                switch (checkLegalMove(idx)) {
                    case 1:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    case 5:
                        return 5;
                    case 6:
                        return 6;
                    default:
                        return 0;
                }
            }
        }
        return 0;
    }

    public void nextTurn() {
        rollNumberint = 0;
        changeMark();
        send("NEXTTURN;" + nickName);
    }

    public void upgradeRedTxt(int idx) {
        updateTxt(txt5, ++redScore);
        System.out.println("dodatkowy punkt ! " + redScore);
        field[idx].deletePown();
        if (redScore == 4) {
            System.out.println("zwyciestwo czerwonych!");
            if (playerColor == PlayerColor.RED)
                finishGame();
        }
    }

    public void upgradeGreenTxt(int idx) {
        updateTxt(txt6, ++greenScore);
        System.out.println("dodatkowy punkt !");
        field[idx].deletePown();
        if (greenScore == 4) {
            System.out.println("zwyciestwo zielonych!");
            if (playerColor == PlayerColor.GREEN)
                finishGame();
        }
    }

    public void upgradeYellowTxt(int idx) {
        updateTxt(txt7, ++yellowScore);
        System.out.println("dodatkowy punkt !");
        field[idx].deletePown();
        if (yellowScore == 4) {
            System.out.println("zwyciestwo zoltych!");
            if (playerColor == PlayerColor.YELLOW)
                finishGame();
        }
    }

    public void upgradeBlueTxt(int idx) {
        updateTxt(txt8, ++blueScore);
        System.out.println("dodatkowy punkt !");
        field[idx].deletePown();
        if (blueScore == 4) {
            System.out.println("zwyciestwo niebieskich!");
            if (playerColor == PlayerColor.BLUE)
                finishGame();
        }
    }

    public void finishGame() {
        System.out.println(redScore + " " + greenScore + " " + yellowScore + " " + blueScore);
        send("FINISHGAME;" + redScore + ";" + greenScore + ";" + yellowScore + ";" + blueScore);
    }

    public void buttonListener(ActionEvent e) {
        Object z = e.getSource();
        if (rollNumberint != 0) {
            for (int i = 0; i < 88; i++) {
                if (z == field[i].getButton()) {

                    int idx = field[i].getIndex();
                    int x = checkMove(idx);
                    switch (x) {
                        case 1: {
                            send("PROMOTION;" + idx);
                            nextTurn();
                            break;
                        }
                        case 2: {
                            send("MOVE;" + idx + ";" + rollNumberint);
                            nextTurn();
                            break;
                        }
                        case 3: {
                            send("REDUPGRADE;" + idx);
                            nextTurn();
                            break;
                        }
                        case 4: {
                            send("GREENUPGRADE;" + idx);
                            nextTurn();
                            break;
                        }
                        case 5: {
                            send("YELLOWUPGRADE;" + idx);
                            nextTurn();
                            break;
                        }
                        case 6: {
                            send("BLUEUPGRADE;" + idx);
                            nextTurn();
                            break;
                        }

                    }

                }
            }
        }
    }

    public void actionListener(ActionEvent e) {
        Object z = e.getSource();
        if (z == confirmReadyGameButton) {
            System.out.println(playerColor + " potwierdzil gotowosc");
            send("READY;" + playerColor);
            confirmReadyGameButton.setVisible(false);
            this.setVisible(false);
            this.setVisible(true);
        }
        if (isMyTurn()) {
            if (z == rollDiceButton) {
                if (rollNumberint == 0) {
                    Random rand = new Random();
                    int x = rand.nextInt() % 6;
                    if (x < 0) x = -x;
                    x++;
                    setRollNumber("" + x);
                    System.out.println(playerColor + " wylosowal " + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
            }
            if (rollNumberint == 0) {
                if (z == roll1) {
                    int x = 1;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
                if (z == roll2) {
                    int x = 2;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
                if (z == roll3) {
                    int x = 3;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
                if (z == roll4) {
                    int x = 4;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
                if (z == roll5) {
                    int x = 5;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
                if (z == roll6) {
                    int x = 6;
                    setRollNumber("" + x);
                    rollNumberint = x;
                    if (!checkPosibleMove()) {
                        nextTurn();
                    }
                }
            }
        }
    }

    public void createFields() {
        int y = 50;
        int sx = 284;
        int sy = 615;
        field = new Field[88];
        for (int i = 0; i < 72; i++) {
            if (0 <= i && i < 5) field[i] = new Field(sx, sy - i * 47, x, i, panel, this::buttonListener);
            if (5 <= i && i < 10)
                field[i] = new Field(sx - (i - 4) * 47, sy - 5 * 47, x, i, panel, this::buttonListener);
            if (10 <= i && i < 13)
                field[i] = new Field(sx - 6 * 47, sy - (i - 5) * 47, x, i, panel, this::buttonListener);
            if (13 <= i && i < 18)
                field[i] = new Field(sx - (18 - i) * 47, sy - 7 * 47, x, i, panel, this::buttonListener);
            if (18 <= i && i < 23) field[i] = new Field(sx, sy - (i - 10) * 47, x, i, panel, this::buttonListener);
            if (23 <= i && i < 26)
                field[i] = new Field(sx + (i - 23) * 47, sy - 13 * 47, x, i, panel, this::buttonListener);
            if (26 <= i && i < 31)
                field[i] = new Field(sx + 2 * 47, sy + (i - 38) * 47, x, i, panel, this::buttonListener);
            if (31 <= i && i < 36)
                field[i] = new Field(sx + (i - 28) * 47, sy - 7 * 47, x, i, panel, this::buttonListener);
            if (36 <= i && i < 39)
                field[i] = new Field(sx + 8 * 47, sy + (i - 43) * 47, x, i, panel, this::buttonListener);
            if (39 <= i && i < 44)
                field[i] = new Field(sx + (46 - i) * 47, sy - 5 * 47, x, i, panel, this::buttonListener);
            if (44 <= i && i < 49)
                field[i] = new Field(sx + 2 * 47, sy - (48 - i) * 47, x, i, panel, this::buttonListener);
            if (49 <= i && i < 52) field[i] = new Field(sx + (51 - i) * 47, sy + 47, x, i, panel, this::buttonListener);
            if (52 <= i && i < 57)
                field[i] = new Field(sx + 47, sy + (52 - i) * 47, x, i, panel, this::buttonListener); //red <52-56>
            if (57 <= i && i < 62)
                field[i] = new Field(sx - (62 - i) * 47, sy - 6 * 47, x, i, panel, this::buttonListener); //green <57-61>
            if (62 <= i && i < 67)
                field[i] = new Field(sx + 47, sy + (i - 74) * 47, x, i, panel, this::buttonListener); //yellow <62-66>
            if (67 <= i && i < 72)
                field[i] = new Field(sx + (74 - i) * 47, sy - 6 * 47, x, i, panel, this::buttonListener); //blue <67-71>
        }

        field[72] = new Field(79, 504, y, 72, panel, this::buttonListener, new Pawn(79, 504, PlayerColor.RED, panel));
        field[73] = new Field(157, 504, y, 73, panel, this::buttonListener, new Pawn(157, 504, PlayerColor.RED, panel));
        field[74] = new Field(79, 583, y, 74, panel, this::buttonListener, new Pawn(79, 583, PlayerColor.RED, panel));
        field[75] = new Field(157, 583, y, 75, panel, this::buttonListener, new Pawn(157, 583, PlayerColor.RED, panel));

        field[76] = new Field(79, 79, y, 76, panel, this::buttonListener, new Pawn(79, 79, PlayerColor.GREEN, panel));
        field[77] = new Field(157, 79, y, 77, panel, this::buttonListener, new Pawn(157, 79, PlayerColor.GREEN, panel));
        field[78] = new Field(79, 157, y, 78, panel, this::buttonListener, new Pawn(79, 157, PlayerColor.GREEN, panel));
        field[79] = new Field(157, 157, y, 79, panel, this::buttonListener, new Pawn(157, 157, PlayerColor.GREEN, panel));

        field[80] = new Field(504, 79, y, 80, panel, this::buttonListener, new Pawn(504, 79, PlayerColor.YELLOW, panel));
        field[81] = new Field(583, 79, y, 81, panel, this::buttonListener, new Pawn(583, 79, PlayerColor.YELLOW, panel));
        field[82] = new Field(504, 157, y, 82, panel, this::buttonListener, new Pawn(504, 157, PlayerColor.YELLOW, panel));
        field[83] = new Field(583, 157, y, 83, panel, this::buttonListener, new Pawn(583, 157, PlayerColor.YELLOW, panel));

        field[84] = new Field(504, 504, y, 84, panel, this::buttonListener, new Pawn(504, 504, PlayerColor.BLUE, panel));
        field[85] = new Field(583, 504, y, 85, panel, this::buttonListener, new Pawn(583, 504, PlayerColor.BLUE, panel));
        field[86] = new Field(504, 583, y, 86, panel, this::buttonListener, new Pawn(504, 583, PlayerColor.BLUE, panel));
        field[87] = new Field(583, 583, y, 87, panel, this::buttonListener, new Pawn(583, 583, PlayerColor.BLUE, panel));
    }

    public Board(PrintWriter out) {
        super("Ludo game!");
        this.out = out;
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/Pictures/LudoBoard.jpg");
        JLabel jl = new JLabel(img);
        jl.setBounds(5, 5, 700, 700);
        panel.setLayout(null);
        createFields();
        createScoreCounter();
        panel.add(jl);
        createPlayerInfo();
        createRollDiceButton();
        createConfirmReadyGameButton();
        createTestRollButtons();
        add(panel);
    }
}
