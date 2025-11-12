package controller;

import model.bean.Jogo;
import model.dao.JogoDAO;
import model.bean.Usuario;
import model.bean.Compra; // Importa o novo modelo
import java.util.ArrayList;
import java.util.List;

public class LojaController {

    private JogoDAO dao;

    public LojaController() {
        dao = new JogoDAO();
    }

    public List<Jogo> listarJogosDaLoja(int idUsuario) {
        return dao.listarJogosDaLoja(idUsuario);
    }

    public List<Jogo> listarJogosDaBiblioteca(int idUsuario) {
        return dao.listarJogosDaBiblioteca(idUsuario);
    }

    public boolean realizarCompra(Usuario usuario, Jogo jogo) {
        Compra novaCompra = new Compra(usuario.getId(), jogo.getId(), jogo.getPreco());

        boolean sucesso = dao.registrarCompra(novaCompra);

        if (sucesso) {
            jogo.incrementDownloads();
        }
        return sucesso;
    }

    public List<Jogo> buscarJogos(String termo, int idUsuario) {
        List<Jogo> todosJogosDaLoja = listarJogosDaLoja(idUsuario);
        List<Jogo> jogosFiltrados = new ArrayList<>();

        for (Jogo jogo : todosJogosDaLoja) {
            if (jogo.getNome().toLowerCase().contains(termo.toLowerCase())) {
                jogosFiltrados.add(jogo);
            }
        }
        return jogosFiltrados;
    }
}