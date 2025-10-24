package view;

import model.bean.Usuario;
import javax.swing.*;

public class TelaPrincipalUsuario extends JFrame {

    private Usuario usuarioLogado;
    // private LojaController controller; // O controller desta tela

    public TelaPrincipalUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;

        setTitle("Loja - Bem-vindo(a) " + usuario.getNome());
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- TODO: Esta é a parte principal do seu trabalho ---
        //
        // 1. Adicione um JScrollPane.
        // 2. Dentro dele, um JPanel (vamos chamar de 'painelLoja').
        // 3. Chame o LojaController.
        // 4. O LojaController vai usar o JogoDAO.listarTodos().
        // 5. Para cada Jogo na lista, crie um "card" (um JPanel menor)
        //    com o nome (JLabel), preço (JLabel) e um botão "Comprar".
        // 6. Adicione todos esses "cards" ao 'painelLoja'.
        //
        // --------------------------------------------------------

        JLabel labelTemporaria = new JLabel("LOJA ESTILO STEAM (EM CONSTRUÇÃO)");
        labelTemporaria.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTemporaria);

        setLocationRelativeTo(null);
    }
}
