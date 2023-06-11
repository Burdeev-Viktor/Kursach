package com.example.organizer.secondTherd;


import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.example.organizer.Const;
import javafx.scene.control.MenuItem;

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
