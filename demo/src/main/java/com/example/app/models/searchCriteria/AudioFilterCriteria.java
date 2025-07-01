package com.example.app.models.searchCriteria;

public class AudioFilterCriteria {
    private Integer audioId;
    private String filePath;
    private String audioName;

    public AudioFilterCriteria(Integer audioId, String filePath, String audioName) {
        this.audioId = audioId;
        this.filePath = filePath;
        this.audioName = audioName;
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

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}
