package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// Padrão Singleton para gerenciar a conexão
public class ConexaoMySQL {

    private static final Properties props = new Properties();
    private static Connection conexao;

    // Bloco estático para carregar as propriedades UMA VEZ
    static {
        try (InputStream input = ConexaoMySQL.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o config.properties");
                throw new IOException("config.properties não encontrado no classpath");
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao carregar o arquivo de propriedades", ex);
        }
    }

    private ConexaoMySQL() {
        // Construtor privado para impedir instanciação
    }

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                );
            } catch (SQLException e) {
                // Em um app real, trate isso com uma janela de erro
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conexao;
    }

    // Métodos utilitários para fechar conexões (Clean Code)
    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
                conexao = null; // Força a reabrir na próxima chamada
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
            fecharConexao(con); // Reutiliza o método anterior
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            fecharConexao(con, stmt); // Reutiliza o método anterior
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}