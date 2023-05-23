package com.example.organizer.repository;

import com.example.organizer.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    @Query(value = "SELECT * FROM `organizer_db`.`grades` WHERE (id_reminder = ?1)",nativeQuery = true)
    List<Grade> getGradesOfReminderId(Long id_reminder);

}
