package Game.Window;

import ClientServer.LobbyInfoClient;

import javax.swing.*;
//import java.awt.*;
import  java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class ClientWindow extends JFrame
{
    private PrintWriter out;
    JPanel panel;
    private Table table1,table2,table3,table4;
    List <LobbyInfoClient> info;
    public ClientWindow(PrintWriter out, List <LobbyInfoClient> i,String nickname)
    {
        super(nickname);
        this.out=out;
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.info=i;
        panel = new JPanel();
        createTable1();
        createTable2();
        createTable3();
        createTable4();
        panel.setLayout(null);
        add(panel);
    }
    private void buttonListener(ActionEvent event)
    {
        Object z = event.getSource();
        if(z==table1.getJoinButton()) {
            send("JOINLOBBY;LOBBY 1");
        }
        if(z==table2.getJoinButton()) {
            send("JOINLOBBY;LOBBY 2");
        }
        if(z==table3.getJoinButton()) {
            send("JOINLOBBY;LOBBY 3");
        }
        if(z==table4.getJoinButton()) {
            send("JOINLOBBY;LOBBY 4");
        }
    }
    public void reloadTables(List <LobbyInfoClient> i){
        this.info=i;
        table1.update(info.get(0));
        table2.update(info.get(1));
        table3.update(info.get(2));
        table4.update(info.get(3));
    }
    public void createTable1()
    {
        table1=new Table(50,50,panel,1,this::buttonListener,info.get(0));
    }
    public void createTable2()
    {
        table2=new Table(250,50,panel,2,this::buttonListener,info.get(1));
    }
    public void createTable3()
    {
        table3=new Table(50,300,panel,3,this::buttonListener,info.get(2));
    }
    public void createTable4()
    {
        table4=new Table(250,300,panel,4,this::buttonListener,info.get(3));
    }
    private void send(String msg){
        out.println(msg);
    }
}