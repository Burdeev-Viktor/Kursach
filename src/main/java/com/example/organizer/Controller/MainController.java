package com.example.organizer.Controller;


import com.example.organizer.CustomView.ReminderView;
import com.example.organizer.SecondTherd.CheckingClass;
import com.example.organizer.repository.Session;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.ReminderService;
import com.example.organizer.service.TimetableService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final ReminderService reminderService= new ReminderService();
    private final TimetableService timetableService = new TimetableService();
    private static int weekCount;
    @FXML
    public Button butStatistic;
    @FXML
    private Button butLessons;
    @FXML
    private Button butTimetable;
    @FXML
    private Button butBack;
    @FXML
    private Button butClose;
    @FXML
    private Button butNext;
    @FXML
    private VBox vb0;
    @FXML
    private VBox vb1;
    @FXML
    private VBox vb2;
    @FXML
    private VBox vb3;
    @FXML
    private VBox vb4;
    @FXML
    private VBox vb5;
    @FXML
    private Button butAdd;
    @FXML
    private ChoiceBox<String> cbLessonName;
    @FXML
    private TextArea taQuest;
    @FXML
    private DatePicker dpDate;
    @FXML
    private ChoiceBox<String> cbSwitch;
    @FXML
    private ChoiceBox<String> cbSwitchSetting;
    @FXML
    private ChoiceBox<String> cbHours;
    @FXML
    private ChoiceBox<String> cbMinuts;
    @FXML
    private ChoiceBox<String> cbDayOfWeek;
    @FXML
    private Label lbSetting;
    @FXML
    private Label lbTime;
    @FXML
    private Label ldtime1;
    @FXML
    private AnchorPane paneLab;
    @FXML
    private AnchorPane paneStandard;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField twCountLab;
    @FXML
    private Label lbDayOfWeek;
    @FXML
    private VBox vbReminders;

    public static void setWeekCount(int weekCount) {
        MainController.weekCount = weekCount;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dpDate.getEditor().setDisable(true);
        dpDate.getEditor().setOpacity(1);
        checkBox.setOnAction(event -> {
            if((checkBox.isSelected())){
                setLab();
            }else {
                setStandard();
            }
        });
        butClose.setOnAction(SciencesController::closeThis);
        butClose.setDisable(true);
        butBack.setOnAction(event -> {
            weekCount--;
            SciencesController.updateMainByEvent(event);
        });
        butNext.setOnAction(event -> {
            weekCount++;
            SciencesController.updateMainByEvent(event);

        });
        butLessons.setOnAction(event -> {
            SciencesController.toLessonsByUser();
            butClose.setDisable(false);
            butClose.fire();
        });
        butTimetable.setOnAction(event -> {
            SciencesController.toNewTimeTableEdit();
            butClose.setDisable(false);
            butClose.fire();
        });
        butStatistic.setOnAction(event -> {
            SciencesController.toStatistic();
        });
        cbSwitch.setOnAction(event -> disOrEnable());

        cbSwitchSetting.setOnAction(event -> disOrEnableDayOfWeek());
        butAdd.setOnAction(event -> {
            formationReminder();
            CheckingClass.getCheckingClass().setRunning(false);
            CheckingClass.getCheckingClass().start();
            SciencesController.updateMainByEvent(event);
        });
        setWeek();
        setDataOfNewReminder();
        setReminders();
    }
    private void setLab() {
        paneStandard.setVisible(false);
        paneStandard.setDisable(true);
        paneLab.setVisible(true);
        paneLab.setDisable(false);
        twCountLab.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                twCountLab.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    private void setStandard() {
        paneStandard.setVisible(true);
        paneStandard.setDisable(false);
        paneLab.setVisible(false);
        paneLab.setDisable(true);
    }

    private void formationReminder() {
        boolean flag = reminderService.createNewReminder(cbLessonName, paneLab,
                twCountLab, taQuest,  dpDate, cbSwitch, cbSwitchSetting, cbDayOfWeek, cbMinuts, cbHours);
        if(flag) setDataOfNewReminder();
    }

    private void setReminders() {
        List<Reminder> reminderArrayList= reminderService.findAllByIdUser(Session.getId());
        for (Reminder reminder : reminderArrayList) {
            ReminderView customControl = new ReminderView(reminder);
            vbReminders.getChildren().add(customControl);
        }

    }

    private void setDataOfNewReminder() {
        reminderService.defaultDisplayInfo(cbLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }

    private void setWeek() {
        timetableService.displayLessonsOneWeek(new VBox[]{vb0, vb1, vb2, vb3, vb4, vb5},weekCount);
    }

    private void disOrEnableDayOfWeek() {
        ReminderEditController.disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }

    private void disOrEnable() {
        reminderService.disOrEnableLabPane(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
}
