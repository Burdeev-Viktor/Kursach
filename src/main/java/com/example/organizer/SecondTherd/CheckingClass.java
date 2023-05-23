package com.example.organizer.SecondTherd;

import com.example.organizer.Const;
import com.example.organizer.model.enums.DayOfWeek;
import com.example.organizer.model.enums.SettingSwitch;
import com.example.organizer.repository.Session;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.ReminderService;
import com.example.organizer.service.TimetableService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckingClass extends Thread {
    private final ReminderService reminderService = new ReminderService();
    private static CheckingClass checkingClass;
    private volatile boolean running;
    public CheckingClass() {
        running = true;
    }
    public static CheckingClass getCheckingClass() {
        if (checkingClass == null) {
            checkingClass = new CheckingClass();
        }
        return checkingClass;
    }
    public void run() {

        running = true;
        while (running) {
            long timeSleep = Const.MINUTE_IN_MILLISECONDS;
            List<Reminder> reminderArrayList = reminderService.findAllRemindersEnableByIdUser(Session.getId());
            DayOfWeek today = TimetableService.getTodayDayOfWeek();
            reminderArrayList = reminderArrayList.stream().filter(reminder -> {
                if(Objects.equals(reminder.getSettingSwitch(), SettingSwitch.EVERYDAY))
                {
                    return true;
                }else return Objects.equals(reminder.getDayOfWeek(), today);
            }).collect(Collectors.toList());

            for (Reminder reminder : reminderArrayList) {
                long k = ReminderService.getMilSeconds(reminder.getTime());
                if (k < 0) {
                    continue;
                }
                if (k <= Const.MINUTE_IN_MILLISECONDS) {
                    SystemTrayClass.sendMassage(reminder.getLessonName(), reminder.getQuest());
                    continue;
                }
                if (k < timeSleep) {
                    timeSleep = ReminderService.getMilSeconds(reminder.getTime()) - Const.MINUTE_IN_MILLISECONDS / 2;
                }
            }
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
