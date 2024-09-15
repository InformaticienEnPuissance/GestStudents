package lib;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
        String user = "root";  // Remplace par ton nom d'utilisateur
        String password = "";  // Remplace par ton mot de passe

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

