open module com.example.organizer {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.beans;
    requires static lombok;
    requires com.dustinredmond.fxtrayicon;
    requires spring.security.crypto;


    exports com.example.organizer;
    exports com.example.organizer.model;
    exports com.example.organizer.CustomView;

    exports com.example.organizer.Controller;



}
