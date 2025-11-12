package view;

import controller.LoginController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaLogin extends JFrame {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);
    private static final Color COR_BOTAO_GRAD_1 = new Color(0, 122, 255);
    private static final Color COR_BOTAO_GRAD_2 = new Color(0, 199, 255);
    private static final Color COR_TEXTO_LABEL = new Color(180, 180, 180);

    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JToggleButton btnVerSenha;
    private JRadioButton rdoUsuario;
    private JRadioButton rdoVendedor;
    private JButton btnEntrar;
    private JLabel lblCadastrese;
    private JLabel lblEsqueciSenha;
    private LoginController controller;
    private ImageIcon iconOlhoAberto, iconOlhoFechado, iconRadioOn, iconRadioOff;
    private Image imgBackground;

    public TelaLogin() {
        setTitle("PixelHaus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        carregarImagens();

        BackgroundPanel painelPrincipal = new BackgroundPanel(imgBackground);
        painelPrincipal.setLayout(new GridBagLayout());
        setContentPane(painelPrincipal);

        controller = new LoginController(this);

        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Bem Vindo a PixelHaus");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 20, 0);
        painelConteudo.add(lblTitulo, gbc);

        JLabel lblEmail = criarLabel("Email");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 0, 2, 0);
        painelConteudo.add(lblEmail, gbc);

        txtEmail = criarCampoDeTexto();
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(txtEmail, gbc);

        // Label de Senha
        JLabel lblSenha = criarLabel("Senha");
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 2, 0);
        painelConteudo.add(lblSenha, gbc);

<<<<<<< HEAD
=======
        // Campo de Senha com botão de olho
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        txtSenha = criarCampoDeSenha();
        btnVerSenha = criarBotaoVerSenha();
        JPanel painelSenhaComBotao = new JPanel(new BorderLayout());
        painelSenhaComBotao.setOpaque(false);
        painelSenhaComBotao.add(txtSenha, BorderLayout.CENTER);
        painelSenhaComBotao.add(btnVerSenha, BorderLayout.EAST);
        painelSenhaComBotao.setBorder(criarBordaNeonComPadding());
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(painelSenhaComBotao, gbc);

<<<<<<< HEAD
=======
        // Botões de Rádio
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        rdoUsuario = criarRadioButton("Usuário", true);
        rdoVendedor = criarRadioButton("Vendedor", false);
        ButtonGroup grpTipoUsuario = new ButtonGroup();
        grpTipoUsuario.add(rdoUsuario);
        grpTipoUsuario.add(rdoVendedor);
        JPanel painelRadios = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelRadios.setOpaque(false);
        painelRadios.add(rdoUsuario);
        painelRadios.add(rdoVendedor);
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        painelConteudo.add(painelRadios, gbc);

        // Botão Entrar
        btnEntrar = new BotaoGradiente("Entrar");
        btnEntrar.setPreferredSize(new Dimension(300, 45));
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        painelConteudo.add(btnEntrar, gbc);

<<<<<<< HEAD
=======
        // Links
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        lblCadastrese = criarLink("Cadastre-se");
        lblEsqueciSenha = criarLink("Esqueci minha senha");
        JPanel painelLinks = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelLinks.setOpaque(false);
        painelLinks.add(lblCadastrese);
        painelLinks.add(lblEsqueciSenha);
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 8, 0);
        painelConteudo.add(painelLinks, gbc);

        painelPrincipal.add(painelConteudo, new GridBagConstraints());

        configurarListeners();

        setSize(500, 600);
        setLocationRelativeTo(null);
    }

    private void carregarImagens() {
        try {
<<<<<<< HEAD
=======
            // 1. Use o ClassLoader para procurar a partir da raiz do classpath.
            // 2. Use APENAS o nome do arquivo.
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410

            URL bgUrl = getClass().getClassLoader().getResource("login_bg.png");
            URL olhoAbertoUrl = getClass().getClassLoader().getResource("olho_aberto.png");
            URL olhoFechadoUrl = getClass().getClassLoader().getResource("olho_fechado.png");
            URL radioOnUrl = getClass().getClassLoader().getResource("radio_on.png");
            URL radioOffUrl = getClass().getClassLoader().getResource("radio_off.png");

<<<<<<< HEAD
            if (bgUrl == null || olhoAbertoUrl == null || olhoFechadoUrl == null || radioOnUrl == null || radioOffUrl == null) {

                throw new RuntimeException("Falha ao carregar um or mais recursos de imagem. Verifique os nomes dos arquivos na pasta 'resources'.");
            }

=======
            // 3. Verificação de Nulo: Esta é a parte mais importante
            //    Se alguma URL for nula, o arquivo não foi encontrado.
            if (bgUrl == null || olhoAbertoUrl == null || olhoFechadoUrl == null || radioOnUrl == null || radioOffUrl == null) {
                // Lança um erro claro no console
                throw new RuntimeException("Falha ao carregar um or mais recursos de imagem. Verifique os nomes dos arquivos na pasta 'resources'.");
            }

            // 4. Agora que sabemos que os arquivos existem, podemos criar os ícones
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            imgBackground = new ImageIcon(bgUrl).getImage();
            iconOlhoAberto = new ImageIcon(olhoAbertoUrl);
            iconOlhoFechado = new ImageIcon(olhoFechadoUrl);
            iconRadioOn = new ImageIcon(radioOnUrl);
            iconRadioOff = new ImageIcon(radioOffUrl);

<<<<<<< HEAD
=======
            // 5. Redimensionamento
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            iconOlhoAberto = redimensionarIcone(iconOlhoAberto, 20, 20);
            iconOlhoFechado = redimensionarIcone(iconOlhoFechado, 20, 20);
            iconRadioOn = redimensionarIcone(iconRadioOn, 16, 16);
            iconRadioOff = redimensionarIcone(iconRadioOff, 16, 16);

        } catch (Exception e) {
            System.err.println("Erro ao carregar imagens: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private ImageIcon redimensionarIcone(ImageIcon icon, int w, int h) {
        if (icon == null) return null;
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void configurarListeners() {
        btnEntrar.addActionListener(controller::acaoBotaoEntrar);
        btnVerSenha.addActionListener(e -> {
            if (btnVerSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
                if (iconOlhoAberto != null) btnVerSenha.setIcon(iconOlhoAberto);
            } else {
                txtSenha.setEchoChar('•');
                if (iconOlhoFechado != null) btnVerSenha.setIcon(iconOlhoFechado);
            }
        });
        lblCadastrese.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.acaoBotaoCadastrar(new ActionEvent(e.getSource(), e.getID(), "cadastrar"));
            }
        });
        lblEsqueciSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipoSelecionado = rdoVendedor.isSelected() ? "vendedor" : "user";
                new TelaResetSenha(tipoSelecionado).setVisible(true);
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
        botao.setPreferredSize(new Dimension(30, 30));
        return botao;
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
        JTextField campo = new JTextField();
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_FUNDO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_DESTAQUE);
        campo.setBorder(criarBordaNeonComPadding());
        campo.setOpaque(true);
        return campo;
    }

    private JPasswordField criarCampoDeSenha() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_FUNDO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_DESTAQUE);
<<<<<<< HEAD
        campo.setBorder(null);
=======
        campo.setBorder(null); // Borda aplicada no painel container
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        campo.setOpaque(true);
        campo.setEchoChar('•');
        return campo;
    }

    private JRadioButton criarRadioButton(String texto, boolean selecionado) {
        JRadioButton radio = new JRadioButton(texto, selecionado);
        radio.setFont(FONTE_PADRAO);
        radio.setForeground(COR_TEXTO);
        radio.setOpaque(false);
        radio.setFocusPainted(false);
        if (iconRadioOff != null) radio.setIcon(iconRadioOff);
        if (iconRadioOn != null) radio.setSelectedIcon(iconRadioOn);
        return radio;
    }

<<<<<<< HEAD
=======
    // Getters
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
    public String getEmail() {
        return txtEmail.getText();
    }

    public String getSenha() {
        return String.valueOf(txtSenha.getPassword());
    }

    public String getTipoUsuario() {
        return rdoUsuario.isSelected() ? "user" : "vendedor";
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

<<<<<<< HEAD
=======
    // Inner class BotaoGradiente
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
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
            Color color1 = COR_BOTAO_GRAD_1;
            Color color2 = COR_BOTAO_GRAD_2;
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

<<<<<<< HEAD
=======
    // Inner class BackgroundPanel
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
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