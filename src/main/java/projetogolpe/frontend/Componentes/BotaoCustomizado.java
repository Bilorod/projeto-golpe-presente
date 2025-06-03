package projetogolpe.frontend.Componentes;


import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotaoCustomizado {
    public BotaoCustomizado() {
    }

    public static JButton criar(String texto, String iconPath) {
        JButton button = new JButton(texto);
        URL iconUrl = BotaoCustomizado.class.getResource("/img/" + iconPath);
        if (iconUrl != null) {
            ImageIcon originalIcon = new ImageIcon(iconUrl);
            Image iconImage = originalIcon.getImage();
            Image resizedImage = iconImage.getScaledInstance(20, 20, 4);
            button.setIcon(new ImageIcon(resizedImage));
        } else {
            System.err.println("⚠️ Ícone não encontrado: " + iconPath);
        }

        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(4);
        button.setIconTextGap(10);
        return button;
    }
}
