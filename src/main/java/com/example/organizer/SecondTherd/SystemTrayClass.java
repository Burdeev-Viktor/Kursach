package com.example.organizer.SecondTherd;


import com.example.organizer.Const;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SystemTrayClass {
    private static TrayIcon trayIcon;

    public static void start() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Class<?> resource = SystemTrayClass.class;
            URL imageURL = resource.getResource("/com/example/organizer/img/iconSmall.png");
            Image image = Toolkit.getDefaultToolkit().createImage(imageURL);
            trayIcon = new TrayIcon(image, Const.TITLE_MAIN);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip(Const.TITLE_MAIN);
            PopupMenu popupMenu = new PopupMenu();
            MenuItem exit = new MenuItem(Const.EXIT);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popupMenu.add(exit);
            trayIcon.setPopupMenu(popupMenu);
            tray.add(trayIcon);
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    public static void sendMassage(String title, String massage) {
        trayIcon.displayMessage(title, massage, TrayIcon.MessageType.NONE);
    }
}
