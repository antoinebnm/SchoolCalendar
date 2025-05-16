package com.edt.model;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String type;

    public int getId() {return id;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public String getType() {return type;}

    public void setId(int id) {this.id = id;}
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) {this.motDePasse = motDePasse;}
    public void setType(String type) {this.type = type;}
}