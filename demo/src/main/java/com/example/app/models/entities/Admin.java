package com.example.app.models.entities;

import jakarta.persistence.*;

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


    @OneToMany(mappedBy = "admin")
    private Integer workId;

    public Admin(Integer admId, String admName, String admPassword, Integer workId) {
        this.admId = admId;
        this.admName = admName;
        this.admPassword = admPassword;
        this.workId = workId;
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

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
