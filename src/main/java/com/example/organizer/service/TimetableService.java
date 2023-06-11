package com.example.organizer.service;

import com.example.organizer.customView.LessonTimetableNanoView;
import com.example.organizer.model.enums.DayOfWeek;
import com.example.organizer.model.enums.NumberWeek;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.repository.TimetableRepository;
import javafx.scene.layout.VBox;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.organizer.service.ReminderService.time;


public class TimetableService {
    private static final TimetableRepository timetableRepository;

    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        timetableRepository = context.getBean(TimetableRepository.class);
    }
    public void delete(LessonTimetable lessonTimetable){
        timetableRepository.delete(lessonTimetable);
    }
    public void save(LessonTimetable lessonTimetable){
        timetableRepository.save(lessonTimetable);
    }
    public List<LessonTimetable> findAllByIdUser(Long id){
        return timetableRepository.findAllByIdUser(id);
    }
    public List<LessonTimetable> findAllByIdUserAndNumberOfWeek(Long id, NumberWeek week){return  timetableRepository.findAllByIdUserAndNumberOfWeek(id,week.toString());}
    public List<LessonTimetable> getLessonsWeekByNumber(int week) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        List<LessonTimetable> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = findAllByIdUserAndNumberOfWeek(Session.getId(), NumberWeek.FIRST);
        } else {
            lessonsList = findAllByIdUserAndNumberOfWeek(Session.getId(), NumberWeek.SECOND);
        }
        System.out.println(ChronoUnit.WEEKS.between(firstSeptember, localeDate));
        return lessonsList;
    }
    public static DayOfWeek getTodayDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        DayOfWeek result = null;
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1 -> result = DayOfWeek.SUNDAY;
            case 2 -> result = DayOfWeek.MONDAY;
            case 3 -> result = DayOfWeek.TUESDAY;
            case 4 -> result = DayOfWeek.WEDNESDAY;
            case 5 -> result = DayOfWeek.THURSDAY;
            case 6 -> result = DayOfWeek.FRIDAY;
            case 7 -> result = DayOfWeek.SATURDAY;
        }
        return result;
    }

    public static LessonTimetable[][][] getSortLessonsTimetableAll() {
        TimetableService timetableService = new TimetableService();
        List<LessonTimetable> lessonTimetableList = timetableService.findAllByIdUser(Session.getId());
        LessonTimetable[][][] lessonTimetables = new LessonTimetable[2][6][];
        for (byte k = 0; k < 6; k++) {
            for (byte j = 0; j < 2; j++) {
                byte numberDay = k;
                byte numberWeek = j;
                List<LessonTimetable> lessonsList = lessonTimetableList
                        .stream()
                        .filter(lessonTimetable ->
                                Objects.equals(lessonTimetable.getDayOfWeek(), DayOfWeek.getSixDay()[numberDay]) &&
                                        (Objects.equals(lessonTimetable.getNumberOfWeek(), NumberWeek.values()[numberWeek]) ||
                                                Objects.equals(lessonTimetable.getNumberOfWeek(), NumberWeek.ALL)))
                        .collect(Collectors.toList());
                if (lessonsList.size() >= 1) {
                    sortLessonByTimeInDay(lessonsList);
                    lessonTimetables[j][k] = lessonsList.toArray(LessonTimetable[]::new);
                }
            }
        }
        return lessonTimetables;
    }
    public static LessonTimetable[][] getSortLessonsTimetableOneWeek(List<LessonTimetable> lessonTimetableList) {
        LessonTimetable[][] lessonTimetables = new LessonTimetable[6][];
        for (byte d = 0; d < 6; d++) {
            byte day = d;
            List<LessonTimetable> lessonsList = lessonTimetableList.stream()
                    .filter(el -> Objects.equals(el.getDayOfWeek(), DayOfWeek.getSixDay()[day]))
                    .collect(Collectors.toList());
            if (lessonsList.size() >= 1) {
                sortLessonByTimeInDay(lessonsList);
                lessonTimetables[d] = lessonsList.toArray(LessonTimetable[]::new);
            }
        }
        return lessonTimetables;
    }
    private static void sortLessonByTimeInDay(List<LessonTimetable> lessonsList){
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < lessonsList.size() - 1; i++) {
                if (time(lessonsList.get(i).getTime(), lessonsList.get(i + 1).getTime())) {
                    flag = true;
                    Collections.swap(lessonsList, i, i + 1);
                }
            }
        }
    }
    public void displayLessonsOneWeek(VBox[] vBoxes,int weekCount){
        LessonTimetable[][] lessonTimetableList = TimetableService.getSortLessonsTimetableOneWeek(getLessonsWeekByNumber(weekCount));
        for (int d = 0; d < vBoxes.length ; d++){
            if(lessonTimetableList[d] == null){
                continue;
            }
            for (int l = 0;l < lessonTimetableList[d].length; l++){
                vBoxes[d].getChildren().add(new LessonTimetableNanoView(lessonTimetableList[d][l]));
                vBoxes[d].setSpacing(2);
            }
        }

    }

}
