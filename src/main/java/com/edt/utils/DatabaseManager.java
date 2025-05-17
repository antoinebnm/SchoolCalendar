package com.edt.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:data/edt.sqlite";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(DatabaseManager.class.getResourceAsStream("/db/init_db.sql")))
            );
            String sql = reader.lines().collect(Collectors.joining("\n"));
            reader.close();

            for (String query : sql.split(";")) {
                if (!query.trim().isEmpty()) {
                    stmt.execute(query);
                }
            }

            System.out.println("Base de données initialisée !");
        } catch (Exception e) {
            System.err.println("Erreur d'initialisation de la base de données : " + e.getMessage());
        }
    }

    public static boolean testConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}