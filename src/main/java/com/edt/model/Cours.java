package com.edt.model;

public class Cours {
    private int id;
    private String matiere;
    private Horaire horaire;
    private String salle;
    private String enseignant;
    private String description;

    public Cours(int id, String matiere, Horaire horaire, String salle, String enseignant, String description) {
        this.id = id;
        this.matiere = matiere;
        this.horaire = horaire;
        this.salle = salle;
        this.enseignant = enseignant;
        this.description = description;
    }

    public String getMatiere() { return matiere; }
    public Horaire getHoraire() { return horaire; }
    public String getSalle() { return salle; }
    public String getEnseignant() { return enseignant; }
    public String getDescription() { return description; }
}