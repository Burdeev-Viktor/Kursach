package com.example.organizer.Repositories;

import com.example.organizer.model.Reminder;

import java.sql.*;
import java.util.ArrayList;

public class ReminderRepo extends db_Settings {
    private static final String NAME_TABLE_REMINDER = "reminders";

    public static ArrayList<Reminder> getRemindersEnable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reminder> lessonsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + NAME_TABLE_REMINDER + " WHERE switch = 1");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Reminder(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("lesson"),
                        resultSet.getString("quest"),
                        resultSet.getString("date"),
                        resultSet.getBoolean("switch"),
                        resultSet.getString("settingSwitch"),
                        resultSet.getString("time"),
                        resultSet.getInt("need_work"),
                        resultSet.getInt("close_work"),
                        resultSet.getString("dayOfWeek")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static void deleteReminderById( Reminder reminder) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM " + NAME_TABLE_REMINDER + " WHERE id = ? AND id_user = ?");
            preparedStatement.setInt(1, reminder.getId());
            preparedStatement.setInt(2, reminder.getIdUser());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void addReminderByIdUser(Reminder reminder) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO " + NAME_TABLE_REMINDER + " (id_user,lesson,date,quest,switch,settingSwitch,time,need_work,close_work,dayOfWeek) VALUES (?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, reminder.getIdUser());
            preparedStatement.setString(2, reminder.getLessonName());
            preparedStatement.setString(3, reminder.getDate());
            preparedStatement.setString(4, reminder.getQuest());
            preparedStatement.setBoolean(5, reminder.isSwitchR());
            preparedStatement.setString(6, reminder.getSettingSwitch());
            preparedStatement.setString(7, reminder.getTime());
            preparedStatement.setInt(8, reminder.getNeedWork());
            preparedStatement.setInt(9, reminder.getCloseWork());
            preparedStatement.setString(10, reminder.getDatOfWeek());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void updateRminderById(Reminder reminder, int idReminder) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE " + NAME_TABLE_REMINDER + " SET lesson = ?,date = ?,quest = ?,switch = ?,settingSwitch = ?,time = ?,dayOfWeek = ?,need_work = ?,close_work = ? WHERE id = ?");
            preparedStatement.setString(1, reminder.getLessonName());
            preparedStatement.setString(2, reminder.getDate());
            preparedStatement.setString(3, reminder.getQuest());
            preparedStatement.setBoolean(4, reminder.isSwitchR());
            preparedStatement.setString(5, reminder.getSettingSwitch());
            preparedStatement.setString(6, reminder.getTime());
            preparedStatement.setString(7, reminder.getDatOfWeek());
            preparedStatement.setInt(8, reminder.getNeedWork());
            preparedStatement.setInt(9, reminder.getCloseWork());
            preparedStatement.setInt(10, idReminder);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static ArrayList<Reminder> getAllRemindersByUser(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reminder> lessonsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + NAME_TABLE_REMINDER + " WHERE id_user = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Reminder(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("lesson"),
                        resultSet.getString("quest"),
                        resultSet.getString("date"),
                        resultSet.getBoolean("switch"),
                        resultSet.getString("settingSwitch"),
                        resultSet.getString("time"),
                        resultSet.getInt("need_work"),
                        resultSet.getInt("close_work"),
                        resultSet.getString("dayOfWeek")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static boolean reminderTableIsExists() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SHOW TABLES LIKE '" + NAME_TABLE_REMINDER + "'");
            resultSet = preparedStatement.executeQuery();
            result = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return !result;
    }

    public static void createReminderTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE `organizer_db`.`" + NAME_TABLE_REMINDER + "` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `id_user` INT NOT NULL ,\n" +
                    "  `lesson` VARCHAR(45) NULL,\n" +
                    "  `date` VARCHAR(10) NULL,\n" +
                    "  `quest` VARCHAR(200) NULL,\n" +
                    "  `need_work` INT NULL,\n" +
                    "  `close_work` INT NULL,\n" +
                    "  `switch` TINYINT NULL,\n" +
                    "  `settingSwitch` VARCHAR(45) NULL,\n" +
                    "  `time` VARCHAR(5) NULL,\n" +
                    "  `dayOfWeek` VARCHAR(10) NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
}
