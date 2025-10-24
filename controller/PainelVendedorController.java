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
        // 1. Validar os dados
        // 2. Criar um objeto Jogo
        Jogo novoJogo = new Jogo();
        novoJogo.setNome(nome);
        novoJogo.setDescricao(desc);
        novoJogo.setPreco(preco);
        novoJogo.setPlataforma(plataforma);
        novoJogo.setIdVendedor(this.idVendedorLogado);

        // 3. Chamar o DAO
        boolean sucesso = jogoDAO.cadastrar(novoJogo);

        // 4. Exibir feedback na view
    }

    public void excluirJogo(int idJogo) {
        // 1. Pedir confirmação
        // 2. Chamar jogoDAO.excluir(idJogo, this.idVendedorLogado)
        // 3. Atualizar a JTable na view
    }

    // ... método carregarMeusJogos() ...
    // ... método atualizarJogo() ...
}
