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
        String tipo = view.getTipoUsuario(); // "user" ou "vendedor"

        // Converte o tipo da view ("user") para o tipo do banco ("comprador")
        String tipoBanco = tipo.equals("user") ? "comprador" : "vendedor";

        if (email.trim().isEmpty() || senha.trim().isEmpty()) {
            view.exibirMensagem("Email e senha são obrigatórios.");
            return;
        }

        try {
            // --- ESTA É A CHAMADA CORRETA ---
            // Chama o DAO com 3 argumentos
            Usuario usuarioLogado = usuarioDAO.validarLogin(email, senha, tipoBanco);

            if (usuarioLogado != null) {
                // Sucesso! Agora decidimos qual tela abrir
                if (usuarioLogado.getTipo().equals("comprador")) {
                    // (Certifique-se que sua TelaPrincipalUsuario aceita um objeto Usuario)
                    new TelaPrincipalUsuario(usuarioLogado).setVisible(true);
                } else {
                    // (Certifique-se que sua TelaPrincipalVendedor aceita um objeto Usuario)
                    new TelaPrincipalVendedor(usuarioLogado).setVisible(true);
                }
                view.dispose();

            } else {
                // DAO retornou null (email, senha ou tipo errados)
                view.exibirMensagem("Email, senha ou tipo de usuário inválidos.");
            }
        } catch (Exception ex) {
            view.exibirMensagem("Erro ao tentar fazer login: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // O seu controller antigo não tinha a ação de cadastro correta
    public void acaoBotaoCadastrar(java.awt.event.ActionEvent e) {
        String tipoSelecionado = view.getTipoUsuario();
        new TelaCadastro(tipoSelecionado).setVisible(true);
    }



    public void acaoEsqueciSenha() {
        // Pega o tipo de usuário selecionado ("user" ou "vendedor")
        String tipoSelecionado = view.getTipoUsuario();
        // Abre a nova tela de redefinição
        new TelaResetSenha(tipoSelecionado).setVisible(true);
    } 
}