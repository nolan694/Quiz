package eu.tn.quiz.controller;

import eu.tn.quiz.dao.UtilisateurDAO;
import eu.tn.quiz.model.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @FXML
    private void handleLogin() {
        String nom = usernameField.getText();
        String password = passwordField.getText();

        try {
            Utilisateur user = utilisateurDAO.findByNom(nom);

            if (user == null) {
                errorLabel.setText("Utilisateur inconnu");
                return;
            }

            if (!user.getMotDePasse().equals(password)) {
                errorLabel.setText("Mot de passe incorrect");
                return;
            }

            // Connexion réussie
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/tn/quiz/hello-view.fxml"));
            Parent root = loader.load();

            CalculatorController controller = loader.getController();
            controller.setUtilisateur(user); // on transmet l'utilisateur

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Calculatrice");
            stage.show();

        } catch (SQLException e) {
            errorLabel.setText("Erreur SQL");
            e.printStackTrace();
        } catch (Exception e) {
            errorLabel.setText("Erreur de chargement");
            e.printStackTrace();
        }
    }
}
