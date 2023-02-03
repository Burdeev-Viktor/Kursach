package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.model.User;
import com.example.organizer.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private final UserService userService = new UserService();
    @FXML
    private Button butSignIn;
    @FXML
    private Button butSignUp;
    @FXML
    private TextField twLogin;
    @FXML
    private TextField twName;
    @FXML
    private PasswordField twPassword;
    @FXML
    private PasswordField twSecondPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butSignIn.setOnAction(SciencesController::toSignIn);
        butSignUp.setOnAction(event -> {
            if (Objects.equals(twLogin.getText(), "") || Objects.equals(twName.getText(), "") || Objects.equals(twPassword.getText(), "") || Objects.equals(twSecondPassword.getText(), "")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_ALL_DATA);
                alert.show();
                return;
            }
            if (!Objects.equals(twPassword.getText(), twSecondPassword.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_PASSWORD_NOT_SUPPRESS);
                alert.show();
                return;
            }
            if (twPassword.getText().length() < Const.LENGTH_OF_PASSWORD) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_PASSWORD_NOT_CURE);
                alert.show();
                return;
            }
            if (userService.userIsExistsByLogin(twLogin.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_USERNAME_IS_EXIST);
                alert.show();
                return;
            }
            User user = new User(twLogin.getText(), twPassword.getText(), twName.getText());
            userService.save(user);
            SciencesController.toSignIn(event);
        });

    }
}
