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
            mostrarDialogoNovaSenha(email, tipo); // Passa o tipo
        } else {
            view.exibirMensagem("Email não encontrado para este tipo de conta.");
        }
    }

    // --- MUDANÇA PRINCIPAL AQUI ---
    // Este método foi completamente reescrito
    private void mostrarDialogoNovaSenha(String email, String tipo) {

        // 1. Cria e exibe o seu diálogo customizado
        DialogNovaSenha dialog = new DialogNovaSenha(view); // 'view' é o JFrame pai
        dialog.setVisible(true);

        // 2. O código para aqui até o diálogo fechar (porque é modal)

        // 3. Pega a senha que o usuário digitou
        String novaSenha = dialog.getNovaSenha();

        // 4. Verifica se o usuário confirmou (novaSenha não será nula)
        if (novaSenha != null) {
            // A lógica de verificação (senhas vazias/diferentes)
            // já foi tratada dentro do DialogNovaSenha.

            boolean sucesso = false;
            if ("user".equals(tipo)) {
                sucesso = usuarioDAO.atualizarSenha(email, novaSenha);
            } else if ("vendedor".equals(tipo)) {
                sucesso = vendedorDAO.atualizarSenha(email, novaSenha);
            }

            if (sucesso) {
                view.exibirMensagem("Senha redefinida com sucesso!");
                view.dispose(); // Fecha a TelaResetSenha
            } else {
                view.exibirMensagem("Ocorreu um erro ao atualizar a senha.");
            }
        }
        // Se novaSenha for nula, significa que o usuário clicou em "Cancelar",
        // então não fazemos nada.
    }
}