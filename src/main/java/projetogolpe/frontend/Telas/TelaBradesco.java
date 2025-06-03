package projetogolpe.frontend.Telas;

import projetogolpe.frontend.Componentes.BotaoCustomizado;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TelaBradesco extends JPanel {
    private final Image backgroundImage;
    private final Image logo;

    public TelaBradesco() {
        this.setLayout((LayoutManager)null);
        this.backgroundImage = (new ImageIcon(this.getClass().getResource("/img/background.png"))).getImage();
        this.logo = (new ImageIcon(this.getClass().getResource("/img/logo.png"))).getImage();
        JButton btnVerificar = BotaoCustomizado.criar("Verificar boleto", "botao_buscar.png");
        btnVerificar.setBounds(480, 450, 250, 55);
        btnVerificar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        this.add(btnVerificar);
        this.adicionarHover(btnVerificar);
        btnVerificar.addActionListener((e) -> new TelaVerificarBoleto());
    }

    private void adicionarHover(final JButton botao) {
        final Color corInicial = new Color(200, 200, 200);
        final Color corHover = Color.WHITE;
        int duracao = 200;
        int steps = 10;
        int delay = 20;
        botao.addMouseListener(new MouseAdapter() {
            Timer timerEnter;
            Timer timerExit;

            public void mouseEntered(MouseEvent e) {
                if (this.timerExit != null && this.timerExit.isRunning()) {
                    this.timerExit.stop();
                }

                this.timerEnter = new Timer(20, (ActionListener)null);
                this.timerEnter.addActionListener(new ActionListener() {
                    int i = 0;

                    public void actionPerformed(ActionEvent evt) {
                        float t = (float)this.i / 10.0F;
                        Color interpolada = TelaBradesco.this.interpolarCores(corInicial, corHover, t);
                        botao.setBorder(BorderFactory.createLineBorder(interpolada, 2));
                        ++this.i;
                        if (this.i > 10) {
                            timerEnter.stop();
                        }

                    }
                });
                this.timerEnter.start();
                botao.setCursor(Cursor.getPredefinedCursor(12));
            }

            public void mouseExited(MouseEvent e) {
                if (this.timerEnter != null && this.timerEnter.isRunning()) {
                    this.timerEnter.stop();
                }

                this.timerExit = new Timer(20, (ActionListener)null);
                this.timerExit.addActionListener(new ActionListener() {
                    int i = 0;

                    public void actionPerformed(ActionEvent evt) {
                        float t = (float)this.i / 10.0F;
                        Color interpolada = TelaBradesco.this.interpolarCores(corHover, corInicial, t);
                        botao.setBorder(BorderFactory.createLineBorder(interpolada, 1));
                        ++this.i;
                        if (this.i > 10) {
                            timerExit.stop();
                        }

                    }
                });
                this.timerExit.start();
                botao.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private Color interpolarCores(Color c1, Color c2, float t) {
        int r = (int)((float)c1.getRed() + t * (float)(c2.getRed() - c1.getRed()));
        int g = (int)((float)c1.getGreen() + t * (float)(c2.getGreen() - c1.getGreen()));
        int b = (int)((float)c1.getBlue() + t * (float)(c2.getBlue() - c1.getBlue()));
        return new Color(r, g, b);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        int logoWidth = 250;
        int logoHeight = 90;
        int logoX = (this.getWidth() - logoWidth) / 2;
        g2d.drawImage(this.logo, logoX, 80, logoWidth, logoHeight, this);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", 1, 18));
        FontMetrics fm = g2d.getFontMetrics();
        int textoY = 190;
        int linhaY = 200;
        g2d.drawString("Olá, Rodrigo!", 80, textoY);
        String ag = "AG 1234";
        int agX = this.getWidth() / 2 - fm.stringWidth(ag) / 2;
        g2d.drawString(ag, agX, textoY);
        String conta = "C/C 12345-6";
        int contaX = this.getWidth() - 80 - fm.stringWidth(conta);
        g2d.drawString(conta, contaX, textoY);
        g2d.drawLine(60, linhaY, this.getWidth() - 60, linhaY);
    }
}
