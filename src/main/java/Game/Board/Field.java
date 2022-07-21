package Game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Field {
    JButton button;
    Pawn pawn;
    private int size;
    private int x;
    private int y;
    private int index;

    public int getIndex() {
        return index;
    }

    public JButton getButton() {
        return button;
    }

    public Pawn getPown() {
        return pawn;
    }

    public void setPown(Pawn pawn) {
        this.pawn = pawn;
        if (this.pawn != null)
            this.pawn.setPosition(x, y);
    }

    public void deletePown() {
        pawn.getPawn().setVisible(false);
        this.pawn = null;
    }

    public Field(int x, int y, int size, int index, JPanel panel, ActionListener buttonListener, Pawn pawn) {
        this.pawn = pawn;
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
        button = new JButton();
        button.setBounds(x, y, size, size);
        button.setFont(new Font(null, Font.BOLD, 8));
        button.addActionListener(buttonListener);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        panel.add(button);
    }

    public Field(int x, int y, int size, int index, JPanel panel, ActionListener buttonListener) {
        pawn = null;
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
        button = new JButton();
        button.setBounds(x, y, size, size);
        button.setFont(new Font(null, Font.BOLD, 8));
        button.addActionListener(buttonListener);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        panel.add(button);
    }
}
