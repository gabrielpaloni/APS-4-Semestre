package controller;

import model.dao.JogoDAO;
import view.TelaPrincipalUsuario;

public class LojaController {

    private TelaPrincipalUsuario view;
    private JogoDAO jogoDAO;

    public LojaController(TelaPrincipalUsuario view) {
        this.view = view;
        this.jogoDAO = new JogoDAO();
    }

    public void carregarJogos() {
        // 1. Chamar o jogoDAO.listarTodos()
        // 2. Pegar a lista de Jogos
        // 3. Iterar pela lista e criar os "cards" (JPanels)
        // 4. Adicionar os cards na view
    }

    public void comprarJogo(int idJogo) {
        // 1. Lógica de compra (pode ser só uma mensagem)
        // 2. Talvez criar uma tabela "biblioteca_usuario" no banco
    }
}
