package com.example.organizer.SecondTherd;


import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.example.organizer.Const;
import com.example.organizer.Controller.SciencesController;
import com.example.organizer.Main;
import com.example.organizer.repository.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class SystemTrayClass {
    private static FXTrayIcon trayIcon;

    public static void setTrayIcon(FXTrayIcon fxTrayIconNew){
        trayIcon = fxTrayIconNew;
        javafx.scene.control.MenuItem exit = new MenuItem(Const.EXIT);
        exit.setOnAction(event -> {
            CheckingClass checkingClass = new CheckingClass();
            checkingClass.setRunning(false);
            System.exit(0);
        });
        trayIcon.setTrayIconTooltip(Const.TITLE_MAIN);
        trayIcon.addMenuItem(exit);
        trayIcon.show();
    }

    public static void sendMassage(String title, String massage) {
        trayIcon.showMessage(title,massage);
    }
}
