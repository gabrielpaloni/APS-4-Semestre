package model.dao;

import database.ConexaoMySQL;
import model.bean.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    public Usuario validarLogin(String email, String plainPassword, String tipo) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ? AND tipo = ?";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, plainPassword);
            stmt.setString(3, tipo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));

                return usuario;
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
    }

    public boolean emailExists(String email, String tipo) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ? AND tipo = ?";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, tipo);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
    }

    public boolean atualizarSenha(String email, String novaSenhaPura) {
        String sql = "UPDATE usuarios SET senha = ? WHERE email = ?";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, novaSenhaPura);
            stmt.setString(2, email);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }
}