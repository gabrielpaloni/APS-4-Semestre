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
        String tipo = view.getTipoUsuario(); // "user" ou "vendedor"

        // Converte para o padrão do banco ("comprador" ou "vendedor")
        String tipoBanco = tipo.equals("user") ? "comprador" : "vendedor";

        // 1. Validações
        if (nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()) {
            view.exibirMensagem("Preencha todos os campos.");
            return;
        }
        if (!senha.equals(confirmaSenha)) {
            view.exibirMensagem("As senhas não conferem.");
            return;
        }

        // 2. Lógica de Cadastro
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha); // Passa a senha em texto puro
        novoUsuario.setTipo(tipoBanco);

        // O DAO (modificado abaixo) salvará essa senha em texto puro
        boolean sucesso = usuarioDAO.cadastrar(novoUsuario);

        // 3. Feedback
        if (sucesso) {
            view.exibirMensagem("Cadastro realizado com sucesso! \nFaça o login.");
            view.dispose(); // Fecha a tela de cadastro
        } else {
            view.exibirMensagem("Erro ao cadastrar. (O email pode já estar em uso).");
        }
    }
}