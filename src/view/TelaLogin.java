package view;

import controller.LoginController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaLogin extends JFrame {

    private static final Color COR_FUNDO_ESCURO = new Color(18, 18, 18);
    private static final Color COR_CAMPO_TEXTO = new Color(31, 31, 31);
    private static final Color COR_AZUL_DESTAQUE = new Color(0, 122, 255);
    private static final Color COR_TEXTO_BRANCO = new Color(240, 240, 240);
    private static final Color COR_TEXTO_CINZA = new Color(160, 160, 160);
    private static final Color COR_BORDA_PADRAO = new Color(80, 80, 80);
    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JToggleButton btnVerSenha;
    private JRadioButton rdoUsuario;
    private JRadioButton rdoVendedor;
    private JButton btnEntrar;
    private JLabel lblCadastrese;
    private JLabel lblEsqueciSenha;
    private LoginController controller;

    private ImageIcon iconOlhoAberto;
    private ImageIcon iconOlhoFechado;

    public TelaLogin() {
        setBackground(COR_FUNDO_ESCURO);
        setTitle("PixelHaus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(COR_FUNDO_ESCURO);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));
        setContentPane(painelPrincipal);

        try {
            URL locationOlhoAberto = getClass().getClassLoader().getResource("olho_aberto.png");
            URL locationOlhoFechado = getClass().getClassLoader().getResource("olho_fechado.png");

            if (locationOlhoAberto == null || locationOlhoFechado == null) {
                throw new RuntimeException("Não foi possível encontrar os ícones de olho. Verifique a pasta 'resources'.");
            }

            iconOlhoAberto = new ImageIcon(locationOlhoAberto);
            iconOlhoFechado = new ImageIcon(locationOlhoFechado);

        } catch (Exception e) {
            System.err.println("Erro ao carregar os ícones de olho: " + e.getMessage());
            e.printStackTrace();
            iconOlhoAberto = null;
            iconOlhoFechado = null;
        }

        controller = new LoginController(this);

        JLabel lblTitulo = new JLabel("Bem Vindo a PixelHaus");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO_BRANCO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblEmail = criarLabel("Email");
        txtEmail = criarCampoDeTexto();

        JLabel lblSenha = criarLabel("Senha");

        JPanel painelSenha = new JPanel(new BorderLayout(0, 0));
        painelSenha.setOpaque(true);
        painelSenha.setBackground(COR_CAMPO_TEXTO);

        txtSenha = criarCampoDeSenha();
        btnVerSenha = criarBotaoVerSenha();

        painelSenha.add(txtSenha, BorderLayout.CENTER);
        painelSenha.add(btnVerSenha, BorderLayout.EAST);

        painelSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_PADRAO),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)
        ));

        rdoUsuario = criarRadioButton("Usuário", true);
        rdoVendedor = criarRadioButton("Vendedor", false);

        ButtonGroup grpTipoUsuario = new ButtonGroup();
        grpTipoUsuario.add(rdoUsuario);
        grpTipoUsuario.add(rdoVendedor);

        JPanel painelRadios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelRadios.setOpaque(false);
        painelRadios.add(rdoUsuario);
        painelRadios.add(Box.createHorizontalStrut(20));
        painelRadios.add(rdoVendedor);

        btnEntrar = new BotaoGradiente("Entrar");

        lblCadastrese = new JLabel("Ainda não tenho uma conta");
        lblCadastrese.setFont(FONTE_PADRAO);
        lblCadastrese.setForeground(COR_TEXTO_CINZA);
        lblCadastrese.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblEsqueciSenha = new JLabel("Esqueci minha senha");
        lblEsqueciSenha.setFont(FONTE_PADRAO);
        lblEsqueciSenha.setForeground(COR_TEXTO_CINZA);
        lblEsqueciSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GroupLayout layout = new GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo, GroupLayout.Alignment.LEADING)
                        .addComponent(lblEmail, GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha, GroupLayout.Alignment.LEADING)
                        .addComponent(painelSenha, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(painelRadios, GroupLayout.Alignment.LEADING)
                        .addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCadastrese)
                        .addComponent(lblEsqueciSenha)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(30)
                        .addComponent(lblEmail)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblSenha)
                        .addComponent(painelSenha, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(painelRadios)
                        .addGap(30)
                        .addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(lblCadastrese)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEsqueciSenha)
        );

        configurarListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void configurarListeners() {
        btnEntrar.addActionListener(controller::acaoBotaoEntrar);

        btnVerSenha.addActionListener(e -> {
            if (btnVerSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
                if (iconOlhoAberto != null) {
                    btnVerSenha.setIcon(iconOlhoAberto);
                }
            } else {
                txtSenha.setEchoChar('•');
                if (iconOlhoFechado != null) {
                    btnVerSenha.setIcon(iconOlhoFechado);
                }
            }
        });

        aplicarEfeitoHoverLink(lblCadastrese);
        aplicarEfeitoHoverLink(lblEsqueciSenha);

        lblCadastrese.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ActionEvent dummyEvent = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "cadastrar");
                controller.acaoBotaoCadastrar(dummyEvent);
            }
        });

        lblEsqueciSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.acaoEsqueciSenha();
            }
        });
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

    private JToggleButton criarBotaoVerSenha() {
        JToggleButton botao = new JToggleButton();

        if (iconOlhoFechado != null) {
            botao.setIcon(iconOlhoFechado);
        } else {
            botao.setText("Ver");
        }

        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(40, 40));
        return botao;
    }

    public String getEmail() { return txtEmail.getText(); }
    public String getSenha() { return new String(txtSenha.getPassword()); }
    public String getTipoUsuario() { return rdoUsuario.isSelected() ? "user" : "vendedor"; }
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

    private JPasswordField criarCampoDeSenha() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_CAMPO_TEXTO);
        campo.setForeground(COR_TEXTO_BRANCO);
        campo.setCaretColor(COR_TEXTO_BRANCO);
        campo.setBorder(null);
        campo.setEchoChar('•');
        return campo;
    }

    private JRadioButton criarRadioButton(String texto, boolean selecionado) {
        JRadioButton radio = new JRadioButton(texto, selecionado);
        radio.setFont(FONTE_PADRAO);
        radio.setForeground(COR_TEXTO_BRANCO);
        radio.setOpaque(false);
        radio.setFocusPainted(false);
        return radio;
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
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
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