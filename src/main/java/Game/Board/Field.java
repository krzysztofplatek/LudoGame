package Game.Board;

import Game.Board.Pown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Field
{
    JButton button;
    Pown pown;
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
    public Pown getPown() {
        return pown;
    }
    public void setPown(Pown pown) {
        this.pown = pown;
        if(this.pown!=null)
            this.pown.setPosition(x,y);
    }
    public void deletePown()
    {
        pown.getPown().setVisible(false);
        this.pown=null;
    }
    public Field(int x, int y, int size, int index, JPanel panel, ActionListener buttonListener, Pown pown)
    {
        this.pown=pown;
        this.x=x;
        this.y=y;
        this.size=size;
        this.index=index;
        button= new JButton();
        button.setBounds(x,y,size,size);
        button.setFont(new Font(null,Font.BOLD,8));
        button.addActionListener(buttonListener);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        panel.add(button);
    }
    public Field(int x, int y, int size, int index, JPanel panel, ActionListener buttonListener)
    {
        pown=null;
        this.x=x;
        this.y=y;
        this.size=size;
        this.index=index;
        button= new JButton();
        button.setBounds(x,y,size,size);
        button.setFont(new Font(null,Font.BOLD,8));
        button.addActionListener(buttonListener);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        panel.add(button);
    }
}
