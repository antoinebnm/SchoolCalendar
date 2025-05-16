package com.edt.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.edt.dao.CoursDAO;
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
            CoursDAO coursDAO = new CoursDAO(conn);
            List<Cours> coursList = coursDAO.getCoursByUserId(userId);

            for (Cours cours : coursList) {
                Horaire horaire = cours.getHoraire();

                Entry<String> entry = new Entry<>(cours.getMatiere());

                entry.setTitle(cours.getMatiere());
                entry.setLocation(cours.getSalle());
                entry.changeStartDate(cours.getHoraire().getDateDebut().toLocalDate());
                entry.changeStartDate(cours.getHoraire().getDateDebut().toLocalDate());
                entry.changeEndTime(cours.getHoraire().getDateFin().toLocalTime());
                entry.changeEndTime(cours.getHoraire().getDateFin().toLocalTime());

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