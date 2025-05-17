package eu.tn.quiz.controller;

import eu.tn.quiz.model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField number1Field;

    @FXML
    private TextField number2Field;

    @FXML
    private Label resultLabel;

    @FXML
    private Label userLabel; // Pour afficher le nom de l'utilisateur connecté

    private Utilisateur utilisateur;

    // ✅ Setter pour recevoir l'utilisateur connecté
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        // Affiche dans la console
        System.out.println("Connecté en tant que : " + utilisateur.getNom());

        // Affiche dans le label si possible
        if (userLabel != null) {
            userLabel.setText("Connecté : " + utilisateur.getNom());
        }
    }

    private double parseInput(TextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Entrée invalide !");
            throw e;
        }
    }

    @FXML
    private void handleAdd() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 + n2;
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleSubtract() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 - n2;
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleMultiply() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 * n2;
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleDivide() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            if (n2 == 0) {
                resultLabel.setText("Division par zéro impossible");
                return;
            }
            double result = n1 / n2;
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ignored) {}
    }
}
