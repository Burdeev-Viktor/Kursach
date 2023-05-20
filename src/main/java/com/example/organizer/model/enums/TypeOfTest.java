package com.example.organizer.model.enums;

public enum TypeOfTest {
    EXAM("Экзамен"),
    CREDIT ("Зачёт"),
    TEST("Тест"),
    UNKNOWN  ("Неизвестно");
    private final String type;
    TypeOfTest(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
