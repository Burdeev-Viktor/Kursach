package com.example.organizer.Service;

import com.example.organizer.Controller.SciencesController;
import com.example.organizer.Repositories.LessonTimetableRepo;
import com.example.organizer.model.LessonTimetable;

import java.util.ArrayList;
import java.util.Collections;

public class TimetableService {
    public static LessonTimetable[][][] getSortLessonsTimetableAll() {
        ArrayList<LessonTimetable> lessonTimetableList = LessonTimetableRepo.getAllLessonsTimetableByUserId(ThisUser.getId());
        LessonTimetable[][][] lessonTimetables = new LessonTimetable[2][6][];
        for (int k = 0; k < 6; k++) {
            for (int j = 0; j < 2; j++) {
                ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
                for (LessonTimetable lessonTimetable : lessonTimetableList) {
                    if (lessonTimetable.getDayOfWeek() == k && (lessonTimetable.getNumberOfWeek() == j || lessonTimetable.getNumberOfWeek() == 2)) {
                        lessonsList.add(lessonTimetable);
                    }
                }

                if (lessonsList.size() > 1) {
                    sortLessonByTimeInDay(lessonsList);
                    LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                    for (int i = 0; i < lessonsList.size(); i++) {
                        timetables[i] = lessonsList.get(i);
                    }
                    lessonTimetables[j][k] = timetables;
                } else {
                    if (lessonsList.size() == 1) {
                        LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                        for (int i = 0; i < lessonsList.size(); i++) {
                            timetables[i] = lessonsList.get(i);
                        }
                        lessonTimetables[j][k] = timetables;
                    } else {
                        lessonTimetables[j][k] = null;
                    }
                }


            }
        }
        return lessonTimetables;
    }

    public static LessonTimetable[][] getSortLessonsTimetableOneWeek(ArrayList<LessonTimetable> lessonTimetableList) {
        LessonTimetable[][] lessonTimetables = new LessonTimetable[6][];
        for (int d = 0; d < 6; d++) {
            ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
            for (LessonTimetable lessonTimetable : lessonTimetableList) {
                if (lessonTimetable.getDayOfWeek() == d ) {
                    lessonsList.add(lessonTimetable);
                }
            }

            if (lessonsList.size() > 1) {
                sortLessonByTimeInDay(lessonsList);
                LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                for (int i = 0; i < lessonsList.size(); i++) {
                    timetables[i] = lessonsList.get(i);
                }
                lessonTimetables[d] = timetables;
            } else {
                if (lessonsList.size() == 1) {
                    LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                    for (int i = 0; i < lessonsList.size(); i++) {
                        timetables[i] = lessonsList.get(i);
                    }
                    lessonTimetables[d] = timetables;
                } else {
                    lessonTimetables[d] = null;
                }


            }
        }
        return lessonTimetables;
    }
private static ArrayList<LessonTimetable> sortLessonByTimeInDay(ArrayList<LessonTimetable> lessonsList){
    boolean flag = true;
    while (flag) {
        flag = false;
        for (int i = 0; i < lessonsList.size() - 1; i++) {
            if (LessonService.time(lessonsList.get(i).getTime(), lessonsList.get(i + 1).getTime())) {
                flag = true;
                Collections.swap(lessonsList, i, i + 1);
            }
        }
    }
    return lessonsList;
}
}
