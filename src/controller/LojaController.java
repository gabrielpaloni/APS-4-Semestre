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
    }

    public void comprarJogo(int idJogo) {
    }
}
