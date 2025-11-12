package view;

import controller.ResetSenhaController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaResetSenha extends JFrame {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);
    private static final Color COR_TEXTO_LABEL = new Color(180, 180, 180);

    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);

    private JTextField txtEmail;
    private JButton btnRedefinir;
    private JLabel lblVoltarAoLogin;
    private String tipoUsuario;

    private ResetSenhaController controller;
    private Image imgBackground;

    public TelaResetSenha(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.controller = new ResetSenhaController(this);

        setTitle("PixelHaus - Redefinir Senha");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        carregarImagens();

        BackgroundPanel painelPrincipal = new BackgroundPanel(imgBackground);
        painelPrincipal.setLayout(new GridBagLayout());
        setContentPane(painelPrincipal);

        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setOpaque(true);
        painelConteudo.setBackground(COR_FUNDO);
        painelConteudo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_DESTAQUE, 1),
                new EmptyBorder(20, 40, 20, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Redefinir Senha");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 20, 0);
        painelConteudo.add(lblTitulo, gbc);

        JLabel lblInfo = criarLabel("Digite o email da sua conta (" + tipoUsuario + ")");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 0, 2, 0);
        painelConteudo.add(lblInfo, gbc);

        txtEmail = criarCampoDeTexto();
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(txtEmail, gbc);

        btnRedefinir = new BotaoGradiente("Verificar Email");
        btnRedefinir.setPreferredSize(new Dimension(300, 45));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        painelConteudo.add(btnRedefinir, gbc);

        lblVoltarAoLogin = criarLink("Voltar ao Login");
        JPanel painelLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelLink.setOpaque(false);
        painelLink.add(lblVoltarAoLogin);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 8, 0);
        painelConteudo.add(painelLink, gbc);

        painelPrincipal.add(painelConteudo, new GridBagConstraints());

        configurarListeners();

        setSize(500, 600);
        setLocationRelativeTo(null);
    }

    private void carregarImagens() {
        try {
            URL bgUrl = getClass().getClassLoader().getResource("login_bg.png");
            if (bgUrl == null) {
                throw new RuntimeException("Imagem 'login_bg.png' não encontrada. Verifique a pasta 'resources'.");
            }
            this.imgBackground = new ImageIcon(bgUrl).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo: " + e.getMessage());
            e.printStackTrace();
            this.imgBackground = null;
        }
    }

    private void configurarListeners() {
        btnRedefinir.addActionListener(e -> controller.acaoBotaoRedefinir());

        lblVoltarAoLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }

    public String getEmail() { return txtEmail.getText(); }
    public String getTipoUsuario() { return tipoUsuario; }
    public void exibirMensagem(String mensagem) { JOptionPane.showMessageDialog(this, mensagem); }

    private Border criarBordaNeonComPadding() {
        Border linhaBorda = BorderFactory.createLineBorder(COR_DESTAQUE, 1, true);
        Border padding = new EmptyBorder(8, 10, 8, 10);
        return BorderFactory.createCompoundBorder(linhaBorda, padding);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_LABEL);
        label.setForeground(COR_TEXTO_LABEL);
        return label;
    }

    private JTextField criarCampoDeTexto() {
        JTextField campo = new JTextField(25);
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_FUNDO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_DESTAQUE);
        campo.setBorder(criarBordaNeonComPadding());
        campo.setOpaque(true);
        return campo;
    }

    private JLabel criarLink(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_PADRAO);
        label.setForeground(COR_DESTAQUE);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(COR_TEXTO.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(COR_DESTAQUE);
            }
        });
        return label;
    }

    class BotaoGradiente extends JButton {
        private boolean isHovered = false;
        public BotaoGradiente(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 16));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { isHovered = true; repaint(); }
                @Override
                public void mouseExited(MouseEvent e) { isHovered = false; repaint(); }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color color1 = new Color(0, 122, 255);
            Color color2 = new Color(0, 199, 255);
            if (isHovered) {
                color1 = color1.brighter();
                color2 = color2.brighter();
            }
            GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}