package Telas;
import Componentes.BotaoCustomizado;

import javax.swing.*;
import java.awt.*;

public class TelaVerificarBoleto extends JFrame {
    private final Image backgroundImage;
    private final Image logo;

    // Componentes de resultado
    private JTextArea txtResultado;
    private JButton btnDenunciar;

    public TelaVerificarBoleto() {
        setTitle("Verificar Boleto");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        backgroundImage = new ImageIcon(getClass().getResource("/img/background.png")).getImage();
        logo = new ImageIcon(getClass().getResource("/img/logo.png")).getImage();

        setContentPane(new JPanel() {
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

                // Linha branca
                g2d.setColor(Color.WHITE);
                int linhaY = 210;
                g2d.drawLine(60, linhaY, getWidth() - 60, linhaY);

                // Texto
                String texto = "Insira as informações do boleto";
                Font font = new Font("Arial", Font.BOLD, 24);
                g2d.setFont(font);
                FontMetrics metrics = g2d.getFontMetrics();
                int textoX = (getWidth() - metrics.stringWidth(texto)) / 2;
                int textoY = linhaY - 10;
                g2d.setColor(Color.WHITE);
                g2d.drawString(texto, textoX, textoY);
            }
        });

        setLayout(null);

        int larguraCampo = 300;
        int alturaCampo = 30;
        int margem = 20;

        int x = 100;
        int y = 250;

        // Campos e rótulos
        JLabel lblBranco = new JLabel("Branco:");
        lblBranco.setBounds(x, y, 100, 30);
        lblBranco.setForeground(Color.WHITE);
        add(lblBranco);

        JTextField txtBranco = new JTextField();
        txtBranco.setBounds(x, y + 30, larguraCampo, alturaCampo);
        add(txtBranco);

        JLabel lblDestinatario = new JLabel("Destinatário:");
        lblDestinatario.setBounds(x, y + 70, 120, 30);
        lblDestinatario.setForeground(Color.WHITE);
        add(lblDestinatario);

        JTextField txtDestinatario = new JTextField();
        txtDestinatario.setBounds(x, y + 100, larguraCampo, alturaCampo);
        add(txtDestinatario);

        JLabel lblCodigoBarras = new JLabel("Código de Barras:");
        lblCodigoBarras.setBounds(x, y + 140, 150, 30);
        lblCodigoBarras.setForeground(Color.WHITE);
        add(lblCodigoBarras);

        JTextField txtCodigoBarras = new JTextField();
        txtCodigoBarras.setBounds(x, y + 170, larguraCampo, alturaCampo);
        add(txtCodigoBarras);

        JButton btnValidar = BotaoCustomizado.criar("Validar", "/");
        btnValidar.setBounds(x, y + 210, larguraCampo, alturaCampo);
        add(btnValidar);

        // Resultado: Área de texto e botão à direita
        txtResultado = new JTextArea();
        txtResultado.setBounds(750, 300, 400, 85);
        txtResultado.setFont(new Font("Arial", Font.BOLD, 18));
        txtResultado.setForeground(Color.WHITE);
        txtResultado.setOpaque(false); // fundo transparente
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setEditable(false);
        txtResultado.setVisible(false);
        add(txtResultado);

        btnDenunciar = new JButton("Denunciar Boleto");
        btnDenunciar.setBounds(750, 390, 200, 35);
        btnDenunciar.setVisible(false);
        add(btnDenunciar);

        // Ação do botão "Validar"
        btnValidar.addActionListener(e -> {
            String codigo = txtCodigoBarras.getText().trim();

            txtResultado.setVisible(true);

            if (codigo.equals("123456789")) {
                txtResultado.setText("Boleto verificado com sucesso!");
                txtResultado.setForeground(Color.WHITE);
                btnDenunciar.setVisible(false);
            } else {
                txtResultado.setText("Atenção: este boleto pode ser falso! Verifique cuidadosamente os dados antes de realizar o pagamento.");
                txtResultado.setForeground(Color.WHITE);
                btnDenunciar.setVisible(true);
            }
        });

        // Ação do botão "Denunciar"
        btnDenunciar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Boleto denunciado às autoridades.");
        });

        setVisible(true);
    }
}
