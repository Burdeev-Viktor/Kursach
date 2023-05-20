package com.example.organizer.model.enums;

public enum NumberWeek {
    FIRST("Первую"),
    SECOND("Вторую"),
    ALL("Каждую");
    private final String number;
    NumberWeek(String number){
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
}
