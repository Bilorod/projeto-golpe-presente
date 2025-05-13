package Componentes;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BotaoCustomizado {
    public static JButton criar(String texto, String iconPath) {
        JButton button = new JButton(texto);

        // Obtendo a URL do arquivo de ícone a partir do recurso
        URL iconUrl = BotaoCustomizado.class.getResource("/img/" + iconPath);

        if (iconUrl != null) {
            // Criar a imagem a partir do ícone
            ImageIcon originalIcon = new ImageIcon(iconUrl);
            Image iconImage = originalIcon.getImage();

            // Redimensionar a imagem para um tamanho fixo (por exemplo, 40x40 pixels)
            Image resizedImage = iconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(resizedImage));
        } else {
            System.err.println("⚠️ Ícone não encontrado: " + iconPath);
        }

        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);

        return button;
    }
}
