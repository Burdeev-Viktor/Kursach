package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.Main;
import com.example.organizer.repository.Session;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.LessonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class SciencesController {
    private static final LessonService lessonService = new LessonService();
    public static void toSignUp(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sign-up.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 770, 400));
        stage.setTitle(Const.TITLE_SIGN_UP);
        stage.show();
    }

    public static void toSignIn(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sign-in.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 700, 400));
        stage.setTitle(Const.TITLE_SIGN_IN);
        stage.show();
    }
    public static void toMain(ActionEvent event, Long userId) {

        if(lessonService.findAllByIdUser(Session.getId()).size() == 0){
            toLessons(event);
            return;
        }
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainController.setWeekCount(0);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 685));
        stage.setMinHeight(685);
        stage.setMinWidth(1000);
        stage.setTitle(Const.TITLE_MAIN);
        ReminderEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void updateMainByEvent(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        double wight = stage.getWidth() - 16;
        double height = stage.getHeight() - 39;
        stage.setScene(new Scene(root, wight, height));
        stage.setTitle(Const.TITLE_MAIN);
        ReminderEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void toLessons(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("lessons.fxml"));
            root = loader.load();
            LessonsController lessonsController = loader.getController();
            lessonsController.setInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle(Const.TITLE_TIMETABLE);
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void toLessonsByUser() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("lessons.fxml"));
            root = loader.load();
            LessonsController lessonsController = loader.getController();
            lessonsController.setInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle(Const.TITLE_TIMETABLE);
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }

    public static void toTimeTableEdit(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle(Const.TITLE_TIMETABLE);
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }

    public static void toNewTimeTableEdit() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle(Const.TITLE_TIMETABLE);
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }

    public static void updateTimeTableEdit( Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle(Const.TITLE_TIMETABLE);
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }

    public static void toEditLesson(LessonTimetable lessonTimetable) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("lesson-timetable-edit.fxml"));
            root = loader.load();
            LessonEditController lessonEditController = loader.getController();
            lessonEditController.setInfo(lessonTimetable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 520));
        stage.setTitle(Const.TITLE_EDIT_LESSON);
        stage.show();
    }

    public static void toEditReminder(Reminder reminder) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("reminder-edit.fxml"));
            root = loader.load();
            ReminderEditController reminderEditController = loader.getController();
            reminderEditController.setInfo(reminder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 530));
        stage.setTitle(Const.TITLE_EDIT_REMINDER);
        stage.show();
    }



    public static void updateMainByStage(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root, 1250, 685));
        stage.setTitle(Const.TITLE_MAIN);
        stage.show();
    }



    public static void toAddLesson(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("build-lesson-timetable.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LessonBuildController.setEventTimetable(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 520));
        stage.setTitle(Const.TITLE_ADD_LESSON);
        stage.show();
    }

    public static void closeThis(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

}
