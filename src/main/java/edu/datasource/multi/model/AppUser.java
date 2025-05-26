package edu.datasource.multi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "application_user")
@ToString
@Getter
@Setter
public class AppUser {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "age")
    private int age;
}
