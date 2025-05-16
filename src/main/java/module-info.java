module com.edt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires com.calendarfx.view;

    opens com.edt to javafx.fxml;
    exports com.edt;
    exports com.edt.controller;
    opens com.edt.controller to javafx.fxml;
}