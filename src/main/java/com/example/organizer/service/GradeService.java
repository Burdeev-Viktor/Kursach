package com.example.organizer.service;

import com.example.organizer.model.Grade;
import com.example.organizer.model.Reminder;
import com.example.organizer.repository.GradeRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GradeService {
    private static final GradeRepository gradeRepository;
    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        gradeRepository = context.getBean(GradeRepository.class);
    }
    public void save(Grade grade){
        gradeRepository.save(grade);
    }
    public List<Grade> getGradesByReminder(Reminder reminder){
        return gradeRepository.getGradesOfReminderId(reminder.getId());
    }
    public String calColor(Reminder reminder){
        List<Grade> gradeList = getGradesByReminder(reminder);
        if (gradeList.size() == 0){
            return "#bd2525";
        }
        long sum = gradeList.stream().mapToLong(Grade::getGrade).sum();
        double mean = (double) sum / gradeList.size();
        if(0 <= mean && mean <= 2.5){
            return"#bd2525";
        }else if(2.5 < mean && mean <= 5){
            return"#cb8c22";
        }else if(5 < mean && mean <= 7.5){
            return"#cccc15";
        }else {
            return"#15bd15";
        }

    }
}
