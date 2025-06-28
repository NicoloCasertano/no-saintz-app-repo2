package com.example.app.models.dtos;

import com.example.app.models.entities.Audio;

public class AudioDto {
    private Integer id;
    private String filePath;
    private String name;

    public AudioDto() {
    }

    public AudioDto(Integer id, String filePath, String name) {
        this.id = id;
        this.filePath = filePath;
        this.name = name;
    }

    public Audio toAudio() {
        return new Audio(filePath, name);
    }

    public static AudioDto toDto(Audio a) {
        return new AudioDto(a.getAudioId(), a.getFilePath(), a.getAudioName());
    }

    public Integer getAudioDtoId() {
        return id;
    }

    public void setAudioDtoId(Integer audioId) {
        this.id = audioId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
