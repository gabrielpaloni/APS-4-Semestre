package controller;

import model.dao.UsuarioDAO;
import model.dao.VendedorDAO;
import view.DialogNovaSenha; // <-- MUDANÇA: Importar o novo diálogo
import view.TelaResetSenha;
import javax.swing.*;
import java.awt.*;

public class ResetSenhaController {

    private TelaResetSenha view;
    private UsuarioDAO usuarioDAO;
    private VendedorDAO vendedorDAO;

    public ResetSenhaController(TelaResetSenha view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        this.vendedorDAO = new VendedorDAO();
    }

    public void acaoBotaoRedefinir() {
        String email = view.getEmail();
        String tipo = view.getTipoUsuario();

        if (email.trim().isEmpty()) {
            view.exibirMensagem("Por favor, digite seu email.");
            return;
        }

        boolean emailValido = false;
        if ("user".equals(tipo)) {
            emailValido = usuarioDAO.emailExists(email, "comprador");
        } else if ("vendedor".equals(tipo)) {
            emailValido = vendedorDAO.emailExists(email);
        }

        if (emailValido) {
            mostrarDialogoNovaSenha(email, tipo);
        } else {
            view.exibirMensagem("Email não encontrado para este tipo de conta.");
        }
    }

    private void mostrarDialogoNovaSenha(String email, String tipo) {

        DialogNovaSenha dialog = new DialogNovaSenha(view);
        dialog.setVisible(true);

        String novaSenha = dialog.getNovaSenha();

        if (novaSenha != null) {

            boolean sucesso = false;
            if ("user".equals(tipo)) {
                sucesso = usuarioDAO.atualizarSenha(email, novaSenha);
            } else if ("vendedor".equals(tipo)) {
                sucesso = vendedorDAO.atualizarSenha(email, novaSenha);
            }

            if (sucesso) {
                view.exibirMensagem("Senha redefinida com sucesso!");
                view.dispose();
            } else {
                view.exibirMensagem("Ocorreu um erro ao atualizar a senha.");
            }
        }
    }
}