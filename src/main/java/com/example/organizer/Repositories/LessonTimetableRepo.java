package com.example.organizer.Repositories;

import com.example.organizer.model.LessonTimetable;

import java.sql.*;
import java.util.ArrayList;

public class LessonTimetableRepo extends db_Settings {

    public static void deleteLessonOfTimeTableById( LessonTimetable lessonTimetable) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM timetable WHERE id = ?");
            preparedStatement.setInt(1, lessonTimetable.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static boolean timetableIsExists() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);

            preparedStatement = connection.prepareStatement("SHOW TABLES LIKE 'timetable' ");
            resultSet = preparedStatement.executeQuery();
            result = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return !result;
    }

    public static void createTimetable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE organizer_db.timetable (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `id_user` INT NOT NULL ,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `teacher` VARCHAR(45) NULL,\n" +
                    "  `room` VARCHAR(45) NULL,\n" +
                    "  `time` VARCHAR(6) NULL,\n" +
                    "  `day_of_week` INT NULL,\n" +
                    "  `number_of_week` INT NULL,\n" +
                    "  `type` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void addLessonTimetableByIdUser(LessonTimetable lessonTimetable) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO timetable (id_user,name,teacher,room,time,day_of_week,number_of_week,type) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, lessonTimetable.getIdUser());
            preparedStatement.setString(2, lessonTimetable.getName());
            preparedStatement.setString(3, lessonTimetable.getTeacher());
            preparedStatement.setString(4, lessonTimetable.getRoom());
            preparedStatement.setString(5, lessonTimetable.getTime());
            preparedStatement.setInt(6, lessonTimetable.getDayOfWeek());
            preparedStatement.setInt(7, lessonTimetable.getNumberOfWeek());
            preparedStatement.setString(8, lessonTimetable.getType());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static ArrayList<LessonTimetable> getAllLessonsTimetableByUserId(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM timetable WHERE id_user = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new LessonTimetable(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("teacher"),
                        resultSet.getString("room"),
                        resultSet.getString("time"),
                        resultSet.getString("type"),
                        resultSet.getInt("day_of_week"),
                        resultSet.getInt("number_of_week")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static ArrayList<LessonTimetable> getLessonsTimetableByUserByWeek(int id, int week) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM timetable WHERE id_user = ? AND number_of_week = ? OR number_of_week = 2");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, week);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new LessonTimetable(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("teacher"),
                        resultSet.getString("room"),
                        resultSet.getString("time"),
                        resultSet.getString("type"),
                        resultSet.getInt("day_of_week"),
                        resultSet.getInt("number_of_week")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static void updateLessonTimetableById(LessonTimetable lessonTimetable, int idLesson, int idUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE timetable SET id_user =?,name = ?,teacher = ?,room = ?,day_of_week = ?,number_of_week = ?,type = ? WHERE id = ?");
            preparedStatement.setInt(1,idUser);
            preparedStatement.setString(2, lessonTimetable.getName());
            preparedStatement.setString(3, lessonTimetable.getTeacher());
            preparedStatement.setString(4, lessonTimetable.getRoom());
            preparedStatement.setInt(5, lessonTimetable.getDayOfWeek());
            preparedStatement.setInt(6, lessonTimetable.getNumberOfWeek());
            preparedStatement.setString(7, lessonTimetable.getType());
            preparedStatement.setInt(8, idLesson);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
}

