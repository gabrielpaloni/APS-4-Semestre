package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogNovaSenha extends JDialog {

    // As mesmas constantes de estilo da TelaResetSenha
    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);
    private static final Color COR_TEXTO_CINZA = new Color(160, 160, 160);
    private static final Color COR_BORDA_PADRAO = new Color(80, 80, 80);
    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);

    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmaSenha;
    private JLabel lblErro;
    private JButton btnConfirmar, btnCancelar;

    private String novaSenha = null; // Armazena a senha se o usuário confirmar

    public DialogNovaSenha(JFrame parent) {
        // 'true' o torna "modal" (bloqueia a janela pai, como um JOptionPane)
        super(parent, "Digite a Nova Senha", true);

        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarListeners();
    }

    // Método público para o controller pegar a senha
    public String getNovaSenha() {
        return novaSenha;
    }

    private void configurarJanela() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true); // Tira a barra de título padrão

        // Cria um painel principal com a cor de fundo e uma borda de destaque
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(COR_PAINEL_FUNDO);
        painelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_DESTAQUE, 1), // Borda externa de destaque
                new EmptyBorder(20, 20, 20, 20)  // Padding interno
        ));
        setContentPane(painelPrincipal);
    }

    private void inicializarComponentes() {
        txtNovaSenha = criarCampoDeSenha();
        txtConfirmaSenha = criarCampoDeSenha();

        // Copia o BotaoGradiente da TelaLogin/TelaResetSenha
        btnConfirmar = new BotaoGradiente("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(120, 40));

        // Botão de cancelar (estilo mais simples)
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(FONTE_PADRAO);
        btnCancelar.setForeground(COR_TEXTO_CINZA);
        btnCancelar.setBackground(COR_PAINEL_FUNDO);
        btnCancelar.setBorder(new LineBorder(COR_BORDA_PADRAO, 1));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.setPreferredSize(new Dimension(100, 40));

        btnCancelar.setFocusPainted(false);
        btnCancelar.setContentAreaFilled(false); // Para não desenhar o fundo padrão
        btnCancelar.setOpaque(true); // Precisamos que seja opaco para pintar nosso fundo

        // Adiciona o efeito Hover
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Ao entrar: Fundo Vermelho, Texto Branco
                btnCancelar.setBackground(new Color(210, 50, 50)); // Um vermelho escuro
                btnCancelar.setForeground(Color.WHITE);
                btnCancelar.setBorder(new LineBorder(new Color(255, 100, 100))); // Borda vermelha clara
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Ao sair: Volta ao normal
                btnCancelar.setBackground(COR_PAINEL_FUNDO);
                btnCancelar.setForeground(COR_TEXTO_CINZA);
                btnCancelar.setBorder(new LineBorder(COR_BORDA_PADRAO, 1));
            }
        });

        lblErro = new JLabel(" "); // Label de erro (começa vazia)
        lblErro.setFont(FONTE_LABEL);
        lblErro.setForeground(Color.RED);
    }

    private void configurarLayout() {
        Container painel = getContentPane();
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("Digite a Nova Senha");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COR_TEXTO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 15, 5); // Mais margem abaixo
        painel.add(lblTitulo, gbc);

        // Reset gbc
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(criarLabel("Nova Senha:"), gbc);

        gbc.gridy = 2;
        painel.add(criarLabel("Confirmar Senha:"), gbc);

        // Campos de Texto
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(txtNovaSenha, gbc);

        gbc.gridy = 2;
        painel.add(txtConfirmaSenha, gbc);

        // Label de Erro
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        painel.add(lblErro, gbc);

        // Painel de Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnConfirmar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15, 5, 5, 5); // Mais margem acima
        painel.add(painelBotoes, gbc);

        pack();
        setLocationRelativeTo(getParent()); // Centraliza na janela pai
    }

    private void configurarListeners() {
        // Ação do Botão Confirmar
        btnConfirmar.addActionListener(e -> {
            String senha1 = new String(txtNovaSenha.getPassword());
            String senha2 = new String(txtConfirmaSenha.getPassword());

            if (senha1.isEmpty() || senha2.isEmpty()) {
                lblErro.setText("Os campos não podem estar vazios.");
                return;
            }
            if (!senha1.equals(senha2)) {
                lblErro.setText("As senhas não conferem.");
                return;
            }

            // Sucesso! Armazena a senha e fecha o diálogo
            this.novaSenha = senha1;
            dispose();
        });

        // Ação do Botão Cancelar
        btnCancelar.addActionListener(e -> {
            this.novaSenha = null; // Garante que a senha é nula
            dispose();
        });
    }

    // --- Métodos de Estilo (copiados da TelaResetSenha) ---

    private JPasswordField criarCampoDeSenha() {
        JPasswordField campo = new JPasswordField(20); // Tamanho
        campo.setFont(FONTE_PADRAO);
        campo.setBackground(COR_FUNDO); // Cor de fundo mais escura
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_DESTAQUE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_PADRAO),
                BorderFactory.createEmptyBorder(8, 10, 8, 10) // Padding
        ));
        campo.setEchoChar('•');
        return campo;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_LABEL);
        label.setForeground(COR_TEXTO_CINZA);
        return label;
    }

    // Copie a classe BotaoGradiente para cá
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