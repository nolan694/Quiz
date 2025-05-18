package eu.tn.quiz.controller;

import eu.tn.quiz.dao.OperationDAO;
import eu.tn.quiz.model.Operation;
import eu.tn.quiz.model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalculatorController {

    @FXML
    private TextField number1Field;

    @FXML
    private TextField number2Field;

    @FXML
    private Label resultLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private TableView<Operation> historyTable;

    @FXML
    private TableColumn<Operation, String> dateColumn;

    @FXML
    private TableColumn<Operation, String> operationColumn;

    @FXML
    private TableColumn<Operation, String> resultColumn;

    private Utilisateur utilisateur;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        System.out.println("Connecté en tant que : " + (utilisateur != null ? utilisateur.getNom() : "Inconnu"));

        if (userLabel != null && utilisateur != null) {
            userLabel.setText("Connecté : " + utilisateur.getNom());
        }

        // Initialiser colonnes TableView
        initHistoryTableColumns();
    }

    private void initHistoryTableColumns() {
        dateColumn.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getDateOperation();
            String formattedDate = (date != null) ? dtf.format(date) : "";
            return new javafx.beans.property.SimpleStringProperty(formattedDate);
        });

        operationColumn.setCellValueFactory(cellData -> {
            Operation op = cellData.getValue();
            String opStr = String.format("%.2f %s %.2f", op.getNombre1(), op.getOperation(), op.getNombre2());
            return new javafx.beans.property.SimpleStringProperty(opStr);
        });

        resultColumn.setCellValueFactory(cellData -> {
            double res = cellData.getValue().getResultat();
            return new javafx.beans.property.SimpleStringProperty(String.format("%.2f", res));
        });
    }

    private double parseInput(TextField field) throws NumberFormatException {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Entrée invalide !");
            throw e;
        }
    }

    private void calculateAndSave(double n1, double n2, String operateur, double resultat) {
        resultLabel.setText(String.valueOf(resultat));
        try {
            if (utilisateur == null) {
                messageLabel.setText("Utilisateur non connecté, opération non enregistrée.");
                return;
            }

            Operation op = new Operation(
                    utilisateur.getId(),
                    n1,
                    n2,
                    operateur,
                    resultat,
                    LocalDateTime.now()
            );

            OperationDAO dao = new OperationDAO();
            dao.saveOperation(op);

            messageLabel.setText("Opération enregistrée !");
        } catch (Exception e) {
            messageLabel.setText("Erreur lors de l'enregistrement !");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdd() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 + n2;
            calculateAndSave(n1, n2, "+", result);
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleSubtract() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 - n2;
            calculateAndSave(n1, n2, "-", result);
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleMultiply() {
        try {
            double n1 = parseInput(number1Field);
            double n2 = parseInput(number2Field);
            double result = n1 * n2;
            calculateAndSave(n1, n2, "*", result);
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
            calculateAndSave(n1, n2, "/", result);
        } catch (NumberFormatException ignored) {}
    }

    @FXML
    private void handleShowHistory() {
        if (utilisateur == null) {
            messageLabel.setText("Utilisateur non connecté, impossible d'afficher l'historique.");
            return;
        }
        try {
            OperationDAO dao = new OperationDAO();
            List<Operation> operations = dao.findByUserId(utilisateur.getId());

            ObservableList<Operation> data = FXCollections.observableArrayList(operations);

            historyTable.setItems(data);
            historyTable.setVisible(true);
            messageLabel.setText("Historique chargé (" + operations.size() + " opérations).");
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du chargement de l'historique !");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        // Exemple basique : fermer la fenêtre ou revenir à la page login
        // Ici on ferme l'application, adapte selon ta gestion des scènes
        System.out.println("Déconnexion de l'utilisateur " + (utilisateur != null ? utilisateur.getNom() : "inconnu"));
        javafx.application.Platform.exit();
    }
}
