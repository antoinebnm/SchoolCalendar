package com.edt.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.edt.dao.CoursDAO;
import com.edt.model.Cours;
import com.edt.utils.DatabaseManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private CalendarView calendarView;

    private final Calendar coursCalendar = new Calendar("Cours");

    private int userId;

    private Object coursDetails = new Object() {
        private Cours cours;
    };

    public void setUserId(int userId) {
        this.userId = userId;
        loadEvents(userId);
    }

    private void initCalendarSettings() {
        calendarView.showAddCalendarButtonProperty().set(false);
        calendarView.showSourceTrayButtonProperty().set(false);
        calendarView.showSearchFieldProperty().set(false);
        calendarView.showPageToolBarControlsProperty().set(false);
        calendarView.setEntryFactory(param -> null);
        calendarView.setContextMenuCallback(param -> null);
        calendarView.setEntryDetailsPopOverContentCallback(param -> {
            Entry<?> entry = param.getEntry();

            calendarView.clearSelection();

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                String[] details = entry.getUserObject().toString().split("<§>");

                alert.setTitle(entry.getTitle());
                alert.setHeaderText("Salle " + entry.getLocation());
                alert.setHeaderText(alert.getHeaderText() + "\nEnseignant: " + details[0]);

                alert.setContentText("Début : " + entry.getStartTime() +
                        "\nFin : " + entry.getEndTime());

                if (!details[1].equals("null")) alert.setContentText(alert.getContentText() + "\n\nDescription:\n" + details[1]);

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(calendarView.getScene().getWindow());
                alert.show();});

            return null;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCalendarSettings();

        coursCalendar.setStyle(Calendar.Style.STYLE2);
        coursCalendar.setReadOnly(true);

        calendarView.getCalendarSources().getFirst().getCalendars().add(coursCalendar);
        calendarView.showWeekPage();
    }

    private void loadEvents(int userId) {
        try (Connection conn = DatabaseManager.getConnection()) {
            CoursDAO coursDAO = new CoursDAO(conn);
            List<Cours> coursList = coursDAO.getCoursByUserId(userId);

            for (Cours cours : coursList) {
                Entry<String> entry = new Entry<>(cours.getMatiere());

                entry.setTitle(cours.getMatiere());
                entry.setLocation("L012 (todo)"); //Salle.getSalleById(cours.getSalleId())
                entry.setUserObject(cours.getEnseignantId()+"<§>"+cours.getDescription()); //Enseignant.getNomById(cours.getEnseignantId())
                entry.changeStartDate(cours.getHoraire().getDateDebut().toLocalDate());
                entry.changeStartTime(cours.getHoraire().getDateDebut().toLocalTime());
                entry.changeEndDate(cours.getHoraire().getDateFin().toLocalDate());
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