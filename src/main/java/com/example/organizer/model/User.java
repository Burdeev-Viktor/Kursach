package com.example.organizer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;


@Entity
@Setter
@Getter

@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
    public User() {}
}
