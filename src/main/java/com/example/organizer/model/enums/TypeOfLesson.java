package com.example.organizer.model.enums;

public enum TypeOfLesson {
    LECTURE("Лекция") ,
    LAB("Лабораторная"),
    PRACTICE("Практика"),
    CONSULTATION("Консультация");
    private final String type;
    TypeOfLesson(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

}
