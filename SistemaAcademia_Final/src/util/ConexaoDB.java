package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:academia.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}
