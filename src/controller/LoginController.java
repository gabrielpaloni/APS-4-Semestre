package controller;

import model.bean.Usuario;
import model.bean.Vendedor;
import model.dao.JogoDAO;
import model.dao.UsuarioDAO;
import model.dao.VendedorDAO;
import view.TelaCadastro;
import view.TelaLogin;
import view.TelaPrincipalUsuario;
import view.TelaPrincipalVendedor;
import view.TelaResetSenha;

import java.util.List;

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
        String tipo = view.getTipoUsuario();

        if (camposInvalidos(email, senha)) {
            view.exibirMensagem("Email e senha são obrigatórios.");
            return;
        }

        try {
            if (tipo.equals("user")) {
                String tipoBanco = "comprador";
                autenticarUsuario(email, senha, tipoBanco);
            } else {
                autenticarVendedor(email, senha);
            }
        } catch (Exception ex) {
            view.exibirMensagem("Erro ao tentar fazer login: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean camposInvalidos(String email, String senha) {
        return email == null || email.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty();
    }
    private void autenticarUsuario(String email, String senha, String tipoBanco) {
        Usuario usuario = usuarioDAO.validarLogin(email, senha, tipoBanco);

        if (usuario != null) {
            new TelaPrincipalUsuario(usuario).setVisible(true);
            view.dispose();
        } else {
            view.exibirMensagem("Email, senha ou tipo de usuário inválidos.");
        }
    }

    private void autenticarVendedor(String email, String senha) {

        Vendedor vendedor = vendedorDAO.validarLogin(email, senha);

        if (vendedor != null) {
            TelaPrincipalVendedor telaVendedor = new TelaPrincipalVendedor(vendedor);

            JogoDAO jogoDAO = new JogoDAO();
            List<model.bean.Jogo> jogosDoVendedor = jogoDAO.listarPorVendedor(vendedor.getId());
            telaVendedor.atualizarCatalogo(jogosDoVendedor);
            telaVendedor.setVisible(true);

            view.dispose();
        } else {
            view.exibirMensagem("Email, senha ou tipo de usuário inválidos.");
        }
    }

    public void acaoBotaoCadastrar(java.awt.event.ActionEvent e) {
        new TelaCadastro(view.getTipoUsuario()).setVisible(true);
    }

    public void acaoEsqueciSenha() {
        new TelaResetSenha(view.getTipoUsuario()).setVisible(true);
    }
}