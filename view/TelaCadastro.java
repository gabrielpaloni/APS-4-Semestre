package view;

import controller.CadastroController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory; // Import que faltava

public class TelaCadastro extends JFrame {

    // --- Constantes de Estilo ---
    private static final Color COR_FUNDO_ESCURO = new Color(18, 18, 18);
    private static final Color COR_CAMPO_TEXTO = new Color(31, 31, 31);
    private static final Color COR_AZUL_DESTAQUE = new Color(0, 122, 255);
    private static final Color COR_TEXTO_BRANCO = new Color(240, 240, 240);
    private static final Color COR_TEXTO_CINZA = new Color(160, 160, 160);
    private static final Color COR_BORDA_PADRAO = new Color(80, 80, 80); // Cor que faltava
    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    // --------------------------------------------------------

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JRadioButton rdoUsuario;
    private JRadioButton rdoVendedor;
    private JButton btnCadastrar;
    private JLabel lblJaTenhoConta;
    private CadastroController controller;

    // --- VARIÁVEIS ADICIONADAS ---
    private ImageIcon iconOlhoAberto;
    private ImageIcon iconOlhoFechado;
    private JToggleButton btnVerSenha;
    private JToggleButton btnVerConfirmaSenha;
    // ----------------------------

    public TelaCadastro(String tipoUsuarioInicial) {
        this.controller = new CadastroController(this);

        setBackground(COR_FUNDO_ESCURO);
        setTitle("PixelHaus - Cadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(COR_FUNDO_ESCURO);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));
        setContentPane(painelPrincipal);

        // --- CARREGAMENTO DOS ÍCONES ADICIONADO ---
        try {
            iconOlhoAberto = new ImageIcon(getClass().getResource("/resources/olho_aberto.png"));
            iconOlhoFechado = new ImageIcon(getClass().getResource("/resources/olho_fechado.png"));
        } catch (Exception e) {
            System.err.println("Erro ao carregar os ícones de olho: " + e.getMessage());
            iconOlhoAberto = null;
            iconOlhoFechado = null;
        }
        // ---------------------------------------

        JLabel lblTitulo = new JLabel("Crie sua Conta");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_TEXTO_BRANCO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = criarLabel("Nome (ou Nome da Loja)");
        txtNome = criarCampoDeTexto();

        JLabel lblEmail = criarLabel("Email");
        txtEmail = criarCampoDeTexto();

        // --- PAINEL DE SENHA (CAMPO + BOTÃO) ---
        JLabel lblSenha = criarLabel("Senha");
        txtSenha = criarCampoDeSenha();
        btnVerSenha = criarBotaoVerSenha();

        JPanel painelSenha = new JPanel(new BorderLayout(0, 0));
        painelSenha.setOpaque(true);
        painelSenha.setBackground(COR_CAMPO_TEXTO);
        painelSenha.add(txtSenha, BorderLayout.CENTER);
        painelSenha.add(btnVerSenha, BorderLayout.EAST);
        painelSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_PADRAO),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)
        ));
        // ---------------------------------------

        // --- PAINEL DE CONFIRMAR SENHA (CAMPO + BOTÃO) ---
        JLabel lblConfirmaSenha = criarLabel("Confirmar Senha");
        txtConfirmaSenha = criarCampoDeSenha();
        btnVerConfirmaSenha = criarBotaoVerSenha();

        JPanel painelConfirmaSenha = new JPanel(new BorderLayout(0, 0));
        painelConfirmaSenha.setOpaque(true);
        painelConfirmaSenha.setBackground(COR_CAMPO_TEXTO);
        painelConfirmaSenha.add(txtConfirmaSenha, BorderLayout.CENTER);
        painelConfirmaSenha.add(btnVerConfirmaSenha, BorderLayout.EAST);
        painelConfirmaSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_PADRAO),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)
        ));
        // ---------------------------------------

        rdoUsuario = criarRadioButton("Usuário", tipoUsuarioInicial.equals("user"));
        rdoVendedor = criarRadioButton("Vendedor", tipoUsuarioInicial.equals("vendedor"));

        ButtonGroup grpTipoUsuario = new ButtonGroup();
        grpTipoUsuario.add(rdoUsuario);
        grpTipoUsuario.add(rdoVendedor);

        JPanel painelRadios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelRadios.setOpaque(false);
        painelRadios.add(rdoUsuario);
        painelRadios.add(Box.createHorizontalStrut(20));
        painelRadios.add(rdoVendedor);

        btnCadastrar = new BotaoGradiente("Cadastrar");

        lblJaTenhoConta = new JLabel("Já tenho uma conta. Fazer login.");
        lblJaTenhoConta.setFont(FONTE_PADRAO);
        lblJaTenhoConta.setForeground(COR_TEXTO_CINZA);
        lblJaTenhoConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Layout
        GroupLayout layout = new GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        // ###############################################################
        // ### AQUI ESTÁ A CORREÇÃO ###
        // ###############################################################
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo, GroupLayout.Alignment.LEADING)
                        .addComponent(lblNome, GroupLayout.Alignment.LEADING)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmail, GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail)
                        .addComponent(lblSenha, GroupLayout.Alignment.LEADING)
                        // LINHA CORRIGIDA (usa o painel, não o txt)
                        .addComponent(painelSenha, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblConfirmaSenha, GroupLayout.Alignment.LEADING)
                        // LINHA CORRIGIDA (usa o painel, não o txt)
                        .addComponent(painelConfirmaSenha, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(painelRadios, GroupLayout.Alignment.LEADING)
                        .addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblJaTenhoConta)
        );
        // ###############################################################
        // ### FIM DA CORREÇÃO ###
        // ###############################################################


        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(30)
                        .addComponent(lblNome)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblEmail)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblSenha)
                        .addComponent(painelSenha, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblConfirmaSenha)
                        .addComponent(painelConfirmaSenha, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(painelRadios)
                        .addGap(30)
                        .addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(lblJaTenhoConta)
        );

        configurarListeners();

        pack(); // Esta é a linha 170 do seu erro. Agora ela funcionará.
        setLocationRelativeTo(null);
    }

    private void configurarListeners() {
        btnCadastrar.addActionListener(controller::acaoBotaoCadastrar);
        aplicarEfeitoHoverLink(lblJaTenhoConta);

        // --- LISTENERS DOS BOTÕES DE OLHO ADICIONADOS ---
        configurarBotaoVerSenha(btnVerSenha, txtSenha);
        configurarBotaoVerSenha(btnVerConfirmaSenha, txtConfirmaSenha);
        // ---------------------------------------------

        lblJaTenhoConta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
            }
        });
    }

    // --- NOVO MÉTODO HELPER ADICIONADO ---
    private void configurarBotaoVerSenha(JToggleButton botao, JPasswordField campo) {
        botao.addActionListener(e -> {
            if (botao.isSelected()) {
                campo.setEchoChar((char) 0); // Mostrar senha
                if (iconOlhoAberto != null) {
                    botao.setIcon(iconOlhoAberto);
                }
            } else {
                campo.setEchoChar('•'); // Ocultar senha
                if (iconOlhoFechado != null) {
                    botao.setIcon(iconOlhoFechado);
                }
            }
        });
    }
    // ------------------------------------

    // Getters para o controller
    public String getNome() { return txtNome.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getSenha() { return new String(txtSenha.getPassword()); }
    public String getConfirmaSenha() { return new String(txtConfirmaSenha.getPassword()); }
    public String getTipoUsuario() { return rdoUsuario.isSelected() ? "user" : "vendedor"; }
    public void exibirMensagem(String mensagem) { JOptionPane.showMessageDialog(this, mensagem); }


    // --- Métodos de Estilo (copiados da sua TelaLogin) ---
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

    // --- MÉTODO MODIFICADO (sem borda) ---
    private JPasswordField criarCampoDeSenha() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_CAMPO_TEXTO);
        campo.setForeground(COR_TEXTO_BRANCO);
        campo.setCaretColor(COR_TEXTO_BRANCO);
        campo.setBorder(null); // <-- Borda removida
        campo.setEchoChar('•');
        return campo;
    }
    // ------------------------------------

    // --- NOVO MÉTODO ADICIONADO (copiado da TelaLogin) ---
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
    // ----------------------------------------------------

    private JRadioButton criarRadioButton(String texto, boolean selecionado) {
        JRadioButton radio = new JRadioButton(texto, selecionado);
        radio.setFont(FONTE_PADRAO);
        radio.setForeground(COR_TEXTO_BRANCO);
        radio.setOpaque(false);
        radio.setFocusPainted(false);
        return radio;
    }

    // Classe interna para o botão (copiada da sua TelaLogin)
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
}