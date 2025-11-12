package controller;

import model.bean.Jogo;
import model.dao.JogoDAO;
<<<<<<< HEAD
import model.bean.Usuario;
import model.bean.Compra; // Importa o novo modelo
=======
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
import java.util.ArrayList;
import java.util.List;

public class LojaController {

    private JogoDAO dao;

    public LojaController() {
        dao = new JogoDAO();
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
            if (jogo.getNome().toLowerCase().contains(termo.toLowerCase())) {
                jogosFiltrados.add(jogo);
            }
        }
<<<<<<< HEAD
=======

>>>>>>> 762f85b7f0e7b4f055b6958eee7e4140019a5410
        return jogosFiltrados;
    }
}