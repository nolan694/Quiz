package eu.tn.quiz.controller;

import eu.tn.quiz.dao.OperationDAO;
import eu.tn.quiz.model.Operation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class OperationController {
    private OperationDAO dao = new OperationDAO();

    @FXML
    private TextField txtNombre1, txtNombre2, txtOperation, txtResultat, txtUtilisateurId;

    @FXML
    private Label lblMessage;

    @FXML
    private void onSaveButtonClicked() {
        try {
            double nombre1 = Double.parseDouble(txtNombre1.getText());
            double nombre2 = Double.parseDouble(txtNombre2.getText());
            String operation = txtOperation.getText();
            double resultat = Double.parseDouble(txtResultat.getText());
            int utilisateurId = Integer.parseInt(txtUtilisateurId.getText());

            Operation op = new Operation();
            op.setNombre1(nombre1);
            op.setNombre2(nombre2);
            op.setOperation(operation);
            op.setResultat(resultat);
            op.setDateOperation(LocalDateTime.now());
            op.setUtilisateurId(utilisateurId);

            System.out.println("Avant saveOperation");
            dao.saveOperation(op);
            System.out.println("Après saveOperation");

            lblMessage.setText("Opération enregistrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Erreur : " + e.getMessage());
        }
    }

}
