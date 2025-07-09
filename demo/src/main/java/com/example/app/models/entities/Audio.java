package com.example.app.models.entities;

import jakarta.persistence.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Entity
@Table(name = "audio")
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer audioId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "audio_name")
    private String originalFileName;

    public Audio() {
    }

    public Audio(String filePath, String originalFileName) {
        this.filePath = filePath;
        this.originalFileName = originalFileName;
    }

    public Integer getAudioId() {
        return audioId;
    }

    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }


}
