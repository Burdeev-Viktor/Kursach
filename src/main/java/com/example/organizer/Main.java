package com.example.organizer;

import com.example.organizer.SecondTherd.CheckingClass;
import com.example.organizer.SecondTherd.SystemTrayClass;
import com.example.organizer.model.enums.SettingSwitch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.scene.control.MenuItem;


import java.util.Objects;


@Component
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Class<?> resource = SystemTrayClass.class;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

       FXTrayIcon fxTrayIcon = new FXTrayIcon(stage, Objects.requireNonNull(resource.getResource("/com/example/organizer/img/iconSmall.png")));
       SystemTrayClass.setTrayIcon(fxTrayIcon);
        stage.setTitle(Const.TITLE_SIGN_IN);
        stage.setScene(scene);
        stage.show();
    }
}