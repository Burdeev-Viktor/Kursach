package com.example.organizer.repository;

import com.example.organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByLogin(String login);
    boolean existsByLoginAndPassword(String login,String password);
    boolean existsByLogin(String login);
}
