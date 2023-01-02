package com.example.organizer.Service;

import com.example.organizer.model.User;

public class ThisUser {
    private static User user;
    public ThisUser (User user1){
        user = user1;
    }
    public static int getId(){
        if (user != null){
            return user.getId();
        }
        return -1;
    }

}
