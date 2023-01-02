package com.example.organizer.model;

public class Reminder {
    private int id;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Reminder(int id, int idUser, String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, int needWork, int closeWork, String datOfWeek) {
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
        this.datOfWeek = datOfWeek;
    }

    private String lessonName;
    private String quest;
    private String date;
    private boolean switchR;
    private String settingSwitch;
    private String time;
    private int needWork;
    private int closeWork;

    public int getNeedWork() {
        return needWork;
    }

    public void setNeedWork(int needWork) {
        this.needWork = needWork;
    }

    public int getCloseWork() {
        return closeWork;
    }

    public void setCloseWork(int closeWork) {
        this.closeWork = closeWork;
    }

    public Reminder() {
    }

    public Reminder(String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, int needWork, int closeWork, String datOfWeek) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.needWork = needWork;
        this.closeWork = closeWork;
        this.datOfWeek = datOfWeek;
    }

    public Reminder(int id, String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, int needWork, int closeWork, String datOfWeek) {
        this.id = id;
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.needWork = needWork;
        this.closeWork = closeWork;
        this.datOfWeek = datOfWeek;
    }

    private String datOfWeek;

    public Reminder(int id, String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time) {
        this.id = id;
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
    }

    public Reminder(String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
    }

    public Reminder(String lessonName, String quest, String date, boolean switchR) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
    }

    public Reminder(String lessonName, String quest, String date, boolean switchR, String settingSwitch) {
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
    }

    public Reminder(int id, String lessonName, String quest, String date, boolean switchR, String settingSwitch, String time, String datOfWeek) {
        this.id = id;
        this.lessonName = lessonName;
        this.quest = quest;
        this.date = date;
        this.switchR = switchR;
        this.settingSwitch = settingSwitch;
        this.time = time;
        this.datOfWeek = datOfWeek;
    }

    public Reminder(int id, String lessonName, String quest, String date, boolean switchR) {
        this.id = id;
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
        this.datOfWeek = datOfWeek;
    }

    public int getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public boolean isSwitchR() {
        return switchR;
    }

    public void setSwitchR(boolean switchR) {
        this.switchR = switchR;
    }

    public String getSettingSwitch() {
        return settingSwitch;
    }

    public void setSettingSwitch(String settingSwitch) {
        this.settingSwitch = settingSwitch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDatOfWeek() {
        return datOfWeek;
    }

    public void setDatOfWeek(String datOfWeek) {
        this.datOfWeek = datOfWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
