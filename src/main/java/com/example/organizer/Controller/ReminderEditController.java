package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.Services.ThisUser;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.LessonService;
import com.example.organizer.service.ReminderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class ReminderEditController implements Initializable {
    private static final LessonService lessonService = new LessonService();
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

    static void setDataOfcb(ChoiceBox<String> cbLessonName, TextArea taQuest, ChoiceBox<String> cbSwitch, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinuts, ChoiceBox<String> cbDayOfWeek) {
        cbLessonName.getItems().setAll(lessonService.getAllLessonsNameByIdUser(ThisUser.getId()));
        taQuest.setText("");
        String[] arrayItemsOfCb = {Const.CHOICE_BOX_YES, Const.CHOICE_BOX_NO};
        cbSwitch.getItems().addAll(arrayItemsOfCb);
        cbSwitch.setValue(arrayItemsOfCb[0]);

        arrayItemsOfCb = Const.CHOICE_BOX_SETTING_SWITCH;
        cbSwitchSetting.getItems().addAll(arrayItemsOfCb);
        cbSwitchSetting.setValue(arrayItemsOfCb[0]);

        for (int i = 0; i < 24; i++) {
            if (Integer.toString(i).length() < 1) {
                cbHours.getItems().add("0" + i);
                continue;
            }
            cbHours.getItems().add(Integer.toString(i));
        }
        cbHours.setValue("00");
        for (int i = 0; i < 60; i += 5) {
            if (Integer.toString(i).length() < 1) {
                cbMinuts.getItems().add("0" + i);
                continue;
            }
            cbMinuts.getItems().add(Integer.toString(i));
        }
        cbMinuts.setValue("00");
        arrayItemsOfCb = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK;
        cbDayOfWeek.getItems().addAll(arrayItemsOfCb);
        cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);
    }

    static void disOrEnable(ChoiceBox<String> cbSwitch, Label lbSetting, Label lbDayOfWeek, Label ldtime1, Label lbTime, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinuts, ChoiceBox<String> cbDayOfWeek) {
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_NO)) {
            lbSetting.setVisible(false);
            lbDayOfWeek.setVisible(false);
            ldtime1.setVisible(false);
            lbTime.setVisible(false);
            cbSwitchSetting.setVisible(false);
            cbSwitchSetting.setDisable(true);
            cbHours.setVisible(false);
            cbHours.setDisable(true);
            cbMinuts.setVisible(false);
            cbMinuts.setDisable(true);
            cbDayOfWeek.setVisible(false);
            cbDayOfWeek.setDisable(true);
        } else {
            lbSetting.setVisible(true);
            lbDayOfWeek.setVisible(true);
            ldtime1.setVisible(true);
            lbTime.setVisible(true);
            cbSwitchSetting.setVisible(true);
            cbSwitchSetting.setDisable(false);
            cbHours.setVisible(true);
            cbHours.setDisable(false);
            cbMinuts.setVisible(true);
            cbMinuts.setDisable(false);
            cbDayOfWeek.setVisible(true);
            cbDayOfWeek.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSwitch.setOnAction(event -> {
            disOrEnable();
        });
        cbSwitchSetting.setOnAction(event -> {
            disOrEnableDayOfWeek();
        });
        butSave.setOnAction(event -> {
            if (formationReminder()) {
                SciencesController.closeThis(event);
            }
        });
        butClose.setOnAction(SciencesController::closeThis);
        butDel.setOnAction(event -> {
            reminder.setIdUser(ThisUser.getId());
            reminderService.delete( reminder);
            SciencesController.updateMainByStage(ReminderEditController.mainStage);
            butClose.fire();
        });

    }

    private void disOrEnableDayOfWeek() {
        disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }

    private boolean formationReminder() {
        if (Objects.equals(cbLessonName.getValue(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
            alert.show();
            return false;
        }
        if(paneStandart.isDisable()){
            Reminder reminderUp = new Reminder();
            reminderUp.setQuest(taQuest.getText());
            reminderUp.setLessonName(cbLessonName.getValue());
            reminderUp.setNeedWork(reminder.getNeedWork());
            reminderUp.setCloseWork(reminder.getCloseWork());
            reminderUp.setId(reminder.getId());
            reminderUp.setIdUser(reminder.getId());
            reminderService.save(reminderUp);
            SciencesController.updateMainByStage(ReminderEditController.mainStage);
            return true;
        }
        LocalDate localDate = dpDate.getValue();
        System.out.println(localDate);
        if (localDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DATE);
            alert.show();
            return false;
        }
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_YES) && Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2]) && Objects.equals(cbDayOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DAY_OF_WEEK);
            alert.show();
            return false;
        }
        Reminder reminder;
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_NO)) {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), false);
        } else if (Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2])) {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, cbSwitchSetting.getValue(), cbHours.getValue() + Const.COLON + cbMinuts.getValue(), cbDayOfWeek.getValue());
        } else {
            reminder = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, cbSwitchSetting.getValue(), cbHours.getValue() + Const.COLON + cbMinuts.getValue(), null);
        }
        reminder.setId(this.reminder.getId());
        reminderService.save(reminder);
        SciencesController.updateMainByStage(ReminderEditController.mainStage);
        return true;
    }

    private void disOrEnable() {
        disOrEnable(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
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
        setDataOfNewReminder();
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
            cbSwitchSetting.setValue(reminder.getSettingSwitch());
        }
        cbHours.setValue(ReminderService.getHours(reminder.getTime()));
        cbMinuts.setValue(ReminderService.getMinutes(reminder.getTime()));
        if (reminder.getDayOfWeek() == null) {
            cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);
        } else {
            cbDayOfWeek.setValue(reminder.getDayOfWeek());
        }
    }

    private void setDataOfNewReminder() {
        setDataOfcb(cbLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
}
