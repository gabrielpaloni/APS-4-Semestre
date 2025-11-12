package model.dao;

import database.ConexaoMySQL;
import model.bean.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompraDAO {

    public boolean registrarCompra(Compra novaCompra) {
        String sql = "INSERT INTO compras (id_comprador, id_jogo, preco_pago, data_compra) VALUES (?, ?, ?, NOW())";
        Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, novaCompra.getIdComprador());
            stmt.setInt(2, novaCompra.getIdJogo());
            stmt.setDouble(3, novaCompra.getPrecoPago());

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