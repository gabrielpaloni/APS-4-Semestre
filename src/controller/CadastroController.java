package controller;

import model.bean.Usuario;
import model.dao.UsuarioDAO;
import view.TelaCadastro;

public class CadastroController {

    private TelaCadastro view;
    private UsuarioDAO usuarioDAO;

    public CadastroController(TelaCadastro view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
    }

    public void acaoBotaoCadastrar(java.awt.event.ActionEvent e) {
        String nome = view.getNome();
        String email = view.getEmail();
        String senha = view.getSenha();
        String confirmaSenha = view.getConfirmaSenha();
        String tipo = view.getTipoUsuario();

        String tipoBanco = tipo.equals("user") ? "comprador" : "vendedor";

        if (nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()) {
            view.exibirMensagem("Preencha todos os campos.");
            return;
        }
        if (!senha.equals(confirmaSenha)) {
            view.exibirMensagem("As senhas não conferem.");
            return;
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setTipo(tipoBanco);

        boolean sucesso = usuarioDAO.cadastrar(novoUsuario);

        if (sucesso) {
            view.exibirMensagem("Cadastro realizado com sucesso! \nFaça o login.");
            view.dispose();
        } else {
            view.exibirMensagem("Erro ao cadastrar. (O email pode já estar em uso).");
        }
    }
}