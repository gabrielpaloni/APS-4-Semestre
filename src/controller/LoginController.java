package controller;

import model.bean.Usuario;
import model.dao.UsuarioDAO;
import view.TelaCadastro;
import view.TelaLogin;
import view.TelaPrincipalUsuario;
import view.TelaPrincipalVendedor;
import view.TelaResetSenha;

public class LoginController {

    private TelaLogin view;
    private UsuarioDAO usuarioDAO;

    public LoginController(TelaLogin view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
    }

    public void acaoBotaoEntrar(java.awt.event.ActionEvent e) {
        String email = view.getEmail();
        String senha = view.getSenha();
        String tipo = view.getTipoUsuario();

        String tipoBanco = tipo.equals("user") ? "comprador" : "vendedor";

        if (email.trim().isEmpty() || senha.trim().isEmpty()) {
            view.exibirMensagem("Email e senha são obrigatórios.");
            return;
        }

        try {
            Usuario usuarioLogado = usuarioDAO.validarLogin(email, senha, tipoBanco);

            if (usuarioLogado != null) {
                if (usuarioLogado.getTipo().equals("comprador")) {
                    new TelaPrincipalUsuario(usuarioLogado).setVisible(true);
                } else {
                    new TelaPrincipalVendedor(usuarioLogado).setVisible(true);
                }
                view.dispose();

            } else {
                view.exibirMensagem("Email, senha ou tipo de usuário inválidos.");
            }
        } catch (Exception ex) {
            view.exibirMensagem("Erro ao tentar fazer login: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void acaoBotaoCadastrar(java.awt.event.ActionEvent e) {
        String tipoSelecionado = view.getTipoUsuario();
        new TelaCadastro(tipoSelecionado).setVisible(true);
    }



    public void acaoEsqueciSenha() {
        String tipoSelecionado = view.getTipoUsuario();
        new TelaResetSenha(tipoSelecionado).setVisible(true);
    } 
}