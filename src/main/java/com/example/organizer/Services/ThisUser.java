package com.example.organizer.Services;

import com.example.organizer.model.User;

/**
 * Класс в котором хранится авторизований пользователь
 */
public class ThisUser {
    private static User user;
    public ThisUser (User user1){
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
