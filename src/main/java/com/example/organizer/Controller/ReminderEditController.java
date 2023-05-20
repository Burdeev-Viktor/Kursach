package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.repository.Session;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.ReminderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ReminderEditController implements Initializable {
    private final ReminderService reminderService= new ReminderService();
    private static Stage mainStage;
    @FXML
    private Button butSave;
    @FXML
    private Button butLab;
    @FXML
    private Button butClose;
    @FXML
    private Button butDel;
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
    private Label lbClose;
    @FXML
    private Label lbNeed;
    @FXML
    private AnchorPane paneStandart;
    @FXML
    private AnchorPane paneLab;
    @FXML
    private Label ldtime1;
    @FXML
    private Label lbDayOfWeek;
    private Reminder reminder;

    public static void setEventTimetable(Stage stage) {
        ReminderEditController.mainStage = stage;
    }

    static void disOrEnableDayOfWeek(ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbDayOfWeek, Label lbDayOfWeek) {
        if (Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_SETTING_SWITCH[0])) {
            cbDayOfWeek.setVisible(false);
            cbDayOfWeek.setDisable(true);
            lbDayOfWeek.setVisible(false);
        } else {
            cbDayOfWeek.setVisible(true);
            cbDayOfWeek.setDisable(false);
            lbDayOfWeek.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSwitch.setOnAction(event -> disOrEnable());
        cbSwitchSetting.setOnAction(event -> disOrEnableDayOfWeek());
        butSave.setOnAction(event -> {
            if (formationReminder()) {
                SciencesController.closeThis(event);
            }
        });
        butClose.setOnAction(SciencesController::closeThis);
        butDel.setOnAction(event -> {
            reminder.setIdUser(Session.getId());
            reminderService.delete( reminder);
            SciencesController.updateMainByStage(ReminderEditController.mainStage);
            butClose.fire();
        });

    }

    private void disOrEnableDayOfWeek() {
        disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }

    private boolean formationReminder() {
        boolean flag = reminderService.updateReminder( cbLessonName, paneStandart, taQuest,  dpDate, cbSwitch, cbSwitchSetting, cbDayOfWeek, cbMinuts, cbHours, reminder);
        if(flag) {
            SciencesController.updateMainByStage(ReminderEditController.mainStage);
            return true;
        }
        return false;
    }

    private void disOrEnable() {
        reminderService.disOrEnableLabPane(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
    private void setLab(){
        paneStandart.setVisible(false);
        paneStandart.setDisable(true);
        paneLab.setVisible(true);
        paneLab.setDisable(false);
        lbNeed.setText("Всего: " + reminder.getNeedWork());
        lbClose.setText("Сдано: " + reminder.getCloseWork());
        butLab.setOnAction(event -> {
            if(reminder.getNeedWork() == reminder.getCloseWork()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Все работы сданы");
                alert.show();
                return;
            }
            reminder.setCloseWork(reminder.getCloseWork() + 1);
            lbClose.setText("Сдано: " + reminder.getCloseWork());
        });
    }
    public void setInfo(Reminder reminder) {
        reminderService.defaultDisplayInfo(cbLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
        this.reminder = reminder;
        cbLessonName.setValue(reminder.getLessonName());
        taQuest.setText(reminder.getQuest());
        if(reminder.getDate() == null){
            setLab();
            return;
        }

        dpDate.setValue(ReminderService.getLocalDateByString(reminder.getDate()));
        taQuest.setText(reminder.getQuest());
        if (reminder.isSwitchR()) {
            cbSwitch.setValue(Const.CHOICE_BOX_YES);
        } else {
            cbSwitch.setValue(Const.CHOICE_BOX_NO);
        }
        if (reminder.getSettingSwitch() == null) {
            cbSwitchSetting.setValue(Const.CHOICE_BOX_SETTING_SWITCH[1]);
        } else {
            cbSwitchSetting.setValue(reminder.getSettingSwitch().getSetting());
        }
        cbHours.setValue(ReminderService.getHours(reminder.getTime()));
        cbMinuts.setValue(ReminderService.getMinutes(reminder.getTime()));
        if (reminder.getDayOfWeek() == null) {
            cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);
        } else {
            cbDayOfWeek.setValue(reminder.getDayOfWeek().getDay());
        }
    }
}
