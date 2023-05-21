package com.example.organizer.repository;

import com.example.organizer.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByIdUser(Long idUser);
    boolean existsByNameAndIdUser(String name, Long idUser);
    @Transactional
    @Modifying
    @Query(value = "UPDATE `organizer_db`.`lessons` SET name = ?2, conditions = ?3, type_of_test = ?4, id_user = ?5 WHERE (id = ?1)",nativeQuery = true)
    int lessonUpdate(Long id, String name,String condition, String type,Long idUser);

}
