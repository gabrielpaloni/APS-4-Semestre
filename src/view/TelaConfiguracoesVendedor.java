package view;

import model.bean.Vendedor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class TelaConfiguracoesVendedor extends JDialog {

    private Vendedor vendedor;
    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    private JTextField idField, nomeLojaField, emailField, tipoField, dataField;
    private JTextField senhaField;
    private JButton voltarButton;
    private TelaPrincipalVendedor telaPrincipal;

    public TelaConfiguracoesVendedor(TelaPrincipalVendedor parent, Vendedor vendedor) {
        super(parent, "Configurações da Conta", true);

        this.telaPrincipal = parent;
        this.vendedor = vendedor;

        Image backgroundImage;
        try {
            URL bgUrl = getClass().getClassLoader().getResource("config_bg.png");
            if (bgUrl == null) {
                throw new RuntimeException("Imagem 'config_bg.png' não encontrada! Verifique o nome na pasta 'resources'.");
            }
            backgroundImage = new ImageIcon(bgUrl).getImage();

        } catch (Exception e) {
            e.printStackTrace();
            backgroundImage = null;
        }

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(COR_TEXTO);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        idField = createStyledTextField(String.valueOf(vendedor.getId()));
        idField.setFocusable(false);
        addFormField(panel, gbc, "ID:", idField, 1);

        nomeLojaField = createStyledTextField(vendedor.getNomeLoja());
        nomeLojaField.setFocusable(false);
        addFormField(panel, gbc, "Nome da Loja:", nomeLojaField, 2);

        emailField = createStyledTextField(vendedor.getEmail());
        emailField.setFocusable(false);
        addFormField(panel, gbc, "Email:", emailField, 3);

        senhaField = createStyledTextField(vendedor.getSenha());
        senhaField.setFocusable(false);
        addFormField(panel, gbc, "Senha:", senhaField, 4);

        tipoField = createStyledTextField(vendedor.getTipo());
        tipoField.setFocusable(false);
        addFormField(panel, gbc, "Tipo de Usuário:", tipoField, 5);

        dataField = createStyledTextField(vendedor.getDataCadastro());
        dataField.setFocusable(false);
        addFormField(panel, gbc, "Data de Cadastro:", dataField, 6);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        voltarButton = createStyledButton("Voltar", COR_DESTAQUE, Color.BLACK);
        buttonPanel.add(voltarButton);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 8, 8, 8);
        panel.add(buttonPanel, gbc);

        voltarButton.addActionListener(e -> dispose());

        add(panel);
        pack();
        setSize(700, 600);
        setLocationRelativeTo(parent);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int yPos) {
        JLabel label = new JLabel(labelText);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.1;
        panel.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.9;
        panel.add(field, gbc);
    }

    private JTextField createStyledTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setEditable(false);

        textField.setBackground(COR_PAINEL_FUNDO);
        textField.setForeground(COR_TEXTO);
        textField.setCaretColor(COR_DESTAQUE);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField.setPreferredSize(new Dimension(350, 30));
        Border outsideBorder = new LineBorder(COR_DESTAQUE, 1);
        Border insideBorder = new EmptyBorder(5, 5, 5, 5);
        textField.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        return textField;
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
}