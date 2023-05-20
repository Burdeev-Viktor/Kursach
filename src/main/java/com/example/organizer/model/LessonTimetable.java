package com.example.organizer.model;

import com.example.organizer.model.enums.DayOfWeek;
import com.example.organizer.model.enums.NumberWeek;
import com.example.organizer.model.enums.TypeOfLesson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "timetable")
public class LessonTimetable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "teacher")
    private String teacher;
    @Column(name = "room")
    private String room;
    @Column(name = "time")
    private String time;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeOfLesson type;
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    @Column(name = "number_of_week")
    @Enumerated(EnumType.STRING)
    private NumberWeek numberOfWeek;

    public LessonTimetable() {}


    public LessonTimetable(String name, String teacher, String room, String time, TypeOfLesson type, DayOfWeek dayOfWeek, NumberWeek numberOfWeek) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }



}
