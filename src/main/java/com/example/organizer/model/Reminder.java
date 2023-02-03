package com.example.organizer.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "reminders")
public class Reminder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "lesson")
    private String lessonName;
    @Column(name = "quest")
    private String quest;
    @Column(name = "date")
    private String date;
    @Column(name = "switch")
    private boolean switchR;
    @Column(name = "settingSwitch")
    private String settingSwitch;
    @Column(name = "time")
    private String time;
    @Column(name = "need_work")
    private int needWork;
    @Column(name = "close_work")
    private int closeWork;
    @Column(name = "dayOfWeek")
    private String dayOfWeek;
    public Reminder(Long id, Long idUser, String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, int needWork, int closeWork, String datOfWeek) {
        this.id = id;
        this.idUser = idUser;
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.needWork = needWork;
        this.closeWork = closeWork;
        this.dayOfWeek = datOfWeek;
    }
    public Reminder() {}
    public Reminder(String lessonName, String quest, String date, boolean switchR) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
    }
    public Reminder(String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, String datOfWeek) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.dayOfWeek = datOfWeek;
    }


}
