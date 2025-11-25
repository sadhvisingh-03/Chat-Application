package chatapp;
import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            // Reconnect if connection is null or closed
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chatdb?allowPublicKeyRetrieval=true&useSSL=false",
                    "root",
                    "Sadhvi@321" // ✅ your password is correct here
                );
                System.out.println("Database Connected Successfully!");
            }
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return connection;
    }

    // Optional: for testing connection only
    public static void main(String[] args) {
        getConnection();
    }
}
