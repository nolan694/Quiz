module eu.tn.quiz {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.tn.quiz to javafx.fxml;
    exports eu.tn.quiz;
}