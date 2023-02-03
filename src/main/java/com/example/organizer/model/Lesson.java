package com.example.organizer.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "type_of_test")
    private String typeOfTest;
    @Column(name = "conditions")
    private String condition;

    public Lesson() {

    }

    public Lesson(Long id, Long idUser, String name, String typeOfTest, String condition) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }
    public Lesson(String name, String typeOfTest, String condition) {
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }
    public Lesson(Long id, String name, String typeOfTest, String condition) {
        this.id = id;
        this.name = name;
        this.typeOfTest = typeOfTest;
        this.condition = condition;
    }
}
