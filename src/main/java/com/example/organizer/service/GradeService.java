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
}
