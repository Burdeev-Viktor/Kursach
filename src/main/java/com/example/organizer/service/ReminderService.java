package com.example.organizer.service;


import com.example.organizer.Const;
import com.example.organizer.SecondTherd.CheckingClass;
import com.example.organizer.model.Reminder;
import com.example.organizer.model.enums.SettingSwitch;
import com.example.organizer.model.enums.DayOfWeek;
import com.example.organizer.repository.ReminderRepository;
import com.example.organizer.repository.Session;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.*;

public class ReminderService {
    private static final ReminderRepository reminderRepository;
    private static final LessonService lessonService = new LessonService();
    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        reminderRepository = context.getBean(ReminderRepository.class);
    }
    public List<Reminder> findAllRemindersEnableByIdUser(Long id){
        return reminderRepository.findAllBySwitchRAndIdUser(true,id);
    }
    public void delete(Reminder reminder){
        reminderRepository.delete(reminder);
    }
    public void save(Reminder reminder){
        reminderRepository.save(reminder);
        CheckingClass.getCheckingClass().setRunning(false);
        CheckingClass.getCheckingClass().start();
    }
    public List<Reminder> findAllByIdUser(Long id){
        return reminderRepository.findAllByIdUser(id);
    }
    public static boolean time(String time1,String time2){
        String[] time1array = time1.split(Const.COLON);
        String[] time2array = time2.split(Const.COLON);
        return ((Integer.parseInt(time1array[0])) >= (Integer.parseInt(time2array[0])));
    }
    public static LocalDate getLocalDateByString(String date) {
        String year = date.substring(0, 4);
        String mouth = date.substring(5, 7);
        String day = date.substring(8);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(mouth), Integer.parseInt(day));
    }

    public static String getHours(String time) {
        return time.substring(0, 2);
    }

    public static String getMinutes(String time) {
        return time.substring(3);
    }

    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        return hours + Const.COLON + minutes;
    }

    public static long getMilSeconds(String dateStart) {
        SimpleDateFormat format = new SimpleDateFormat(Const.TINE_FORMAT);
        String dateStop = getCurrentTime();
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert d1 != null;
        assert d2 != null;
        return d1.getTime() - d2.getTime();
    }
    public boolean createNewReminder(ChoiceBox<String> cbLessonName,
                                     AnchorPane paneLab, TextField twCountLab,
                                     TextArea taQuest, DatePicker dpDate,
                                     ChoiceBox<String> cbSwitch,
                                     ChoiceBox<String> cbSwitchSetting,
                                     ChoiceBox<String> cbDayOfWeek,
                                     ChoiceBox<String> cbMinutes,
                                     ChoiceBox<String> cbHours){
        if (Objects.equals(cbLessonName.getValue(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
            alert.show();
            return false;
        }
        if(!paneLab.isDisable()){
            Reminder reminder = new Reminder();
            reminder.setNeedWork(Integer.parseInt(twCountLab.getText()));
            reminder.setSwitchR(false);
            reminder.setLessonName(cbLessonName.getValue());
            reminder.setQuest(taQuest.getText());
            reminder.setIdUser(Session.getId());
            save(reminder);
            return true;
        }
        Reminder reminder = checkFormatReminders(cbLessonName, taQuest, dpDate, cbSwitch, cbSwitchSetting, cbDayOfWeek, cbMinutes, cbHours);
        if (reminder == null){
            return false;
        }
        reminder.setIdUser(Session.getId());
        save(reminder);
        return true;
    }
    public boolean updateReminder(ChoiceBox<String> cbLessonName,
                                  AnchorPane paneStandard,
                                  TextArea taQuest, DatePicker dpDate,
                                  ChoiceBox<String> cbSwitch,
                                  ChoiceBox<String> cbSwitchSetting,
                                  ChoiceBox<String> cbDayOfWeek,
                                  ChoiceBox<String> cbMinutes,
                                  ChoiceBox<String> cbHours,
                                  Reminder reminder){
        if (Objects.equals(cbLessonName.getValue(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
            alert.show();
            return false;
        }
        if(paneStandard.isDisable()){
            Reminder reminderUp = new Reminder();
            reminderUp.setQuest(taQuest.getText());
            reminderUp.setLessonName(cbLessonName.getValue());
            reminderUp.setNeedWork(reminder.getNeedWork());
            reminderUp.setCloseWork(reminder.getCloseWork());
            reminderUp.setId(reminder.getId());
            reminderUp.setIdUser(reminder.getId());
            reminderUp.setIdUser(Session.getId());
            save(reminderUp);
            return true;
        }
        Reminder reminderUp = checkFormatReminders(cbLessonName, taQuest, dpDate, cbSwitch, cbSwitchSetting, cbDayOfWeek, cbMinutes, cbHours);
        if (reminderUp == null){
            return false;
        }
        reminderUp.setIdUser(Session.getId());
        reminderUp.setId(reminder.getId());
        save(reminderUp);
        return true;
    }
    private Reminder checkFormatReminders(ChoiceBox<String> cbLessonName,
                                         TextArea taQuest, DatePicker dpDate,
                                         ChoiceBox<String> cbSwitch,
                                         ChoiceBox<String> cbSwitchSetting,
                                         ChoiceBox<String> cbDayOfWeek,
                                         ChoiceBox<String> cbMinutes,
                                         ChoiceBox<String> cbHours){
        LocalDate localDate = dpDate.getValue();
        System.out.println(localDate);
        if (localDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DATE);
            alert.show();
            return null;
        }
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_YES) && Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2]) && Objects.equals(cbDayOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Const.MESSAGE_ERROR_NOT_DAY_OF_WEEK);
            alert.show();
            return null;
        }
        Reminder reminderUp;
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_NO)) {
            reminderUp = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), false);
        } else if (Objects.equals(cbSwitchSetting.getValue(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2])) {
            reminderUp = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, SettingSwitch.getInstance(cbSwitchSetting.getValue()), cbHours.getValue() + Const.COLON + cbMinutes.getValue(), DayOfWeek.valueOf(cbDayOfWeek.getValue()));
        } else {
            reminderUp = new Reminder(cbLessonName.getValue(), taQuest.getText(), localDate.toString(), true, SettingSwitch.getInstance(cbSwitchSetting.getValue()), cbHours.getValue() + Const.COLON + cbMinutes.getValue(), null);
        }
        return reminderUp;
    }
    public void defaultDisplayInfo(ChoiceBox<String> cbLessonName, TextArea taQuest, ChoiceBox<String> cbSwitch, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinutes, ChoiceBox<String> cbDayOfWeek) {
        cbLessonName.getItems().setAll(lessonService.getAllLessonsNameByIdUser(Session.getId()));
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
                cbMinutes.getItems().add("0" + i);
                continue;
            }
            cbMinutes.getItems().add(Integer.toString(i));
        }
        cbMinutes.setValue("00");
        arrayItemsOfCb = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK;
        cbDayOfWeek.getItems().addAll(arrayItemsOfCb);
        cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);
    }
    public void disOrEnableLabPane(ChoiceBox<String> cbSwitch, Label lbSetting, Label lbDayOfWeek, Label ldtime1, Label lbTime, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinutes, ChoiceBox<String> cbDayOfWeek) {
        if (Objects.equals(cbSwitch.getValue(), Const.CHOICE_BOX_NO)) {
            lbSetting.setVisible(false);
            lbDayOfWeek.setVisible(false);
            ldtime1.setVisible(false);
            lbTime.setVisible(false);
            cbSwitchSetting.setVisible(false);
            cbSwitchSetting.setDisable(true);
            cbHours.setVisible(false);
            cbHours.setDisable(true);
            cbMinutes.setVisible(false);
            cbMinutes.setDisable(true);
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
            cbMinutes.setVisible(true);
            cbMinutes.setDisable(false);
            cbDayOfWeek.setVisible(true);
            cbDayOfWeek.setDisable(false);
        }
    }

}
