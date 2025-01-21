import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/meu_banco";
    private static final String USER = "postgres";
    private static final String PASSWORD = "senha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
