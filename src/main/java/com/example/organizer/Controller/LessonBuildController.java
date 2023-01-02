package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.Repositories.LessonTimetableRepo;
import com.example.organizer.Service.ThisUser;
import com.example.organizer.model.LessonTimetable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LessonBuildController implements Initializable {
    private static ActionEvent eventTimetable;
    @FXML
    private Button butClose;
    @FXML
    private Button butSave;
    @FXML
    private ChoiceBox<String> cbName;
    @FXML
    private TextField twTeacher;
    @FXML
    private TextField twRoom;
    @FXML
    private ChoiceBox<String> cbType;
    @FXML
    private ChoiceBox<String> cbDayOfWeek;
    @FXML
    private ChoiceBox<String> cbShift;
    @FXML
    private ChoiceBox<String> cbTime;
    @FXML
    private ChoiceBox<String> cbNumberOfWeek;

    public static void setEventTimetable(ActionEvent eventTimetable) {
        LessonBuildController.eventTimetable = eventTimetable;
    }

    static void settingsChoiceBoxes(ChoiceBox<String> cbName,ChoiceBox<String> cbNumberOfWeek, ChoiceBox<String> cbDayOfWeek, ChoiceBox<String> cbTime,ChoiceBox<String> cbShift,ChoiceBox<String> cbType) {
        String[] array = Const.CHOICE_BOX_NUMBER_OF_WEEK;
        cbNumberOfWeek.getItems().addAll(array);
        cbNumberOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbNumberOfWeek.setStyle("-fx-font-size: 18");
        cbNumberOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);

        array = Const.CHOICE_BOX_SIX_DAYS_OF_WEEK;
        cbDayOfWeek.getItems().addAll(array);
        cbDayOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbDayOfWeek.setStyle("-fx-font-size: 18");
        cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);

        array = Const.TYPE_OF_LESSON;
        cbType.getItems().addAll(array);
        cbType.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbType.setStyle("-fx-font-size: 18");
        cbType.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);

        array = LessonRepo.getAllLessonsNameByUserId(ThisUser.getId());
        cbName.getItems().addAll(array);
        cbName.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbName.setStyle("-fx-font-size: 18");
        
        array = new String[]{"Первая", "Вторая"};
        cbShift.getItems().addAll(array);
        cbShift.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbShift.setStyle("-fx-font-size: 18");
        cbShift.setValue(array[0]);
        cbShift.setOnAction(event -> {
            if(Objects.equals(cbShift.getValue(), "Первая")){
                cbTime.getItems().removeAll();
                cbTime.setValue(Const.TIME_OF_START_LESSON_FIRST[0]);
                cbTime.getItems().setAll(Const.TIME_OF_START_LESSON_FIRST);
            }else if (Objects.equals(cbShift.getValue(), "Вторая")){
                cbTime.getItems().removeAll();
                cbTime.setValue(Const.TIME_OF_START_LESSON_SECOND[0]);
                cbTime.getItems().setAll(Const.TIME_OF_START_LESSON_SECOND);
            }


        });

        array = Const.TIME_OF_START_LESSON_FIRST;
        cbTime.getItems().addAll(array);
        cbTime.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbTime.setStyle("-fx-font-size: 18");
        cbTime.setValue(array[0]);

        
        
    }

    public static boolean errorChecking(ChoiceBox<String> cbName, TextField twTeacher, TextField twRoom, ChoiceBox<String> cbDayOfWeek, ChoiceBox<String> cbNumberOfWeek, ChoiceBox<String> cbType) {
        return !(!Objects.equals(cbName.getValue(), "") ||
                !Objects.equals(twTeacher.getText(), "") ||
                !Objects.equals(twRoom.getText(), "") ||
                !Objects.equals(cbDayOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX) ||
                !Objects.equals(cbNumberOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX) ||
                !Objects.equals(cbType.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingsChoiceBoxes(cbName, cbNumberOfWeek, cbDayOfWeek,cbTime,cbShift, cbType);
        butClose.setOnAction(SciencesController::closeThis);
        butSave.setOnAction(event -> {
            if (errorChecking(cbName, twTeacher, twRoom, cbDayOfWeek, cbNumberOfWeek, cbType)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_ALL_DATA);
                alert.show();
                return;
            }
            LessonTimetable lessonTimetable = new LessonTimetable(cbName.getValue(), twTeacher.getText(), twRoom.getText(),cbTime.getValue(), cbType.getValue(), -1, -1);
            switch (cbDayOfWeek.getValue()) {
                case "Понедельник" -> lessonTimetable.setDayOfWeek(0);
                case "Вторник" -> lessonTimetable.setDayOfWeek(1);
                case "Среда" -> lessonTimetable.setDayOfWeek(2);
                case "Четверг" -> lessonTimetable.setDayOfWeek(3);
                case "Пятница" -> lessonTimetable.setDayOfWeek(4);
                case "Суббота" -> lessonTimetable.setDayOfWeek(5);
            }
            switch (cbNumberOfWeek.getValue()) {
                case "Первая" -> lessonTimetable.setNumberOfWeek(0);
                case "Вторая" -> lessonTimetable.setNumberOfWeek(1);
                case "Каждую" -> lessonTimetable.setNumberOfWeek(2);
            }
            lessonTimetable.setIdUser(ThisUser.getId());
            LessonTimetableRepo.addLessonTimetableByIdUser(lessonTimetable);
            SciencesController.toTimeTableEdit(eventTimetable);
            SciencesController.closeThis(event);
        });

    }
}
