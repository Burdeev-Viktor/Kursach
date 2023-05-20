package com.example.organizer.model.enums;

public enum DayOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");
    private final String day;
    DayOfWeek(String day) {
        this.day = day;
    }
    public String getDay() {
        return day;
    }
    public static DayOfWeek[] getSixDay(){
        return new DayOfWeek[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
    }
}
