package com.harsh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User
{
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3, message = "Name should atleast have 2 character")
    //@JsonProperty("USER NAME")
    private String name;

    @Past(message = "DOB should be in the past")
    private LocalDate birth_Date;

    protected User()
    {

    }

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birth_Date = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birth_Date;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birth_Date = birthDate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birthDate=" + birth_Date + "]";
    }
        
}
