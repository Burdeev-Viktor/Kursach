package com.example.organizer.service;

import com.example.organizer.Const;
import com.example.organizer.Services.ThisUser;
import com.example.organizer.model.Lesson;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.repository.LessonRepository;
import com.example.organizer.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LessonService {
    private static final LessonRepository lessonsRepository;

    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        lessonsRepository = context.getBean(LessonRepository.class);
    }
    public List<Lesson> findAllByIdUser(Long id){
        return lessonsRepository.findAllByIdUser(id);
    }
    public String[] getAllLessonsNameByIdUser(Long id){
        List<Lesson> lessonList = findAllByIdUser(id);
        String[] names = new String[lessonList.size()];
        for (int i = 0; i < names.length; i++){
            names[i] = lessonList.get(i).getName();
        }
        return names;
    }
    public void save(Lesson lesson){
        lessonsRepository.save(lesson);
    }
    public boolean lessonIsExistsByNameAndIdUser(String name,Long id){
        return lessonsRepository.existsByNameAndIdUser(name,id);
    }
    public void delete(Lesson lesson){
        lessonsRepository.delete(lesson);
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
