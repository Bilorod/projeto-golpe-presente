package Telas;

import javax.swing.*;

public class FalsoMain extends JFrame {
    public FalsoMain() {
        setTitle("Bradesco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new TelaBradesco());
        setVisible(true);
    }

    public static void main(String[] args) {
        new FalsoMain();
    }
}
