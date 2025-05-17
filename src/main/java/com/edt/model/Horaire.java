package com.edt.model;

import java.time.LocalDateTime;
import java.time.Duration;

public class Horaire {

    private LocalDateTime dateDebut;
    private int duree; // en minutes

    public Horaire(LocalDateTime dateDebut, int duree) {
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public int getDuree() { return duree; }
    public LocalDateTime getDateFin() { return dateDebut.plusMinutes(duree); }

    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public void setDuree(int duree) { this.duree = duree; }
    public Duration getDureeAsDuration() { return Duration.ofMinutes(duree); }

    @Override
    public String toString() {
        return "Début : " + dateDebut + ", Fin : " + getDateFin();
    }
}