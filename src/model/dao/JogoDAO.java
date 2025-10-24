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

    public boolean cadastrar(Jogo jogo) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO jogos (nome, descricao, preco, plataforma, id_vendedor) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getDescricao());
            stmt.setDouble(3, jogo.getPreco());
            stmt.setString(4, jogo.getPlataforma());
            stmt.setInt(5, jogo.getIdVendedor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    public boolean atualizar(Jogo jogo) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        String sql = "UPDATE jogos SET nome = ?, descricao = ?, preco = ?, plataforma = ? WHERE id = ? AND id_vendedor = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getDescricao());
            stmt.setDouble(3, jogo.getPreco());
            stmt.setString(4, jogo.getPlataforma());
            stmt.setInt(5, jogo.getId());
            stmt.setInt(6, jogo.getIdVendedor());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    public boolean excluir(int idJogo, int idVendedor) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM jogos WHERE id = ? AND id_vendedor = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idJogo);
            stmt.setInt(2, idVendedor);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    public List<Jogo> listarTodos() {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM jogos";
        List<Jogo> jogos = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("nome"));
                jogo.setDescricao(rs.getString("descricao"));
                jogo.setPreco(rs.getDouble("preco"));
                jogo.setPlataforma(rs.getString("plataforma"));
                jogo.setIdVendedor(rs.getInt("id_vendedor"));
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
