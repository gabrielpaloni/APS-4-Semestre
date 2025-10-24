package view;

import model.bean.Usuario; // <-- MUDE PARA A CLASSE UNIFICADA

import javax.swing.*;

public class TelaPrincipalVendedor extends JFrame {

    private Usuario vendedorLogado; // <-- MUDE O TIPO DA VARIÁVEL

    // O construtor agora aceita um 'Usuario'
    public TelaPrincipalVendedor(Usuario usuario) {
        this.vendedorLogado = usuario;

        // Usamos o 'getNome()' pois 'getNomeLoja()' não existe mais
        setTitle("Painel do Vendedor - " + usuario.getNome());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Seu código do painel do vendedor aqui...

        JLabel labelTemporaria = new JLabel("PAINEL DO VENDEDOR (EM CONSTRUÇÃO)");
        labelTemporaria.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTemporaria);

        setLocationRelativeTo(null);
    }
}