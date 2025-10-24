package controller;

import model.dao.UsuarioDAO;
import view.TelaResetSenha;
import javax.swing.*;
import java.awt.*;

public class ResetSenhaController {

    private TelaResetSenha view;
    private UsuarioDAO usuarioDAO;

    public ResetSenhaController(TelaResetSenha view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
    }

    public void acaoBotaoRedefinir() {
        String email = view.getEmail();
        String tipo = view.getTipoUsuario();
        String tipoBanco = tipo.equals("user") ? "comprador" : "vendedor";

        if (email.trim().isEmpty()) {
            view.exibirMensagem("Por favor, digite seu email.");
            return;
        }

        if (usuarioDAO.emailExists(email, tipoBanco)) {
            mostrarDialogoNovaSenha(email);
        } else {
            view.exibirMensagem("Email não encontrado para este tipo de conta.");
        }
    }

    private void mostrarDialogoNovaSenha(String email) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel lblNovaSenha = new JLabel("Nova Senha:");
        JPasswordField txtNovaSenha = new JPasswordField(10);
        JLabel lblConfirmaSenha = new JLabel("Confirmar Senha:");
        JPasswordField txtConfirmaSenha = new JPasswordField(10);

        panel.add(lblNovaSenha);
        panel.add(txtNovaSenha);
        panel.add(lblConfirmaSenha);
        panel.add(txtConfirmaSenha);

        int result = JOptionPane.showConfirmDialog(view, panel, "Digite a Nova Senha",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String novaSenha = new String(txtNovaSenha.getPassword());
            String confirmaSenha = new String(txtConfirmaSenha.getPassword());

            if (novaSenha.isEmpty() || !novaSenha.equals(confirmaSenha)) {
                view.exibirMensagem("As senhas não conferem ou estão vazias. Tente novamente.");
                return;
            }

            boolean sucesso = usuarioDAO.atualizarSenha(email, novaSenha);

            if (sucesso) {
                view.exibirMensagem("Senha redefinida com sucesso!");
                view.dispose();
            } else {
                view.exibirMensagem("Ocorreu um erro ao atualizar a senha.");
            }
        }
    }
}