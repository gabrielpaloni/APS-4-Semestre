package view;

import model.bean.Usuario; // <-- MUDE PARA A CLASSE UNIFICADA

import javax.swing.*;

public class TelaPrincipalVendedor extends JFrame {

    private Usuario vendedorLogado;

    public TelaPrincipalVendedor(Usuario usuario) {
        this.vendedorLogado = usuario;

        setTitle("Painel do Vendedor - " + usuario.getNome());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelTemporaria = new JLabel("PAINEL DO VENDEDOR (EM CONSTRUÇÃO)");
        labelTemporaria.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTemporaria);

        setLocationRelativeTo(null);
    }
}