package com.example.organizer.model;

import com.example.organizer.model.enums.DayOfWeek;
import com.example.organizer.model.enums.SettingSwitch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
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
    @Column(name = "setting_switch")
    @Enumerated(EnumType.STRING)
    private SettingSwitch settingSwitch;
    @Column(name = "time")
    private String time;
    @Column(name = "need_work")
    private int needWork;
    @Column(name = "close_work")
    private int closeWork;
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    public Reminder() {}
    public Reminder(String lessonName, String quest, String date, boolean switchR) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
    }
    public Reminder(String lessonName, String quest, String date, boolean switchR, SettingSwitch settingSwitch, String time, DayOfWeek datOfWeek) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.dayOfWeek = datOfWeek;
    }


}
