package view;

import model.bean.Usuario;
import javax.swing.*;

public class TelaPrincipalUsuario extends JFrame {

    private Usuario usuarioLogado;

    public TelaPrincipalUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;

        setTitle("Loja - Bem-vindo(a) " + usuario.getNome());
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelTemporaria = new JLabel("LOJA ESTILO STEAM (EM CONSTRUÇÃO)");
        labelTemporaria.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTemporaria);

        setLocationRelativeTo(null);
    }
}
