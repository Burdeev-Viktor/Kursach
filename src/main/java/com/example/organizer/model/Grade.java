package com.example.organizer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grades")
public class Grade {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_reminder")
    private Long idReminder;
    @Column(name = "grade")
    private short grade;
    public Grade(Long idReminder, short grade) {
        this.idReminder = idReminder;
        this.grade = grade;
    }

    public Grade() {

    }
}
