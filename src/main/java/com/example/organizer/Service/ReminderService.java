package com.example.organizer.Service;

import com.example.organizer.Const;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс сервис для напоминания
 */

public class ReminderService {
    /**
     * Метод перевода даты из String в тип LocalDate
     * @param date - String
     * @return - LocalDate
     */
    public static LocalDate getLocalDateByString(String date) {
        String year = date.substring(0, 4);
        String mouth = date.substring(5, 7);
        String day = date.substring(8);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(mouth), Integer.parseInt(day));
    }

    /**
     * Полечение часов из стороки времени
     * @param time
     * @return
     */
    public static String getHours(String time) {
        return time.substring(0, 2);
    }
    /**
     * Полечение минут из стороки времени
     * @param time
     * @return
     */
    public static String getMinutes(String time) {
        return time.substring(3);
    }

    /**
     * Метод получения текущего времени
     * @return - время
     */
    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        return hours + Const.COLON + minutes;
    }

    /**
     * Разница между текущим временем и заданым в милисикундах
     * @param dateStart - заданное время
     * @return - милисикунды
     */
    public static long getMilSeconds(String dateStart) {
        SimpleDateFormat format = new SimpleDateFormat(Const.TINE_FORMAT);
        String dateStop = ReminderService.getCurrentTime();
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

    /**
     * Соответствие между днями недель
     * @return
     */
    public static String getTodayDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        String result = null;
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[6];
            case 2 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[0];
            case 3 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[1];
            case 4 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[2];
            case 5 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[3];
            case 6 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[4];
            case 7 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[5];
        }
        return result;
    }
}
