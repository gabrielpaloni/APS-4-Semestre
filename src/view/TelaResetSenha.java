package view;

import controller.ResetSenhaController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaResetSenha extends JFrame {

    private static final Color COR_FUNDO_ESCURO = new Color(18, 18, 18);
    private static final Color COR_CAMPO_TEXTO = new Color(31, 31, 31);
    private static final Color COR_AZUL_DESTAQUE = new Color(0, 122, 255);
    private static final Color COR_TEXTO_BRANCO = new Color(240, 240, 240);
    private static final Color COR_TEXTO_CINZA = new Color(160, 160, 160);
    private static final Color COR_BORDA_PADRAO = new Color(80, 80, 80);
    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);

    private JTextField txtEmail;
    private JButton btnRedefinir;
    private JLabel lblVoltarAoLogin;
    private String tipoUsuario;

    private ResetSenhaController controller;

    public TelaResetSenha(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.controller = new ResetSenhaController(this);

        setBackground(COR_FUNDO_ESCURO);
        setTitle("PixelHaus - Redefinir Senha");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(COR_FUNDO_ESCURO);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));
        setContentPane(painelPrincipal);

        JLabel lblTitulo = new JLabel("Redefinir Senha");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO_BRANCO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblInfo = criarLabel("Digite o email da sua conta (" + tipoUsuario + ")");
        txtEmail = criarCampoDeTexto();

        btnRedefinir = new BotaoGradiente("Verificar Email");

        lblVoltarAoLogin = new JLabel("Voltar ao Login");
        lblVoltarAoLogin.setFont(FONTE_PADRAO);
        lblVoltarAoLogin.setForeground(COR_TEXTO_CINZA);
        lblVoltarAoLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Layout
        GroupLayout layout = new GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo, GroupLayout.Alignment.LEADING)
                        .addComponent(lblInfo, GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRedefinir, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVoltarAoLogin)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(30)
                        .addComponent(lblInfo)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
                        .addComponent(btnRedefinir, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(lblVoltarAoLogin)
        );

        configurarListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void configurarListeners() {
        btnRedefinir.addActionListener(e -> controller.acaoBotaoRedefinir());

        aplicarEfeitoHoverLink(lblVoltarAoLogin);
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

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_PADRAO);
        label.setForeground(COR_TEXTO_CINZA);
        return label;
    }

    private JTextField criarCampoDeTexto() {
        JTextField campo = new JTextField();
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_CAMPO_TEXTO);
        campo.setForeground(COR_TEXTO_BRANCO);
        campo.setCaretColor(COR_TEXTO_BRANCO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_PADRAO),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return campo;
    }

    private void aplicarEfeitoHoverLink(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(COR_AZUL_DESTAQUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(COR_TEXTO_CINZA);
            }
        });
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
}