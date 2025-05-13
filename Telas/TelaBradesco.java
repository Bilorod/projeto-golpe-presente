package Telas;
import Componentes.BotaoCustomizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaBradesco extends JPanel {
    private final Image backgroundImage;
    private final Image logo;

    public TelaBradesco() {
        setLayout(null);

        // Carrega imagens
        backgroundImage = new ImageIcon(getClass().getResource("/img/background.png")).getImage();
        logo = new ImageIcon(getClass().getResource("/img/logo.png")).getImage();

        // Botões personalizados
        JButton btnVerificar = BotaoCustomizado.criar("Verificar boleto", "botao.png");
        btnVerificar.setBounds(480, 450, 250, 55);
        btnVerificar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        add(btnVerificar);

        //JButton btnDenunciados = BotaoCustomizado.criar("Boletos denunciados", "botao.png");
        //btnDenunciados.setBounds(510, 550, 200, 50);
        //btnDenunciados.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        //add(btnDenunciados);

        // Adiciona efeito hover com animação
        adicionarHover(btnVerificar);
        //adicionarHover(btnDenunciados);

        // Ação do botão "Verificar boleto"
        btnVerificar.addActionListener(e -> new TelaVerificarBoleto());
    }

    // Método para adicionar o efeito hover com animação
    private void adicionarHover(JButton botao) {
        final Color corInicial = new Color(200, 200, 200);
        final Color corHover = Color.WHITE;
        final int duracao = 200; // milissegundos
        final int steps = 10;
        final int delay = duracao / steps;

        botao.addMouseListener(new MouseAdapter() {
            Timer timerEnter, timerExit;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (timerExit != null && timerExit.isRunning()) timerExit.stop();

                timerEnter = new Timer(delay, null);
                timerEnter.addActionListener(new ActionListener() {
                    int i = 0;
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        float t = (float) i / steps;
                        Color interpolada = interpolarCores(corInicial, corHover, t);
                        botao.setBorder(BorderFactory.createLineBorder(interpolada, 2));
                        i++;
                        if (i > steps) timerEnter.stop();
                    }
                });
                timerEnter.start();
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (timerEnter != null && timerEnter.isRunning()) timerEnter.stop();

                timerExit = new Timer(delay, null);
                timerExit.addActionListener(new ActionListener() {
                    int i = 0;
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        float t = (float) i / steps;
                        Color interpolada = interpolarCores(corHover, corInicial, t);
                        botao.setBorder(BorderFactory.createLineBorder(interpolada, 1));
                        i++;
                        if (i > steps) timerExit.stop();
                    }
                });
                timerExit.start();
                botao.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    // Método auxiliar para interpolar cores
    private Color interpolarCores(Color c1, Color c2, float t) {
        int r = (int) (c1.getRed() + t * (c2.getRed() - c1.getRed()));
        int g = (int) (c1.getGreen() + t * (c2.getGreen() - c1.getGreen()));
        int b = (int) (c1.getBlue() + t * (c2.getBlue() - c1.getBlue()));
        return new Color(r, g, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Fundo
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Logo
        int logoWidth = 250, logoHeight = 90;
        int logoX = (getWidth() - logoWidth) / 2;
        g2d.drawImage(logo, logoX, 80, logoWidth, logoHeight, this);

        // Texto
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fm = g2d.getFontMetrics();

        int textoY = 190;
        int linhaY = 200;

        g2d.drawString("Olá, Rodrigo!", 80, textoY);

        String ag = "AG 1234";
        int agX = getWidth() / 2 - fm.stringWidth(ag) / 2;
        g2d.drawString(ag, agX, textoY);

        String conta = "C/C 12345-6";
        int contaX = getWidth() - 80 - fm.stringWidth(conta);
        g2d.drawString(conta, contaX, textoY);

        g2d.drawLine(60, linhaY, getWidth() - 60, linhaY);
    }
}
