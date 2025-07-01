package com.example.app.models.dtos;

import com.example.app.models.entities.Audio;
import com.example.app.models.entities.User;
import com.example.app.models.entities.Work;

import java.time.LocalDate;

public class WorkDto {
    private Integer workId;
    private String title;
    private Integer bpm;
    private String key;
    private Audio audio;
    private String img;
    private User user;
    private LocalDate dataDiCreazione;
    private String nota;

    public WorkDto(Integer workId, String title, Integer bpm, String key, Audio audio, String img, User user, LocalDate dataDiCreazione, String nota) {
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

    public Work toWork() {
        return new Work(workId, title, bpm, key, audio, img, user, dataDiCreazione, nota);
    }

    static public WorkDto toDto(Work w) {
        return new WorkDto(w.getWorkId(),
                w.getTitle(),
                w.getBpm(),
                w.getKey(),
                w.getAudio(),
                w.getImg(),
                w.getUser(),
                w.getDataDiCreazione(),
                w.getNota());
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
