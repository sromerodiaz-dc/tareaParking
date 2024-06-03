import java.sql.*;

public class Database {
    private static final Database instance = new Database();
    public final Connection connection;

    private Database() {
        connection = connect();
    }

    public static Database getInstance() {
        return instance;
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Error al conectar a la base de datos", e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException("Error al cerrar la conexión", e);
        }
    }
}

class DatabaseConfig {
    public static final String URL = "jdbc:postgresql://localhost:5432/proyecto";
    public static final String USER = "postgres";
    public static final String PASSWORD = "123";
}

class DatabaseException extends RuntimeException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}