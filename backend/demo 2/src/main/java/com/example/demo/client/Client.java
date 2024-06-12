package com.example.demo.client;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate join_date;
    @Transient
    private Integer age_of_org;


    public Client() {
    }

    public Client(Long id, String name, String email, LocalDate join_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.join_date = join_date;
    }

    public Client(String name, String email, LocalDate join_date) {
        this.name = name;
        this.email = email;
        this.join_date = join_date;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public Integer getAge_of_org() {
        return Period.between(this.join_date, LocalDate.now()).getYears();
    }

    public void setAge_of_org(Integer age_of_org) {
        this.age_of_org = age_of_org;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", join_date=" + join_date +
                ", age_of_org=" + age_of_org +
                '}';
    }
}
