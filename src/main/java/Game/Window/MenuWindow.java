package Game.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

public class MenuWindow extends JFrame {
    private PrintWriter out;
    private String nickname;
    private JButton button;
    private JTextField textField;

    public String getNickname() {
        return nickname;
    }

    public MenuWindow(PrintWriter out) {
        super("Chose your nickname!");
        this.out = out;
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        pane.setLayout(null);

        JLabel text = new JLabel("Chose your nickname");
        text.setFont(new Font(null, Font.BOLD, 19));
        text.setBounds(100, 80, 200, 50);
        pane.add(text);

        textField = new JTextField("");
        textField.setFont(new Font(null, Font.BOLD, 20));
        textField.setBounds(100, 140, 200, 50);
        pane.add(textField);

        button = new JButton("Confirm");
        button.setBounds(150, 210, 100, 45);
        button.addActionListener(this::actionPerformed);
        pane.add(button);

        add(pane);
    }

    private void send(String msg) {
        out.println(msg);
    }

    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if (z == button) {
            String txt = textField.getText();
            if (!txt.isEmpty()) {
                nickname = txt;
                send("SETNAME;" + nickname);
                this.setVisible(false);
            }

        }
    }
}
