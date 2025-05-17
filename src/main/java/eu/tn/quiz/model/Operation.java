package eu.tn.quiz.model;

import java.time.LocalDateTime;

public class Operation {
    private int id;  // id auto-incrémenté en base
    private Integer utilisateurId;  // ID utilisateur, nullable
    private double nombre1;
    private double nombre2;
    private String operation;    // "+", "-", "*", "/"
    private double resultat;
    private LocalDateTime dateOperation;

    public Operation() {
    }

    public Operation(Integer utilisateurId, double nombre1, double nombre2, String operation, double resultat, LocalDateTime dateOperation) {
        this.utilisateurId = utilisateurId;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.operation = operation;
        this.resultat = resultat;
        this.dateOperation = dateOperation;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public double getNombre1() {
        return nombre1;
    }

    public void setNombre1(double nombre1) {
        this.nombre1 = nombre1;
    }

    public double getNombre2() {
        return nombre2;
    }

    public void setNombre2(double nombre2) {
        this.nombre2 = nombre2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResultat() {
        return resultat;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }
}
