package view;

import javax.swing.*;
import java.awt.*;

public class ApplicationView extends JFrame {
    private JPanel p;
    private JLabel l;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;

    public ApplicationView() {
        super("Fortnite Lifetime and Daily stats");
        setVisible(true);
        setSize(400, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        l = new JLabel("User: Not found yet", SwingConstants.CENTER);
        p = new JPanel(new GridLayout(4, 1));
        l2 = new JLabel("Daily wins: Loading data...", SwingConstants.CENTER);
        l3 = new JLabel("Lifetime wins: Loading data...", SwingConstants.CENTER);
        l4 = new JLabel("Next update: Loading data...", SwingConstants.CENTER);
        p.add(l);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        add(p);
    }

    public void setL(String input) {
        l.setText(input);
    }

    public void setL2(String input) {
        l2.setText(input);
    }

    public void setL3(String input) {
        l3.setText(input);
    }

    public void setL4(String input) {
        l4.setText(input);
    }
}
