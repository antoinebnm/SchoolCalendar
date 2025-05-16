package com.edt.dao;

import com.edt.model.Cours;
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
}