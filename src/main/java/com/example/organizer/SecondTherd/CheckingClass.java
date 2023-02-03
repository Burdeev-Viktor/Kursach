package com.example.organizer.SecondTherd;

import com.example.organizer.Const;
import com.example.organizer.Services.ThisUser;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.ReminderService;
import com.example.organizer.service.TimetableService;

import java.util.List;
import java.util.Objects;

public class CheckingClass extends Thread {
    private final ReminderService reminderService = new ReminderService();
    private static CheckingClass checkingClass;
    private volatile boolean running;
    private CheckingClass() {
        running = true;
    }
    public static void running() {
        checkingClass.start();
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
            long timeSleep = Const.HOUR_IN_MILLISECONDS;
            List<Reminder> reminderArrayList = reminderService.findAllRemindersEnableByIdUser(ThisUser.getId());
            String today = TimetableService.getTodayDayOfWeek();
            for (int i = 0; i < reminderArrayList.size(); i++) {
                if (!(Objects.equals(reminderArrayList.get(i).getDayOfWeek(), today) || Objects.equals(reminderArrayList.get(i).getSettingSwitch(), "Каждый день"))) {
                    reminderArrayList.remove(i);
                    i--;
                }
            }
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
