package com.edt.model;

public class Etudiant extends User{
    private String classe;

    public Etudiant(String nom, String prenom, String email, String motDePasse, String classe) {
        super.setNom(nom);
        super.setPrenom(prenom);
        super.setEmail(email);
        super.setMotDePasse(motDePasse);
        this.classe = classe;
    }

    public void setClasse(String classe) { this.classe = classe;}

    public String getClasse() { return classe;}
}
