package com.example.app.models.dtos;

import com.example.app.models.entities.Audio;

import java.nio.file.Paths;

public class AudioDto {
    private Integer id;
    private String storedFileName;
    private String originalFileName;

    public AudioDto() {
    }

    public AudioDto(Integer id, String storedFileName, String originalFileName) {
        this.id = id;
        this.storedFileName = storedFileName;
        this.originalFileName = originalFileName;
    }

    public Audio toAudio() {
        return new Audio("uploads/" + storedFileName, originalFileName);
    }

    public static AudioDto toDto(Audio a) {
        return new AudioDto(
          a.getAudioId(),
          Paths.get(a.getFilePath()).getFileName().toString(),
          a.getOriginalFileName());
    }

    public Integer getAudioDtoId() {
        return id;
    }

    public void setAudioDtoId(Integer audioId) {
        this.id = audioId;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}
