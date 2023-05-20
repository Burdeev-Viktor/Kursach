package com.example.organizer.service;

import com.example.organizer.SecondTherd.Encode;
import com.example.organizer.model.User;
import com.example.organizer.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private static final PasswordEncoder passwordEncoder = new Encode();
    private static final UserRepository userRepository;

    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userRepository = context.getBean(UserRepository.class);
    }
    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.existsByLoginAndPassword(user.getLogin(), user.getPassword());
    }
    public boolean userIsExistsByLogin(String login){
        return userRepository.existsByLogin(login);
    }
}
