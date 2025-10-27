package controller;

import model.bean.Usuario;
import model.bean.Vendedor;
import model.dao.JogoDAO; // Importação necessária
import model.dao.UsuarioDAO;
import model.dao.VendedorDAO;
import view.TelaCadastro;
import view.TelaLogin;
import view.TelaPrincipalUsuario;
import view.TelaPrincipalVendedor;
import view.TelaResetSenha;

import java.util.List; // Importação necessária

public class LoginController {

    private TelaLogin view;
    private UsuarioDAO usuarioDAO;
    private VendedorDAO vendedorDAO;

    public LoginController(TelaLogin view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        this.vendedorDAO = new VendedorDAO();
    }

    public void acaoBotaoEntrar(java.awt.event.ActionEvent e) {
        String email = view.getEmail();
        String senha = view.getSenha();

        String tipoUsuario = view.getTipoUsuario();

        if (camposInvalidos(email, senha)) {
            view.exibirMensagem("Email e senha são obrigatórios.");
            return;
        }

        try {
            if (tipoUsuario.equals("user")) {
                autenticarUsuario(email, senha, tipoUsuario);
            } else {
                autenticarVendedor(email, senha);
            }
        } catch (Exception ex) {
            view.exibirMensagem("Erro ao tentar fazer login: " + ex.getMessage());
        }
    }

    private boolean camposInvalidos(String email, String senha) {
        return email == null || email.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty();
    }

    private void autenticarUsuario(String email, String senha, String tipo) {
        Usuario usuario = usuarioDAO.validarLogin(email, senha, tipo);

        if (usuario != null) {
            new TelaPrincipalUsuario(usuario).setVisible(true);
            view.dispose();
        } else {
            view.exibirMensagem("Email ou senha inválidos.");
        }
    }

    // Este é o método que alteramos (e removemos a duplicata)
    private void autenticarVendedor(String email, String senha) {
        Vendedor vendedor = vendedorDAO.validarLogin(email, senha);

        if (vendedor != null) {
            // --- INÍCIO DA LÓGICA DE CARREGAMENTO ---

            // 1. Cria a instância da tela
            TelaPrincipalVendedor telaVendedor = new TelaPrincipalVendedor(vendedor);

            // 2. Cria uma instância do JogoDAO
            JogoDAO jogoDAO = new JogoDAO();

            // 3. Busca a lista de jogos específica deste vendedor
            List<model.bean.Jogo> jogosDoVendedor = jogoDAO.listarPorVendedor(vendedor.getId());

            // 4. Manda a tela se preencher com a lista de jogos
            telaVendedor.atualizarCatalogo(jogosDoVendedor);

            // 5. Agora, torna a tela visível
            telaVendedor.setVisible(true);

            // --- FIM DA LÓGICA ---

            view.dispose();
        } else {
            view.exibirMensagem("Email ou senha inválidos.");
        }
    }

    public void acaoBotaoCadastrar(java.awt.event.ActionEvent e) {
        new TelaCadastro(view.getTipoUsuario()).setVisible(true);
    }

    public void acaoEsqueciSenha() {
        new TelaResetSenha(view.getTipoUsuario()).setVisible(true);
    }
}