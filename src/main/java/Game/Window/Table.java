package Game.Window;

import ClientServer.LobbyInfoClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Table extends JFrame
{
    int position_x;
    int position_y;
    private JLabel inGame;
    private JButton joinButton;
    private JLabel info,txt1,txt2,txt3,txt4;
    public JButton getJoinButton() {
        return joinButton;
    }
    public void changeJoinButtonVisible()
    {
        if(joinButton.isVisible())
            joinButton.setVisible(false);
        else
            joinButton.setVisible(true);
    }
    public void changeInGameButtonVisible()
    {
        if(inGame.isVisible())
            inGame.setVisible(false);
        else
            inGame.setVisible(true);
    }
    public void update(LobbyInfoClient lobbyInfo){
        info.setText(lobbyInfo.getName());
        txt1.setText("R :"+lobbyInfo.getRed());
        txt2.setText("G :"+lobbyInfo.getGreen());
        txt3.setText("Y :"+lobbyInfo.getYellow());
        txt4.setText("B :"+lobbyInfo.getBlue());

        boolean started=false;
        if(lobbyInfo.getInGame().equals("yes")){
            started=true;
        }

        inGame.setVisible(started);
        joinButton.setVisible(!started);


    }

    public Table(int position_x, int position_y, JPanel panel, int tableNumber, ActionListener buttonListener, LobbyInfoClient lobbyInfo)
    {

        this.position_x=position_x;
        this.position_y=position_y;
        info=new JLabel(lobbyInfo.getName());
        info.setBounds(position_x,position_y,100,50);
        info.setFont(new Font(null,Font.BOLD,15));

        txt1= new JLabel("R :"+lobbyInfo.getRed());
        txt1.setBounds(position_x,position_y+25,100,50);
        txt1.setFont(new Font(null,Font.BOLD,15));

        txt2= new JLabel("G :"+lobbyInfo.getGreen());
        txt2.setBounds(position_x,position_y+50,100,50);
        txt2.setFont(new Font(null,Font.BOLD,15));

        txt3= new JLabel("Y :"+lobbyInfo.getYellow());
        txt3.setBounds(position_x,position_y+75,100,50);
        txt3.setFont(new Font(null,Font.BOLD,15));

        txt4= new JLabel("B :"+lobbyInfo.getBlue());
        txt4.setBounds(position_x,position_y+100,100,50);
        txt4.setFont(new Font(null,Font.BOLD,15));

        inGame=new JLabel("in Game");
        inGame.setBounds(position_x,position_y+140,100,50);
        inGame.setFont(new Font(null,Font.BOLD,20));
        joinButton=new JButton("Join");
        joinButton.setBounds(position_x,position_y+140,100,50);
        joinButton.addActionListener(buttonListener);
        panel.add(inGame);


        boolean started=false;

        if(lobbyInfo.getInGame().equals("yes")){
            started=true;
        }

        inGame.setVisible(started);
        joinButton.setVisible(!started);


        panel.add(joinButton);
        panel.add(info);
        panel.add(txt1);
        panel.add(txt2);
        panel.add(txt3);
        panel.add(txt4);


    }
}
