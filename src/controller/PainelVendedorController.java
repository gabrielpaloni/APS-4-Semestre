package controller;

import model.bean.Jogo;
import model.dao.JogoDAO;
import view.TelaPrincipalVendedor;

public class PainelVendedorController {

    private TelaPrincipalVendedor view;
    private JogoDAO jogoDAO;
    private int idVendedorLogado;

    public PainelVendedorController(TelaPrincipalVendedor view, int idVendedorLogado) {
        this.view = view;
        this.idVendedorLogado = idVendedorLogado;
        this.jogoDAO = new JogoDAO();
    }

    public void lancarNovoJogo(String nome, String desc, double preco, String plataforma) {

        Jogo novoJogo = new Jogo();
        novoJogo.setNome(nome);
        novoJogo.setDescricao(desc);
        novoJogo.setPreco(preco);
        novoJogo.setPlataforma(plataforma);
        novoJogo.setIdVendedor(this.idVendedorLogado);

        boolean sucesso = jogoDAO.cadastrar(novoJogo);
    }

    public void excluirJogo(int idJogo) {
    }
}