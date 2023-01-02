package com.example.organizer.Repositories;

import com.example.organizer.model.Lesson;
import com.example.organizer.model.User;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepo extends db_Settings{
    private static final String NAME_TABLE_LESSONS = "lessons";
    public static boolean lessonTableIsExistsByUser() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SHOW TABLES LIKE '" + NAME_TABLE_LESSONS + "' ");
            resultSet = preparedStatement.executeQuery();
            result = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return !result;
    }
    public static void createTimetableByUser() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE `organizer_db`." + NAME_TABLE_LESSONS + " (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `id_user` INT NOT NULL ,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `type_of_test` VARCHAR(45) NULL,\n" +
                    "  `conditions` VARCHAR(100) NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
    public static ArrayList<Lesson> getAllLessonsByUserId(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Lesson> lessonsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + NAME_TABLE_LESSONS + " WHERE id_user = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("type_of_test"),
                        resultSet.getString("conditions")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }
    public static String[] getAllLessonsNameByUserId(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> lessonsNameList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + NAME_TABLE_LESSONS + " WHERE id_user = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsNameList.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        String[] names = new String[lessonsNameList.size()];
        for (int i = 0;i < lessonsNameList.size();i++) {
            names[i] = lessonsNameList.get(i);
        }
        return names;
    }
    public static void addLessonByIdUser(Lesson lesson) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO " + NAME_TABLE_LESSONS + " (id_user,name,type_of_test,conditions) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, lesson.getIdUser());
            preparedStatement.setString(2, lesson.getName());
            preparedStatement.setString(3, lesson.getTypeOfTest());
            preparedStatement.setString(4, lesson.getCondition());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
    public static boolean lessonIsExistsByUser(String lessonName, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + NAME_TABLE_LESSONS +" WHERE name = ? AND id_user = ?");
            preparedStatement.setString(1, lessonName);
            preparedStatement.setInt(2,id);
            resultSet = preparedStatement.executeQuery();

            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return false;
    }
    public static void deleteLessonById( Lesson lesson) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM " + NAME_TABLE_LESSONS + " WHERE id = ?");
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

}
