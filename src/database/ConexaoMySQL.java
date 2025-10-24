package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoMySQL {

    private static final Properties props = new Properties();
    private static Connection conexao;

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
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conexao;
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
                conexao = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
            fecharConexao(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            fecharConexao(con, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}