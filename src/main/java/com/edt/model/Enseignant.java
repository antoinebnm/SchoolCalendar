package com.edt.model;

public class Enseignant extends User{
    private String matiere;

    public Enseignant(String nom, String prenom, String email, String motDePasse, String matiere) {
        super.setNom(nom);
        super.setPrenom(prenom);
        super.setEmail(email);
        super.setMotDePasse(motDePasse);
        this.matiere = matiere;
    }

    public void setMatiere(String matiere) {this.matiere = matiere;}

    public String getMatiere() {return matiere;}
}
