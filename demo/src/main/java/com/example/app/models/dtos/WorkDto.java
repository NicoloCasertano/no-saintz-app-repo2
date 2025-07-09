package com.example.app.models.dtos;

import com.example.app.models.entities.Audio;
import com.example.app.models.entities.User;
import com.example.app.models.entities.Work;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkDto {
    private Integer workId;
    private String title;
    private Integer bpm;
    private String key;
    private AudioDto audio;
    private String img;
    private UserDto user;
    private LocalDate dataDiCreazione;
    private String nota;

    public WorkDto(){};

    public WorkDto(Integer workId, String title, Integer bpm, String key, AudioDto audio, String img, UserDto user, LocalDate dataDiCreazione, String nota) {
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
      Work w = new Work();
      w.setWorkId(this.workId);
      w.setTitle(this.title);
      w.setBpm(this.bpm);
      w.setKey(this.key);
      w.setAudio(this.audio.toAudio());
      w.setImg(this.img);
      w.setUser(this.user.toUser());
      w.setDataDiCreazione(this.dataDiCreazione);
      w.setNota(this.nota);
      return w;
    }

    public static WorkDto toDto(Work w) {
        return new WorkDto(w.getWorkId(),
                w.getTitle(),
                w.getBpm(),
                w.getKey(),
                AudioDto.toDto(w.getAudio()),
                w.getImg(),
                UserDto.toDto(w.getUser()),
                w.getDataDiCreazione(),
                w.getNota());
    }

//    public static WorkDto toDto(Work w) {
//      var dto = new WorkDto();
//      dto.setWorkId(w.getWorkId());
//      dto.setTitle(w.getTitle());
//      dto.setBpm(w.getBpm());
//      dto.setKey(w.getKey());
//      dto.setAudioDto(AudioDto.toDto(w.getAudio()));
//      dto.setImg(w.getImg());
//      dto.setUserDto(UserDto.toDto(w.getUser()));
//      dto.setDataDiCreazione(w.getDataDiCreazione());
//      return dto;
//    }


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

    public AudioDto getAudio() {
        return audio;
    }

    public void setAudio(AudioDto audio) {
        this.audio = audio;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
