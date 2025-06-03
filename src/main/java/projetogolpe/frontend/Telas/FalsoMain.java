package projetogolpe.frontend.Telas;

import java.awt.Component;
import javax.swing.JFrame;

public class FalsoMain extends JFrame {
    public FalsoMain() {
        this.setTitle("Bradesco");
        this.setDefaultCloseOperation(3);
        this.setSize(1200, 800);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        this.add(new TelaBradesco());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FalsoMain();
    }
}
