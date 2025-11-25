import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/chatdb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Sadhvi@321";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e);
        }
    }
}



