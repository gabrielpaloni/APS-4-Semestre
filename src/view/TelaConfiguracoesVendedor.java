package view;

import model.bean.Vendedor;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaConfiguracoesVendedor extends JFrame {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255); // Ciano

    public TelaConfiguracoesVendedor(Vendedor vendedor) {
        setTitle("Configurações da Conta - " + vendedor.getNomeLoja());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setBackground(COR_FUNDO);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("CONFIGURAÇÕES DA CONTA");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(COR_TEXTO);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 20, 5);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        addFormField(panel, gbc, "ID:", String.valueOf(vendedor.getId()), 1);
        addFormField(panel, gbc, "Nome da Loja:", vendedor.getNomeLoja(), 2);
        addFormField(panel, gbc, "Email:", vendedor.getEmail(), 3);
        addFormField(panel, gbc, "Senha:", vendedor.getSenha(), 4);
        addFormField(panel, gbc, "Tipo de Usuário:", vendedor.getTipo(), 5);
        addFormField(panel, gbc, "Data de Cadastro:", vendedor.getDataCadastro(), 6);

        add(panel);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, int yPos) {
        JLabel label = new JLabel(labelText);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.1;
        panel.add(label, gbc);

        JTextField textField = new JTextField(valueText);
        textField.setEditable(false);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        textField.setBackground(COR_PAINEL_FUNDO);
        textField.setForeground(COR_TEXTO);
        textField.setCaretColor(COR_TEXTO);
        Border outsideBorder = new LineBorder(COR_DESTAQUE, 1);
        Border insideBorder = new EmptyBorder(5, 5, 5, 5);
        textField.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        gbc.gridx = 1;
        gbc.gridy = yPos;
        gbc.weightx = 0.9;
        panel.add(textField, gbc);
    }
}

