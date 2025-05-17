package eu.tn.quiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {

    private final String url = "jdbc:mysql://localhost:3306/calculatrice";
    private final String user = "root";
    private final String password = "";  // Mets ton mot de passe MySQL ici

    public void testConnection() {
        System.out.println("Test de connexion à la base...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("Connexion échouée !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DBTest test = new DBTest();
        test.testConnection();
    }
}
