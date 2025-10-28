package controller;

import model.bean.Jogo;
import model.dao.JogoDAO;
import java.util.ArrayList;
import java.util.List;

public class LojaController {

    private JogoDAO dao;

    public LojaController() {
        dao = new JogoDAO();
    }

    public List<Jogo> listarJogos() {
        return dao.listarTodosJogos();
    }

    public void registrarDownload(Jogo jogo) {
        dao.registrarDownload(jogo.getId());
        jogo.incrementDownloads();
    }

    public List<Jogo> buscarJogos(String termo) {
        List<Jogo> todosJogos = listarJogos();
        List<Jogo> jogosFiltrados = new ArrayList<>();

        for (Jogo jogo : todosJogos) {
            if (jogo.getNome().toLowerCase().contains(termo.toLowerCase())) {
                jogosFiltrados.add(jogo);
            }
        }

        return jogosFiltrados;
    }
}