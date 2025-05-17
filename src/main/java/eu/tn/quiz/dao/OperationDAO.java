package eu.tn.quiz.dao;

import eu.tn.quiz.model.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {

    private final String url = "jdbc:mysql://localhost:3306/calculatrice";
    private final String user = "root";
    private final String password = "";

    // Enregistrement d'une opération dans la table "operation"
    public void saveOperation(Operation op) {
        String sql = "INSERT INTO operation (nombre1, nombre2, operation, resultat, date_operation, utilisateur_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, op.getNombre1());
            stmt.setDouble(2, op.getNombre2());
            stmt.setString(3, op.getOperation());
            stmt.setDouble(4, op.getResultat());
            stmt.setTimestamp(5, Timestamp.valueOf(op.getDateOperation()));

            if (op.getUtilisateurId() != null) {
                stmt.setInt(6, op.getUtilisateurId());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted == 0) {
                throw new SQLException("Échec de l'insertion de l'opération, aucune ligne insérée.");
            } else {
                System.out.println("Opération enregistrée avec succès !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Récupérer toutes les opérations
    public List<Operation> findAll() throws SQLException {
        List<Operation> list = new ArrayList<>();
        String sql = "SELECT * FROM operation ORDER BY date_operation DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Operation op = new Operation();
                op.setId(rs.getInt("id"));
                op.setNombre1(rs.getDouble("nombre1"));
                op.setNombre2(rs.getDouble("nombre2"));
                op.setOperation(rs.getString("operation"));
                op.setResultat(rs.getDouble("resultat"));
                Timestamp timestamp = rs.getTimestamp("date_operation");
                if (timestamp != null) {
                    op.setDateOperation(timestamp.toLocalDateTime());
                }
                int userId = rs.getInt("utilisateur_id");
                if (!rs.wasNull()) {
                    op.setUtilisateurId(userId);
                } else {
                    op.setUtilisateurId(null);
                }

                list.add(op);
            }
        }
        return list;
    }

    // Récupérer les opérations d'un utilisateur par utilisateur_id
    public List<Operation> findByUserId(Integer utilisateurId) throws SQLException {
        List<Operation> list = new ArrayList<>();
        String sql = "SELECT * FROM operation WHERE utilisateur_id = ? ORDER BY date_operation DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, utilisateurId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Operation op = new Operation();
                    op.setId(rs.getInt("id"));
                    op.setNombre1(rs.getDouble("nombre1"));
                    op.setNombre2(rs.getDouble("nombre2"));
                    op.setOperation(rs.getString("operation"));
                    op.setResultat(rs.getDouble("resultat"));
                    Timestamp timestamp = rs.getTimestamp("date_operation");
                    if (timestamp != null) {
                        op.setDateOperation(timestamp.toLocalDateTime());
                    }
                    op.setUtilisateurId(utilisateurId);

                    list.add(op);
                }
            }
        }
        return list;
    }
}
