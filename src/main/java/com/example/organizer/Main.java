package com.example.organizer;

import com.example.organizer.model.User;
import com.example.organizer.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class Main extends Application {
    ;
    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {


//        UserService userService = new UserService();
//        User user = new User("123","123","123");
//       userService.save(user);



        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle(Const.TITLE_SIGN_IN);
        stage.setScene(scene);
        stage.show();
    }
}