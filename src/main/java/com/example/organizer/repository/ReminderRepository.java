package com.example.organizer.repository;

import com.example.organizer.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
   List<Reminder> findAllBySwitchRAndIdUser(boolean switchR, Long idUser);
   List<Reminder> findAllByIdUser(Long idUser);
}
