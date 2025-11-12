package view;

import controller.LojaController;
import model.bean.Jogo;
import model.bean.Usuario;
import view.TelaPrincipalUsuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class TelaCompra extends JDialog {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);
    private static final Color COR_BOTAO_CANCELAR = new Color(75, 75, 75);

    public TelaCompra(TelaPrincipalUsuario parent, Jogo jogo, Usuario usuario, LojaController controller) {
        super(parent, "Confirmar Compra", true);

        setSize(700, 600);
        setLocationRelativeTo(parent);
        setResizable(false);

        String projectRoot = System.getProperty("user.dir");
        String bgPath = projectRoot + java.io.File.separator + "resources" + java.io.File.separator + "user_bg.png";

        Image backgroundImage = null;
        java.io.File bgFile = new java.io.File(bgPath);
        if (bgFile.exists()) {
            backgroundImage = new ImageIcon(bgPath).getImage();
        } else {
            System.err.println("Imagem de fundo não encontrada: " + bgPath);
        }

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(jogo.getNome());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(COR_DESTAQUE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel imgLabel = criarLabelImagem(jogo.getNomeArquivoImagem());
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(imgLabel, gbc);

        JPanel panelDireita = new JPanel();
        panelDireita.setLayout(new BoxLayout(panelDireita, BoxLayout.Y_AXIS));
        panelDireita.setOpaque(false);
        panelDireita.setBorder(new EmptyBorder(0, 10, 0, 0));

        JLabel lblDescTitulo = new JLabel("Descrição");
        lblDescTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblDescTitulo.setForeground(COR_TEXTO);
        lblDescTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDireita.add(lblDescTitulo);
        panelDireita.add(Box.createVerticalStrut(5));

        JTextArea descArea = criarAreaDescricao(jogo.getDescricao());
        JScrollPane scrollDesc = new JScrollPane(descArea);
        scrollDesc.setBorder(null);
        scrollDesc.setOpaque(false);
        scrollDesc.getViewport().setOpaque(false);
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDireita.add(scrollDesc);

        panelDireita.add(Box.createVerticalStrut(15));

        JLabel lblReqTitulo = new JLabel("Requisitos de Sistema");
        lblReqTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblReqTitulo.setForeground(COR_TEXTO);
        lblReqTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDireita.add(lblReqTitulo);
        panelDireita.add(Box.createVerticalStrut(5));

        String requisitos = (jogo.getRequisitosSistema() != null) ? jogo.getRequisitosSistema() : "Requisitos não informados.";
        JTextArea reqArea = criarAreaDescricao(requisitos);
        JScrollPane scrollReq = new JScrollPane(reqArea);
        scrollReq.setBorder(null);
        scrollReq.setOpaque(false);
        scrollReq.getViewport().setOpaque(false);
        scrollReq.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDireita.add(scrollReq);

        panelDireita.add(Box.createVerticalGlue());

        panelDireita.add(Box.createVerticalStrut(10));
        JLabel priceLabel = new JLabel(String.format("Preço: R$ %.2f", jogo.getPreco()));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        priceLabel.setForeground(COR_TEXTO);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDireita.add(priceLabel);

        gbc.gridx = 1; gbc.gridy = 1; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        panel.add(panelDireita, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        JButton btnConfirmar = createStyledButton("Confirmar Compra", COR_DESTAQUE, Color.BLACK);
        JButton btnCancelar = createStyledButton("Cancelar", COR_BOTAO_CANCELAR, COR_TEXTO);

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnConfirmar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0; // Reseta o weighty
        gbc.insets = new Insets(20, 8, 8, 8);
        panel.add(buttonPanel, gbc);

        btnCancelar.addActionListener(e -> dispose());

        btnConfirmar.addActionListener(e -> {
            boolean sucesso = controller.realizarCompra(usuario, jogo);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!\nO jogo foi adicionado à sua biblioteca.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                parent.refreshTelas();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao processar a compra.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }

    private JLabel criarLabelImagem(String nomeImagem) {
        ImageIcon icon = null;
        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            try {
                String projectRoot = System.getProperty("user.dir");
                String absolutePath = projectRoot + java.io.File.separator + "resources" + java.io.File.separator + nomeImagem;

                java.io.File imageFile = new java.io.File(absolutePath);

                if (imageFile.exists()) {
                    icon = new ImageIcon(absolutePath);
                    Image resizedImage = icon.getImage().getScaledInstance(280, 350, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(resizedImage);
                } else {
                    System.err.println("Imagem do jogo não encontrada: " + absolutePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (icon == null) {
            JLabel placeholder = new JLabel("Imagem Indisponível");
            placeholder.setPreferredSize(new Dimension(280, 350));
            return placeholder;
        }

        return new JLabel(icon);
    }

    private JTextArea criarAreaDescricao(String texto) {
        JTextArea area = new JTextArea(texto);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setForeground(COR_TEXTO);
        area.setOpaque(false);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable(false);
        area.setFocusable(false);
        return area;
    }

    private JButton createStyledButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
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