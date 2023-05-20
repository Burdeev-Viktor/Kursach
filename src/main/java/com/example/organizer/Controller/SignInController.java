package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.SecondTherd.CheckingClass;
import com.example.organizer.SecondTherd.SystemTrayClass;
import com.example.organizer.repository.Session;
import com.example.organizer.model.User;
import com.example.organizer.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    private final UserService userService = new UserService();
    @FXML
    private Button butSignIn;
    @FXML
    private Button butSignUp;
    @FXML
    private TextField twLogin;
    @FXML
    private PasswordField twPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        butSignUp.setOnAction(SciencesController::toSignUp);
        butSignIn.setOnAction(event -> {
            if (twLogin.getText() == null || twPassword.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_ALL_DATA);
                alert.show();
                return;
            }
            if (!userService.userIsExistsByLoginAndPassword(new User(twLogin.getText(), twPassword.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_SIGN_IN);
                alert.show();
                return;
            }

            Session.setUser(userService.findUserByLogin(twLogin.getText()));
            CheckingClass.getCheckingClass().start();
            SciencesController.toMain(event, Session.getId());
        });

    }
}
