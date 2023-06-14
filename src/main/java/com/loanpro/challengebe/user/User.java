package com.loanpro.challengebe.user;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users") // Using name "users" instead of "user" because it's a reserved word in H2 DBs
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private UserStatus status;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = UserStatus.ACTIVE;
    }
}
