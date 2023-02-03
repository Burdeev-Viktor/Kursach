package com.example.organizer.Controller;

import com.example.organizer.Const;

import com.example.organizer.Services.ThisUser;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.service.LessonService;
import com.example.organizer.service.TimetableService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.organizer.Controller.LessonBuildController.errorChecking;
import static com.example.organizer.Controller.LessonBuildController.settingsChoiceBoxes;

public class LessonEditController implements Initializable {
    private final LessonService lessonService = new LessonService();
    private final TimetableService timetableService = new TimetableService();
    private static Stage tableStage;
    @FXML
    private Button butClose;
    @FXML
    private Button butDel;
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
    private ChoiceBox<String> cbShift;
    @FXML
    private ChoiceBox<String> cbTime;
    @FXML
    private ChoiceBox<String> cbDayOfWeek;
    @FXML
    private ChoiceBox<String> cbNumberOfWeek;
    private LessonTimetable lessonTimetable;

    public static void setEventTimetable(Stage stage) {
        LessonEditController.tableStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            lessonTimetable.setId(this.lessonTimetable.getId());
            lessonTimetable.setIdUser(ThisUser.getId());
            timetableService.save(lessonTimetable);
            SciencesController.updateTimeTableEdit( tableStage);
            SciencesController.closeThis(event);
        });
        butDel.setOnAction(event -> {
            timetableService.delete(this.lessonTimetable);
            SciencesController.updateTimeTableEdit( tableStage);
            SciencesController.closeThis(event);
        });
    }

    public void setInfo(LessonTimetable lessonTimetable) {
        this.lessonTimetable = lessonTimetable;
        settingsChoiceBoxes(cbName,cbNumberOfWeek, cbDayOfWeek,cbTime,cbShift, cbType);
        cbName.setValue(lessonTimetable.getName());
        twTeacher.setText(lessonTimetable.getTeacher());
        twRoom.setText(lessonTimetable.getRoom());
        cbType.setValue(lessonTimetable.getType());
        switch (lessonTimetable.getDayOfWeek()) {
            case 0 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[0]);
            case 1 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[1]);
            case 2 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[2]);
            case 3 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[3]);
            case 4 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[4]);
            case 5 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[5]);
        }
        switch (lessonTimetable.getNumberOfWeek()) {
            case 0 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[0]);
            case 1 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[1]);
            case 2 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[2]);
        }
        cbName.getItems().addAll(lessonService.getAllLessonsNameByIdUser(ThisUser.getId()));
    }
}
