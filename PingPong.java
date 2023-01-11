import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PingPong extends JFrame{

    public PingPong(){
        setSize(new Dimension(600,700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public static void main(String[] args) {
        PingPong ping = new PingPong();
        ping.add(new InnerScreen());

        ping.validate();
    }
}

