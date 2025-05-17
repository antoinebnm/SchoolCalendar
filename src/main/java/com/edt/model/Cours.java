package com.edt.model;

public class Cours {
    private int id;
    private String matiere;
    private Horaire horaire;
    private int salle_id;
    private int enseignant_id;
    private String description;

    public Cours(int id, String matiere, Horaire horaire, int salle_id, int enseignant_id, String description) {
        this.id = id;
        this.matiere = matiere;
        this.horaire = horaire;
        this.salle_id = salle_id;
        this.enseignant_id = enseignant_id;
        this.description = description;
    }

    public String getMatiere() { return matiere; }
    public Horaire getHoraire() { return horaire; }
    public int getSalleId() { return salle_id; }
    public int getEnseignantId() { return enseignant_id; }
    public String getDescription() { return description; }
}