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
    private Integer audioId;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "audio_name")
    private String audioName;

    private File file = new File(filePath);
    private Clip clip;

    public Audio() {
    }

    public Audio(String filePath, String audioName) {
        this.filePath = filePath;
        this.audioName = audioName;
    }



    public boolean startAudio(AudioInputStream audioInputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
            return true;
        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("Audio not supported");
            return false;
        }
        catch (IOException e) {
            System.out.println("Something went wrong with the audio file");
            return false;

        }
        catch (LineUnavailableException e) {
            System.out.println("Unable to access audio resource");
            return false;

        }
    }

    public boolean stopAudio(AudioInputStream audioInputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            Clip clip = AudioSystem.getClip();
            if (startAudio(audioInputStream)) {
                clip.stop();
            }
            return true;
        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("Audio not supported");
            return false;
        }
        catch (IOException e) {
            System.out.println("Something went wrong with the audio file");
            return false;
        }
        catch (LineUnavailableException e) {
            System.out.println("Unable to access audio resource");
            return false;
        }
    }

    public boolean restartAudio(AudioInputStream audioInputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            Clip clip = AudioSystem.getClip();
            if (startAudio(audioInputStream) || stopAudio(audioInputStream)) {
                clip.setMicrosecondPosition(0);
            }
            return true;
        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("Audio not supported");
            return false;
        }
        catch (IOException e) {
            System.out.println("Something went wrong with the audio file");
            return false;
        }
        catch (LineUnavailableException e) {
            System.out.println("Unable to access audio resource");
            return false;
        }
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
        return audioName;
    }

    public void setOriginalFileName(String audioName) {
        this.audioName = audioName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }
}
