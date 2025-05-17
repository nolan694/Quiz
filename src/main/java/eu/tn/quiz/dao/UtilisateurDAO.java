package eu.tn.quiz.dao;

import eu.tn.quiz.model.Utilisateur;

import java.sql.*;

public class UtilisateurDAO {

    private final String url = "jdbc:mysql://localhost:3306/calculatrice";
    private final String user = "root";
    private final String password = "";

    public Utilisateur findByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM utilisateur WHERE nom = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setEmail(rs.getString("email"));
                    u.setMotDePasse(rs.getString("mot_de_passe"));
                    return u;
                }
            }
        }
        return null;
    }

    public void insertUser(Utilisateur u) throws SQLException {
        String sql = "INSERT INTO users (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getMotDePasse()); // Idéalement hashé
            stmt.executeUpdate();
        }
    }
}
