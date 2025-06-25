package com.example.app.models.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "work")
public class Work {

    @Id
    @Column(name = "work_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workId;

    @Column(name = "title")
    private String title;

    @Column(name = "bpm")
    private Integer bpm;

    @Column(name = "key")
    private String key;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audio_id")
    private Audio audio;

    @Column(name = "image")
    private String img;

    @ManyToOne
    @JoinColumn(name = "fr_user_id")
    private User user;

    @Column(name = "data_di_creazione")
    private LocalDate dataDiCreazione;

    @Column(name = "nota-appunti")
    private String nota;

    @ManyToMany(mappedBy = "works")
    private Set<User> users = new HashSet<>();


    public Work(Integer workId, String title, Integer bpm, String key, Audio audio, String img, User user, LocalDate dataDiCreazione, String nota) {
        this.workId = workId;
        this.title = title;
        this.bpm = bpm;
        this.key = key;
        this.audio = audio;
        this.img = img;
        this.user = user;
        this.dataDiCreazione = dataDiCreazione;
        this.nota = nota;
    }

    //GETTER E SETTER
    public Work() {
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDataDiCreazione() {
        return dataDiCreazione;
    }

    public void setDataDiCreazione(LocalDate dataDiCreazione) {
        this.dataDiCreazione = dataDiCreazione;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
