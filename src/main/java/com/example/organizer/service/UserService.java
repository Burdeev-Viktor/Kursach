package com.example.organizer.service;

import com.example.organizer.model.User;
import com.example.organizer.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserService {

    private static final UserRepository userRepository;

    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userRepository = context.getBean(UserRepository.class);
    }
    public void save(User user){
        userRepository.save(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(new User());
    }
    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }
    public boolean userIsExistsByLoginAndPassword(User user){
        return userRepository.existsByLoginAndPassword(user.getLogin(), user.getPassword());
    }
    public boolean userIsExistsByLogin(String login){
        return userRepository.existsByLogin(login);
    }
}
