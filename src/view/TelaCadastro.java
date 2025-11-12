package view;

import controller.CadastroController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaCadastro extends JFrame {

<<<<<<< HEAD

=======
    // --- CORES E FONTES UNIFICADAS (DA TELA LOGIN) ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);
    private static final Color COR_TEXTO_LABEL = new Color(180, 180, 180);

    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);

<<<<<<< HEAD

=======
    // --- Componentes ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
    private JTextField txtNome, txtEmail;
    private JPasswordField txtSenha, txtConfirmaSenha;
    private JRadioButton rdoUsuario, rdoVendedor;
    private JButton btnCadastrar;
    private JLabel lblJaTenhoConta;
    private CadastroController controller;

<<<<<<< HEAD
=======
    // --- Ícones ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
    private ImageIcon iconOlhoAberto, iconOlhoFechado, iconRadioOn, iconRadioOff;
    private JToggleButton btnVerSenha, btnVerConfirmaSenha;
    private Image imgBackground;

    public TelaCadastro(String tipoUsuarioInicial) {
        this.controller = new CadastroController(this);

        setTitle("PixelHaus - Crie sua Conta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

<<<<<<< HEAD
        carregarImagens();

        BackgroundPanel painelPrincipal = new BackgroundPanel(imgBackground);
        painelPrincipal.setLayout(new GridBagLayout());
        setContentPane(painelPrincipal);

        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setOpaque(false);
=======
        carregarImagens(); // Carrega todos os ícones e fundo

        // --- ESTRUTURA PRINCIPAL (COMO A TELA LOGIN) ---
        BackgroundPanel painelPrincipal = new BackgroundPanel(imgBackground);
        painelPrincipal.setLayout(new GridBagLayout()); // Layout centralizado
        setContentPane(painelPrincipal);

        // Painel de conteúdo que ficará no centro
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setOpaque(false); // Transparente
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Título ---
        JLabel lblTitulo = new JLabel("Crie sua Conta");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 20, 0);
        painelConteudo.add(lblTitulo, gbc);

        // --- Nome (ou Nome da Loja) ---
        JLabel lblNome = criarLabel("Nome (ou Nome da Loja)");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 0, 2, 0);
        painelConteudo.add(lblNome, gbc);

        txtNome = criarCampoDeTexto();
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(txtNome, gbc);

        // --- Email ---
        JLabel lblEmail = criarLabel("Email");
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 2, 0);
        painelConteudo.add(lblEmail, gbc);

        txtEmail = criarCampoDeTexto();
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(txtEmail, gbc);

        // --- Senha ---
        JLabel lblSenha = criarLabel("Senha");
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 2, 0);
        painelConteudo.add(lblSenha, gbc);

        txtSenha = criarCampoDeSenha();
        btnVerSenha = criarBotaoVerSenha();
        JPanel painelSenha = criarPainelDeSenha(txtSenha, btnVerSenha);
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(painelSenha, gbc);

        // --- Confirmar Senha ---
        JLabel lblConfirmaSenha = criarLabel("Confirmar Senha");
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 2, 0);
        painelConteudo.add(lblConfirmaSenha, gbc);

        txtConfirmaSenha = criarCampoDeSenha();
        btnVerConfirmaSenha = criarBotaoVerSenha();
        JPanel painelConfirmaSenha = criarPainelDeSenha(txtConfirmaSenha, btnVerConfirmaSenha);
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 8, 0);
        painelConteudo.add(painelConfirmaSenha, gbc);

<<<<<<< HEAD
=======
        // --- Botões de Rádio ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        rdoUsuario = criarRadioButton("Usuário", tipoUsuarioInicial.equals("user"));
        rdoVendedor = criarRadioButton("Vendedor", tipoUsuarioInicial.equals("vendedor"));
        ButtonGroup grpTipoUsuario = new ButtonGroup();
        grpTipoUsuario.add(rdoUsuario);
        grpTipoUsuario.add(rdoVendedor);
        JPanel painelRadios = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelRadios.setOpaque(false);
        painelRadios.add(rdoUsuario);
        painelRadios.add(rdoVendedor);
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        painelConteudo.add(painelRadios, gbc);

        // --- Botão Cadastrar ---
        btnCadastrar = new BotaoGradiente("Cadastrar");
        btnCadastrar.setPreferredSize(new Dimension(300, 45));
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        painelConteudo.add(btnCadastrar, gbc);

<<<<<<< HEAD
=======
        // --- Link "Já tenho conta" ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        lblJaTenhoConta = criarLink("Já tenho uma conta. Fazer login.");
        JPanel painelLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelLink.setOpaque(false);
        painelLink.add(lblJaTenhoConta);
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 8, 0);
        painelConteudo.add(painelLink, gbc);
<<<<<<< HEAD
=======

        // Adiciona o painel de conteúdo ao painel principal
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        painelPrincipal.add(painelConteudo, new GridBagConstraints());

        configurarListeners();

<<<<<<< HEAD
        setSize(500, 750);
=======
        setSize(500, 750); // Define um tamanho fixo (maior que o login)
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        setLocationRelativeTo(null);
    }

    private void carregarImagens() {
        try {
<<<<<<< HEAD
=======
            // Imagem de fundo (mesma da tela de login)
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            URL bgUrl = getClass().getClassLoader().getResource("login_bg.png");
            if (bgUrl == null) throw new RuntimeException("Imagem 'login_bg.png' não encontrada.");
            this.imgBackground = new ImageIcon(bgUrl).getImage();

<<<<<<< HEAD
=======
            // Ícones de Olho
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            URL olhoAbertoUrl = getClass().getClassLoader().getResource("olho_aberto.png");
            URL olhoFechadoUrl = getClass().getClassLoader().getResource("olho_fechado.png");
            if (olhoAbertoUrl == null || olhoFechadoUrl == null) throw new RuntimeException("Ícones de olho não encontrados.");
            iconOlhoAberto = redimensionarIcone(new ImageIcon(olhoAbertoUrl), 20, 20);
            iconOlhoFechado = redimensionarIcone(new ImageIcon(olhoFechadoUrl), 20, 20);

<<<<<<< HEAD
=======
            // Ícones de Rádio
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            URL radioOnUrl = getClass().getClassLoader().getResource("radio_on.png");
            URL radioOffUrl = getClass().getClassLoader().getResource("radio_off.png");
            if (radioOnUrl == null || radioOffUrl == null) throw new RuntimeException("Ícones de rádio não encontrados.");
            iconRadioOn = redimensionarIcone(new ImageIcon(radioOnUrl), 16, 16);
            iconRadioOff = redimensionarIcone(new ImageIcon(radioOffUrl), 16, 16);

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

<<<<<<< HEAD
=======
    // --- MÉTODOS DE ESTILO (COMO DA TELA LOGIN) ---

>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
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
        JTextField campo = new JTextField(25); // Tamanho
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
        campo.setBorder(null); // Borda é aplicada no painel container
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        campo.setOpaque(true);
        campo.setEchoChar('•');
        return campo;
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

    private JPanel criarPainelDeSenha(JPasswordField campoSenha, JToggleButton botaoOlho) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setOpaque(false);
        painel.add(campoSenha, BorderLayout.CENTER);
        painel.add(botaoOlho, BorderLayout.EAST);
<<<<<<< HEAD
        painel.setBorder(criarBordaNeonComPadding());
=======
        painel.setBorder(criarBordaNeonComPadding()); // Borda neon!
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        return painel;
    }

    private JRadioButton criarRadioButton(String texto, boolean selecionado) {
        JRadioButton radio = new JRadioButton(texto, selecionado);
        radio.setFont(FONTE_PADRAO);
        radio.setForeground(COR_TEXTO);
        radio.setOpaque(false);
        radio.setFocusPainted(false);

<<<<<<< HEAD
=======
        // Correções que fizemos na TelaLogin
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        radio.setContentAreaFilled(false);
        radio.setBorderPainted(false);

        if (iconRadioOff != null) radio.setIcon(iconRadioOff);
        if (iconRadioOn != null) radio.setSelectedIcon(iconRadioOn);
        return radio;
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

<<<<<<< HEAD
=======
    // --- Listeners (semelhante ao que já existia) ---
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
    private void configurarListeners() {
        btnCadastrar.addActionListener(controller::acaoBotaoCadastrar);

        configurarBotaoVerSenha(btnVerSenha, txtSenha);
        configurarBotaoVerSenha(btnVerConfirmaSenha, txtConfirmaSenha);

        lblJaTenhoConta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose(); // Apenas fecha a tela de cadastro
            }
        });
    }

    private void configurarBotaoVerSenha(JToggleButton botao, JPasswordField campo) {
        botao.addActionListener(e -> {
            if (botao.isSelected()) {
                campo.setEchoChar((char) 0);
                if (iconOlhoAberto != null) botao.setIcon(iconOlhoAberto);
            } else {
                campo.setEchoChar('•');
                if (iconOlhoFechado != null) botao.setIcon(iconOlhoFechado);
            }
        });
    }

    // --- Getters (sem alteração) ---
    public String getNome() { return txtNome.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getSenha() { return new String(txtSenha.getPassword()); }
    public String getConfirmaSenha() { return new String(txtConfirmaSenha.getPassword()); }
    public String getTipoUsuario() { return rdoUsuario.isSelected() ? "user" : "vendedor"; }
    public void exibirMensagem(String mensagem) { JOptionPane.showMessageDialog(this, mensagem); }

<<<<<<< HEAD
=======
    // --- Inner class BotaoGradiente (sem alteração) ---
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
<<<<<<< HEAD
=======

    // --- Inner class BackgroundPanel (Adicionada) ---
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