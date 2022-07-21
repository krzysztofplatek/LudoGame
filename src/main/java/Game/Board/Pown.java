package Game.Board;

import Game.Enum.PlayerColor;

import javax.swing.*;

public class Pown
{
    JLabel pown;
    private int position_x;
    private int position_y;
    private PlayerColor color;
    public PlayerColor getColor() {
        return color;
    }
    public void setPosition(int x,int y)
    {
        pown.setLocation(x,y);
    }

    public JLabel getPown() {
        return pown;
    }
    public Pown(int x, int y, PlayerColor color, JPanel panel)
    {
        this.color=color;
        position_x=x;
        position_y=y;
        ImageIcon img=null;
        if(color==PlayerColor.RED) img =new ImageIcon("src/Pictures/redP.png");
        if(color==PlayerColor.BLUE) img =new ImageIcon("src/Pictures/blueP.png");
        if(color==PlayerColor.GREEN) img =new ImageIcon("src/Pictures/greenP.png");
        if(color==PlayerColor.YELLOW) img =new ImageIcon("src/Pictures/yellowP.png");
        pown= new JLabel(img);
        pown.setBounds(x,y,47,47);
        panel.add(pown);
    }
}
