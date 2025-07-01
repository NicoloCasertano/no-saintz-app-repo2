package com.example.app.models.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admId;

    @Column(name = "admin_name")
    private String admName;

    @Column(name = "admin_password")
    private String admPassword;


    public Admin(Integer admId, String admName, String admPassword) {
        this.admId = admId;
        this.admName = admName;
        this.admPassword = admPassword;
    }

    public Admin() {
    }

    //GETTER E SETTER
    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword;
    }


}
