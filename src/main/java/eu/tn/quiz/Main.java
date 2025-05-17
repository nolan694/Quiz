package eu.tn.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/eu/tn/quiz/login.fxml"));
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        //        OperationDAO dao = new OperationDAO();
//        Operation op = new Operation();
//        op.setNombre1(10);
//        op.setNombre2(5);
//        op.setOperation("-");
//        op.setResultat(5);
//        op.setDateOperation(LocalDateTime.now());
//        op.setUtilisateurId(1);
//
//        System.out.println("Avant saveOperation");
//        dao.saveOperation(op);
//        System.out.println("Après saveOperation");
    }
}