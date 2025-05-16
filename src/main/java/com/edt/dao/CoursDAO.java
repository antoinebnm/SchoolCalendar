package com.edt.dao;

import com.edt.model.Cours;
import com.edt.model.Etudiant;
import com.edt.model.Horaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    private final Connection connection;

    public CoursDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Cours> getCoursByUserId(int userId) {
        List<Cours> coursList = new ArrayList<>();
        String query = "select * from Cours Natural join EmploiDuTemps where EmploiDuTemps.cours_id = Cours.id AND EmploiDuTemps.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cours cours = new Cours(
                        rs.getInt("id"),
                        rs.getString("matiere"),
                        new Horaire(rs.getTimestamp("date_time_debut").toLocalDateTime(), rs.getInt("duree")),
                        rs.getString("salle"),
                        rs.getString("enseignant"),
                        rs.getString("description")
                );
                coursList.add(cours);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursList;
    }

    public void addCours(Cours cours) {
        String query = "INSERT INTO Cours (nom, id_enseignant, id_salle, date_debut, duree) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cours.getMatiere());
            stmt.setString(2, cours.getEnseignant());
            stmt.setString(3, cours.getSalle());
            stmt.setTimestamp(4, Timestamp.valueOf(cours.getHoraire().getDateDebut()));
            stmt.setInt(5, cours.getHoraire().getDuree());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}