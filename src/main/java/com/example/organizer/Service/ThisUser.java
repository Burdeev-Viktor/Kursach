package com.example.organizer.Service;

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
    public static int getId(){
        if (user != null){
            return user.getId();
        }
        return -1;
    }

}
