package model.dao;

import database.ConexaoMySQL;
import model.bean.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    public boolean cadastrar(Jogo jogo) { return false; }
    public boolean atualizar(Jogo jogo) { return false; }
    public boolean excluir(int idJogo, int idVendedor) { return false; }

    public List<Jogo> listarPorVendedor(int idVendedor) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT j.*, COUNT(c.id_jogo) AS numero_vendas, MAX(c.data_compra) AS ultima_venda " +
                "FROM jogos j " +
                "LEFT JOIN compras c ON j.id = c.id_jogo " +
                "WHERE j.id_vendedor = ? " +
                "GROUP BY j.id";

        List<Jogo> jogos = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idVendedor);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("titulo"));
                jogo.setDescricao(rs.getString("descricao"));
                jogo.setPreco(rs.getDouble("preco"));
                jogo.setIdVendedor(rs.getInt("id_vendedor"));
                jogo.setNomeArquivoImagem(rs.getString("nome_imagem"));
                jogo.setDataLancamento(rs.getString("data_lancamento"));
                jogo.setRequisitosSistema(rs.getString("requisitos_sistema"));
                jogo.setDataPublicacao(rs.getString("data_publicacao"));
                jogo.setTotalDownloads(rs.getInt("total_downloads"));
                jogo.setNumeroDeVendas(rs.getInt("numero_vendas"));
                jogo.setDataUltimaVenda(rs.getString("ultima_venda"));

                jogos.add(jogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
        return jogos;
    }
}