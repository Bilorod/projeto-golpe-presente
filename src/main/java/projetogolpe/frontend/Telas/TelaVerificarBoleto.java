package projetogolpe.frontend.Telas;

import projetogolpe.frontend.Componentes.BotaoCustomizado;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TelaVerificarBoleto extends JFrame {
    private List<BankEntry> bankEntries = new ArrayList();
    private final Image backgroundImage;
    private final Image logo;
    private JTextArea txtResultado;
    private JButton btnDenunciar;
    private JComboBox<BankEntry> comboBanco;
    private JTextField txtCpfCnpj;

    public TelaVerificarBoleto() {
        this.setTitle("Verificar Boleto");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        this.backgroundImage = (new ImageIcon(this.getClass().getResource("/img/background.png"))).getImage();
        this.logo = (new ImageIcon(this.getClass().getResource("/img/logo.png"))).getImage();
        this.setContentPane(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.drawImage(TelaVerificarBoleto.this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                int logoWidth = 250;
                int logoHeight = 90;
                int logoX = (this.getWidth() - logoWidth) / 2;
                g2d.drawImage(TelaVerificarBoleto.this.logo, logoX, 80, logoWidth, logoHeight, this);
                g2d.setColor(Color.WHITE);
                int linhaY = 210;
                g2d.drawLine(60, linhaY, this.getWidth() - 60, linhaY);
                String texto = "Insira as informações do boleto";
                Font font = new Font("Arial", 1, 24);
                g2d.setFont(font);
                int textoX = (this.getWidth() - g2d.getFontMetrics().stringWidth(texto)) / 2;
                g2d.drawString(texto, textoX, linhaY - 10);
            }
        });
        this.setLayout((LayoutManager)null);
        int x = 100;
        int y = 250;
        int larguraCampo = 300;
        int alturaCampo = 30;
        JLabel lblBanco = new JLabel("Banco (código 3 dígitos):");
        lblBanco.setBounds(x, y, 200, 30);
        lblBanco.setForeground(Color.WHITE);
        this.add(lblBanco);
        this.comboBanco = new JComboBox();
        this.comboBanco.setEditable(true);
        this.comboBanco.setBounds(x, y + 30, larguraCampo, alturaCampo);
        this.add(this.comboBanco);
        this.fetchAndPopulateBancos();
        this.attachCodeListener();
        JLabel lblDestinatario = new JLabel("Destinatário:");
        lblDestinatario.setBounds(x, y + 70, 120, 30);
        lblDestinatario.setForeground(Color.WHITE);
        this.add(lblDestinatario);
        JTextField txtDestinatario = new JTextField();
        txtDestinatario.setBounds(x, y + 100, larguraCampo, alturaCampo);
        this.add(txtDestinatario);
        JLabel lblCodigoBarras = new JLabel("Código de Barras:");
        lblCodigoBarras.setBounds(x, y + 140, 150, 30);
        lblCodigoBarras.setForeground(Color.WHITE);
        this.add(lblCodigoBarras);
        JTextField txtCodigoBarras = new JTextField();
        txtCodigoBarras.setBounds(x, y + 170, larguraCampo, alturaCampo);
        this.add(txtCodigoBarras);
        JButton btnValidar = BotaoCustomizado.criar("Validar", "/");
        btnValidar.setBounds(x, y + 210, larguraCampo, alturaCampo);
        this.add(btnValidar);
        this.txtResultado = new JTextArea();
        this.txtResultado.setBounds(750, 300, 400, 85);
        this.txtResultado.setFont(new Font("Arial", 1, 18));
        this.txtResultado.setForeground(Color.WHITE);
        this.txtResultado.setOpaque(false);
        this.txtResultado.setLineWrap(true);
        this.txtResultado.setWrapStyleWord(true);
        this.txtResultado.setEditable(false);
        this.txtResultado.setVisible(false);
        this.add(this.txtResultado);
        this.btnDenunciar = new JButton("Denunciar Boleto");
        this.btnDenunciar.setBounds(750, 390, 200, 35);
        this.btnDenunciar.setVisible(false);
        this.add(this.btnDenunciar);
        btnValidar.addActionListener((e) -> {
            String banco = this.comboBanco.getEditor().getItem().toString().trim();
            String destinatario = txtDestinatario.getText().trim();
            String codigo = txtCodigoBarras.getText().trim();
            JSONObject payload = new JSONObject();

            try {
                payload.put("banco", banco);
                payload.put("destinatario", destinatario);
                payload.put("codigoDeBarras", codigo);
            } catch (JSONException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao montar JSON:\n" + ex.getMessage(), "Erro de JSON", 0);
                return;
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/verificar")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(payload.toString())).build();

            try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                String resp = (String)response.body();
                this.txtResultado.setText(resp);
                this.txtResultado.setVisible(true);
                boolean isValido = resp.startsWith("Boleto sem nenhuma inconsistência detectada");
                this.btnDenunciar.setVisible(!isValido);
            } catch (InterruptedException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao verificar boleto: " + ((Exception)ex).getMessage(), "Erro", 0);
            }

        });
        this.btnDenunciar.addActionListener((e) -> {
            String banco = this.comboBanco.getEditor().getItem().toString().trim();
            String destinatario = txtDestinatario.getText().trim();
            String codigoBarras = txtCodigoBarras.getText().trim();
            String ultimos = codigoBarras.substring(codigoBarras.length() - 10);
            String valorStr = String.format("%.2f", Double.parseDouble(ultimos) / (double)100.0F);
            JSONObject payload = new JSONObject();

            try {
                payload.put("banco", banco);
                payload.put("codigoBarras", codigoBarras);
                payload.put("cpfCnpj", destinatario);
                payload.put("valor", valorStr);
            } catch (JSONException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao montar JSON de denúncia:\n" + ex.getMessage(), "Erro de JSON", 0);
                return;
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/denunciar")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(payload.toString())).build();

            try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                this.txtResultado.setText((String)response.body());
                this.txtResultado.setVisible(true);
            } catch (InterruptedException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao enviar denúncia: " + ((Exception)ex).getMessage(), "Erro", 0);
            }

        });
        this.setVisible(true);
    }

    private void fetchAndPopulateBancos() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/bancos")).GET().build();
        client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept((body) -> {
            try {
                JSONArray arr = new JSONArray(body);
                BankEntry[] entries = new BankEntry[arr.length()];

                for(int i = 0; i < arr.length(); ++i) {
                    JSONObject obj = arr.getJSONObject(i);
                    entries[i] = new BankEntry(obj.getString("nomeBanco"), obj.getString("codigoBanco"));
                }

                this.bankEntries = Arrays.asList(entries);
                SwingUtilities.invokeLater(() -> this.comboBanco.setModel(new DefaultComboBoxModel(entries)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).exceptionally((ex) -> {
            ex.printStackTrace();
            return null;
        });
    }

    private void attachCodeListener() {
        final JTextField editor = (JTextField)this.comboBanco.getEditor().getEditorComponent();
        editor.getDocument().addDocumentListener(new DocumentListener() {
            private void checkCode() {
                String txt = editor.getText().trim();
                if (txt.length() == 3) {
                    for(BankEntry be : TelaVerificarBoleto.this.bankEntries) {
                        if (be.getCode().equals(txt)) {
                            SwingUtilities.invokeLater(() -> TelaVerificarBoleto.this.comboBanco.setSelectedItem(be));
                            break;
                        }
                    }
                }

            }

            public void insertUpdate(DocumentEvent e) {
                this.checkCode();
            }

            public void removeUpdate(DocumentEvent e) {
                this.checkCode();
            }

            public void changedUpdate(DocumentEvent e) {
                this.checkCode();
            }
        });
    }

    private static class BankEntry {
        private final String name;
        private final String code;

        public BankEntry(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String toString() {
            return this.name + " (" + this.code + ")";
        }

        public String getCode() {
            return this.code;
        }
    }
}
