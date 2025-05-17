module eu.tn.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.tn.quiz to javafx.fxml;
    exports eu.tn.quiz;
    exports eu.tn.quiz.controller;
    opens eu.tn.quiz.controller to javafx.fxml;
}