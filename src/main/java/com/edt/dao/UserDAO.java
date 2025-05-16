package com.edt.dao;

import com.edt.model.User;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    private User getUser(String arg, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, arg);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setMotDePasse(rs.getString("mot_de_passe"));
            u.setType(rs.getString("type"));
            return u;
        }
        return null;
    }

    public User findByEmail(String email) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM utilisateur WHERE email = ?")) {
            User u = getUser(email, stmt);
            if (u != null) return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByType(String type) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM utilisateur WHERE type = ?")) {
            User u = getUser(type, stmt);
            if (u != null) return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}