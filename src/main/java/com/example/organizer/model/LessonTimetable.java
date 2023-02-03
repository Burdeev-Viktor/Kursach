package com.example.organizer.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
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
    private String type;
    @Column(name = "day_of_week")
    private int dayOfWeek;
    @Column(name = "number_of_week")
    private int numberOfWeek;

    public LessonTimetable() {

    }

    public LessonTimetable(Long id, Long idUser, String name, String teacher, String room, String time, String type, int dayOfWeek, int numberOfWeek) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public LessonTimetable(String name, String teacher, String room, String time, String type, int dayOfWeek, int numberOfWeek) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public LessonTimetable(Long id, String name, String teacher, String room, String time, String type, int dayOfWeek, int numberOfWeek) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

}
