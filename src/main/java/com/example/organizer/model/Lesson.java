package com.example.organizer.model;

public class Lesson {
    private int id;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Lesson(int id, int idUser, String name, String typeOfTest, String condition) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }

    private String name;
    private String typeOfTest;
    private String condition;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfTest() {
        return typeOfTest;
    }

    public void setTypeOfTest(String typeOfTest) {
        this.typeOfTest = typeOfTest;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Lesson(String name, String typeOfTest, String condition) {
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }

    public Lesson(int id, String name, String typeOfTest, String condition) {
        this.id = id;
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }
}
