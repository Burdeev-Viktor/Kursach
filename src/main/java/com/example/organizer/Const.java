package com.example.organizer;

public interface Const {
    String MESSAGE_ERROR_NOT_ALL_DATA = "Введите все данные!";
    String MESSAGE_ERROR_SIGN_IN = "Ошибка входа, проверьте Логин или Пороль!";
    String MESSAGE_ERROR_PASSWORD_NOT_SUPPRESS = "Пороли не совпадают!";
    String MESSAGE_ERROR_PASSWORD_NOT_CURE = "Пороль должен содержать не менее 2-х символов";
    String MESSAGE_ERROR_USERNAME_IS_EXIST = "Пользователь с таким логином уже существует!";
    String MESSAGE_ERROR_NOT_LESSON_NAME = "Введите название предмета!";
    String MESSAGE_ERROR_LESSON_NAME_IS_EXSIST = "Предмет с таким именем уже существует";
    String MESSAGE_ERROR_NOT_LESSONS_IN_db = "Создайте хоть один предмет!";
    String MESSAGE_ERROR_NOT_DATE = "Введите дату сдачи!";
    String MESSAGE_ERROR_NOT_DAY_OF_WEEK = "Введите день недели когда вам напоминать!";
    String[] CHOICE_BOX_NUMBER_OF_WEEK = {"Первая", "Вторая", "Каждую"};
    String[] CHOICE_BOX_TYPE_OF_TEST = {"Экзамен", "Зачёт", "Тест", "Неизвестно"};
    String[] TIME_OF_START_LESSON_FIRST = {"8:00", "9:55", "11:40","13:25","15:40","17:45"};
    String[] TIME_OF_START_LESSON_SECOND = {"12:00", "13:55", "15:40","17:45"};
    String DEFAULT_VALUE_CHOICE_BOX = "Выбрать";
    String[] CHOICE_BOX_SIX_DAYS_OF_WEEK = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    String[] CHOICE_BOX_SEVEN_DAYS_OF_WEEK = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    String[] TYPE_OF_LESSON = {"Лекция", "Лабораторная", "Практика", "Консультация"};
    String CHOICE_BOX_NO = "НЕТ";
    String CHOICE_BOX_YES = "ДА";
    String COLON = ":";
    String[] CHOICE_BOX_SETTING_SWITCH = {"Каждый день", "Каждую неделю"};
    String TITLE_SIGN_UP = "Регистрация";
    String TITLE_SIGN_IN = "Вход";
    String TITLE_TIMETABLE = "Расписание";
    String TITLE_EDIT_LESSON = "Пара";
    String TITLE_ADD_LESSON = "Новая пара";
    String TITLE_EDIT_REMINDER = "Задание";
    String TITLE_MAIN = "Студенческий органайзер";
    String EXIT = "Выход";
    String TINE_FORMAT = "hh:mm";
    int LENGTH_OF_PASSWORD = 3;
    int VBOX_SPACING = 5;
    long MINUTE_IN_MILLISECONDS = 60000;

}