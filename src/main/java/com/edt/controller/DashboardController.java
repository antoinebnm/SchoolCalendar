package com.edt.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.edt.dao.CoursDAO;
import com.edt.dao.UserDAO;
import com.edt.model.Cours;
import com.edt.model.Horaire;
import com.edt.utils.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private CalendarView calendarView;

    private Calendar coursCalendar = new Calendar("Cours");

    private int userId = 1; // TODO: replace with real user session

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calendarView.getCalendarSources().get(0).getCalendars().add(coursCalendar);
        calendarView.showWeekPage();

        loadEvents(userId);
    }

private void loadEvents(int userId) {
    try (Connection conn = DatabaseManager.getConnection()) {
        // Vérifier d'abord le type d'utilisateur
        String userType = UserDAO.findSomethingById(userId, "type");
        if (Objects.equals(userType, "Etudiant") || Objects.equals(userType, "Enseignant")) {
            coursCalendar.setReadOnly(true);
            calendarView.setEntryFactory(param -> null);
        }
        
        // Puis charger les cours
        CoursDAO coursDAO = new CoursDAO(conn);
        List<Cours> coursList = coursDAO.getCoursByUserId(userId);

        for (Cours cours : coursList) {
            // Code pour ajouter les cours au calendrier
            Entry<String> entry = new Entry<>(cours.getMatiere());
            // ...
            coursCalendar.addEntry(entry);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private DayOfWeek getDayOfWeek(String jour) {
        return switch (jour.toUpperCase()) {
            case "LUNDI" -> DayOfWeek.MONDAY;
            case "MARDI" -> DayOfWeek.TUESDAY;
            case "MERCREDI" -> DayOfWeek.WEDNESDAY;
            case "JEUDI" -> DayOfWeek.THURSDAY;
            case "VENDREDI" -> DayOfWeek.FRIDAY;
            case "SAMEDI" -> DayOfWeek.SATURDAY;
            default -> DayOfWeek.MONDAY;
        };
    }
}