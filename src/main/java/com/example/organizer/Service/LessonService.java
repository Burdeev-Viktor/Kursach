package com.example.organizer.Service;

import com.example.organizer.Const;
import com.example.organizer.Controller.SciencesController;
import com.example.organizer.Repositories.LessonTimetableRepo;
import com.example.organizer.model.LessonTimetable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Класс сервис Пар
 */
public class LessonService {
    /**
     * Метод получения списка пар на требуемой недели
     * @param week - номер недели после 01,09,ГГГГ
     * @return - список пар
     */
    public static ArrayList<LessonTimetable> getLessonsThisWeek(int week) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        ArrayList<LessonTimetable> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = LessonTimetableRepo.getLessonsTimetableByUserByWeek(ThisUser.getId(), 0);
        } else {
            lessonsList = LessonTimetableRepo.getLessonsTimetableByUserByWeek(ThisUser.getId(), 1);
        }
        System.out.println(ChronoUnit.WEEKS.between(firstSeptember, localeDate));
        return lessonsList;
    }

    /**
     * Метод проверяет больше ли время1 или время2
     * @param time1 - время1
     * @param time2  - время2
     * @return
     */
    public static boolean time(String time1,String time2){
        String[] time1array = time1.split(Const.COLON);
        String[] time2array = time2.split(Const.COLON);
        boolean flag = ((Integer.parseInt(time1array[0])) >= (Integer.parseInt(time2array[0])));
        return flag;
    }
}
