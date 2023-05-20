package com.example.organizer.repository;

import com.example.organizer.model.User;

/**
 * Класс в котором хранится авторизований пользователь
 */
public class Session {
    private static User user;
    public static void setUser (User user1){
        user = user1;
    }

    /**
     *
     * @return
     * > 0 - пользователь авторизован
     * -1 - пользователь не авторизован
     */
    public static Long getId(){
        if (user != null){
            return user.getId();
        }
        return -1L;
    }

}
