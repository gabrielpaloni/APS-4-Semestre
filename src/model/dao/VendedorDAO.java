package model.dao;

import database.ConexaoMySQL;
import model.bean.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendedorDAO {

    public boolean cadastrar(Vendedor vendedor) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, vendedor.getNomeLoja());
            stmt.setString(2, vendedor.getEmail());
            stmt.setString(3, vendedor.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    public Vendedor validarLogin(String email, String senha) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt("id"));
                vendedor.setEmail(rs.getString("email"));
                vendedor.setNomeLoja(rs.getString("nome"));
                return vendedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
        return null;
    }

    public Vendedor buscarVendedorPorId(int id) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt("id"));
                vendedor.setNomeLoja(rs.getString("nome"));
                vendedor.setEmail(rs.getString("email"));
                vendedor.setSenha(rs.getString("senha"));
                vendedor.setTipo(rs.getString("tipo"));
                vendedor.setDataCadastro(rs.getString("data_cadastro"));

                return vendedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
        return null;
    }
}