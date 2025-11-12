package model.dao;

import database.ConexaoMySQL;
import model.bean.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendedorDAO {

    /**
     * CORRIGIDO: Agora também insere o 'tipo' do usuário.
     */
    public boolean cadastrar(Vendedor vendedor) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        // MUDANÇA: Adicionada a coluna 'tipo'
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, vendedor.getNomeLoja());
            stmt.setString(2, vendedor.getEmail());
            stmt.setString(3, vendedor.getSenha());
            stmt.setString(4, "vendedor"); // MUDANÇA: Define o tipo como 'vendedor'
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }

    /**
     * Este método estava correto. Nenhuma mudança.
     */
    public Vendedor validarLogin(String email, String senha) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ? AND tipo = 'vendedor'";

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

    /**
     * Este método estava correto. Nenhuma mudança.
     */
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

    // --- NOVO MÉTODO (Necessário para o ResetSenhaController) ---
    /**
     * Verifica se um email já existe para um usuário do tipo 'vendedor'.
     */
    public boolean emailExists(String email) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // Usamos SELECT 1 para ser mais rápido (só queremos saber se existe)
        String sql = "SELECT 1 FROM usuarios WHERE email = ? AND tipo = 'vendedor'";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            return rs.next(); // Retorna true se encontrou um registro, false se não
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt, rs);
        }
    }

    // --- NOVO MÉTODO (Necessário para o ResetSenhaController) ---
    /**
     * Atualiza a senha de um usuário baseado no email,
     * APENAS se ele for do tipo 'vendedor'.
     */
    public boolean atualizarSenha(String email, String novaSenha) {
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;
        // Garante que só estamos atualizando a senha de um vendedor
        String sql = "UPDATE usuarios SET senha = ? WHERE email = ? AND tipo = 'vendedor'";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, novaSenha);
            stmt.setString(2, email);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se 1 linha foi atualizada
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoMySQL.fecharConexao(conexao, stmt);
        }
    }
}