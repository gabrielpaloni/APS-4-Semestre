package model.dao;

import database.ConexaoMySQL;
import model.bean.Compra;
import model.bean.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    private CompraDAO compraDAO;

    public JogoDAO() {
        this.compraDAO = new CompraDAO();
    }

    public boolean cadastrar(Jogo jogo) { return false; }
    public boolean atualizar(Jogo jogo) { return false; }
    public boolean excluir(int idJogo, int idVendedor) { return false; }

    public List<Jogo> listarPorVendedor(int idVendedor) {
        Connection conexao = ConexaoMySQL.getConexao(); PreparedStatement stmt = null; ResultSet rs = null;
        String sql = "SELECT j.*, COUNT(c.id_jogo) AS numero_vendas, MAX(c.data_compra) AS ultima_venda " +
                "FROM jogos j LEFT JOIN compras c ON j.id = c.id_jogo " +
                "WHERE j.id_vendedor = ? GROUP BY j.id";
        List<Jogo> jogos = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement(sql); stmt.setInt(1, idVendedor); rs = stmt.executeQuery();
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
        } catch (SQLException e) { e.printStackTrace();
        } finally { ConexaoMySQL.fecharConexao(conexao, stmt, rs); }
        return jogos;
    }

    public List<Jogo> listarJogosDaLoja(int idUsuario) {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogos WHERE id NOT IN (SELECT id_jogo FROM compras WHERE id_comprador = ?)";

        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                jogos.add(mapearJogo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
        return jogos;
    }

    public List<Jogo> listarJogosDaBiblioteca(int idUsuario) {
        List<Jogo> jogos = new ArrayList<>();

        String sql = "SELECT DISTINCT j.* FROM jogos j " +
                "JOIN compras c ON j.id = c.id_jogo " +
                "WHERE c.id_comprador = ?";

        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                jogos.add(mapearJogo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
        return jogos;
    }

    private Jogo mapearJogo(ResultSet rs) throws SQLException {
        Jogo jogo = new Jogo();
        jogo.setId(rs.getInt("id"));
        jogo.setNome(rs.getString("titulo"));
        jogo.setDescricao(rs.getString("descricao"));
        jogo.setPreco(rs.getDouble("preco"));
        jogo.setNomeArquivoImagem(rs.getString("nome_imagem"));
        jogo.setTotalDownloads(rs.getInt("total_downloads"));
        jogo.setRequisitosSistema(rs.getString("requisitos_sistema"));
        // jogo.setDataLancamento(rs.getString("data_lancamento"));
        // jogo.setDataPublicacao(rs.getString("data_publicacao"));
        // jogo.setIdVendedor(rs.getInt("id_vendedor"));

        return jogo;
    }

    public boolean registrarCompra(Compra novaCompra) {
        boolean sucessoCompra = compraDAO.registrarCompra(novaCompra);
        if (sucessoCompra) {
            registrarDownload(novaCompra.getIdJogo());
            return true;
        }
        return false;
    }

    private void registrarDownload(int idJogo) {
        String sql = "UPDATE jogos SET total_downloads = total_downloads + 1 WHERE id = ?";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idJogo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }
}