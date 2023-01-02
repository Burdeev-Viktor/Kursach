package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.CustomView.LessonTimetableNanoView;
import com.example.organizer.CustomView.ReminderView;
import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.SecondTherd.CheckingClass;
import com.example.organizer.Service.LessonService;
import com.example.organizer.Service.ThisUser;
import com.example.organizer.Service.TimetableService;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.model.Reminder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static int weekCount;
    @FXML
    private Button butBack;
    @FXML
    private Button butClose;
    @FXML
    private Button butNext;
    @FXML
    private MenuItem mnTimeTable;
    @FXML
    private MenuItem mnItemLessons;
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
    private AnchorPane paneStandart;
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
        mnTimeTable.setOnAction(event -> {
            SciencesController.toNewTimeTableEdit();
            butClose.setDisable(false);
            butClose.fire();
        });
        mnItemLessons.setOnAction(event -> {
            SciencesController.toLessonsByUser();
            butClose.setDisable(false);
            butClose.fire();
        });
        cbSwitch.setOnAction(event -> {
            disOrEnable();
        });

        cbSwitchSetting.setOnAction(event -> {
            disOrEnableDayOfWeek();
        });
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
        paneStandart.setVisible(false);
        paneStandart.setDisable(true);
        paneLab.setVisible(true);
        paneLab.setDisable(false);
        twCountLab.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    twCountLab.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    private void setStandard() {
        paneStandart.setVisible(true);
        paneStandart.setDisable(false);
        paneLab.setVisible(false);
        paneLab.setDisable(true);
    }

    private void formationReminder() {
        if (Objects.equals(cbLessonName.getValue(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
            alert.show();
            return;
        }
        if(!paneLab.isDisable()){
            Reminder reminder = new Reminder();
            reminder.setNeedWork(Integer.parseInt(twCountLab.getText()));
            reminder.setSwitchR(false);
            reminder.setLessonName(cbLessonName.getValue());
            reminder.setQuest(taQuest.getText());
            reminder.setIdUser(ThisUser.getId());
            ReminderRepo.addReminderByIdUser(reminder);
            return;
        }
        LocalDate localDate = dpDate.getValue();
        System.out.println(localDate);
        if (localDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DATE);
            alert.show();
            return;
        }
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_YES) && Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2]) && Objects.equals(cbDayOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DAY_OF_WEEK);
            alert.show();
            return;
        }
        Reminder reminder = null;
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_NO)) {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), false);
        } else if (Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2])) {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, cbSwitchSetting.getValue(), cbHours.getValue() + Const.COLON + cbMinuts.getValue(), cbDayOfWeek.getValue());
        } else {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, cbSwitchSetting.getValue(), cbHours.getValue() + Const.COLON + cbMinuts.getValue(), null);
        }
        if (ReminderRepo.reminderTableIsExists()) {
            ReminderRepo.createReminderTable();
        }
        reminder.setIdUser(ThisUser.getId());
        ReminderRepo.addReminderByIdUser(reminder);
        setDataOfNewReminder();
    }

    private void setReminders() {
        if (ReminderRepo.reminderTableIsExists()) {
            ReminderRepo.createReminderTable();
        }
        ArrayList<Reminder> reminderArrayList = null;

        reminderArrayList = ReminderRepo.getAllRemindersByUser(ThisUser.getId());

        for (Reminder reminder : reminderArrayList) {
            ReminderView customControl = new ReminderView(reminder);
            vbReminders.getChildren().add(customControl);
        }

    }

    private void setDataOfNewReminder() {
        ReminderEditController.setDataOfcb(cbLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);

    }

    private void setWeek() {
        VBox[] vBoxes = {
                vb0, vb1, vb2, vb3, vb4, vb5
        };
        LessonTimetable[][] lessonTimetableList = TimetableService.getSortLessonsTimetableOneWeek(LessonService.getLessonsThisWeek(weekCount));
        for (int d = 0; d < 6 ; d++){
            if(lessonTimetableList[d] == null){
                continue;
            }
            for (int l = 0;l < lessonTimetableList[d].length; l++){
                vBoxes[d].getChildren().add(new LessonTimetableNanoView(lessonTimetableList[d][l]));
                vBoxes[d].setSpacing(2);
            }
        }
    }

    private void disOrEnableDayOfWeek() {
        ReminderEditController.disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }

    private void disOrEnable() {
        ReminderEditController.disOrEnable(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
}
