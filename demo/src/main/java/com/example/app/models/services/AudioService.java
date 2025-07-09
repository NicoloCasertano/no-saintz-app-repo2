package com.example.app.models.services;

import com.example.app.models.entities.Audio;
import com.example.app.models.searchCriteria.AudioFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface AudioService {

    Optional<Audio> findAudioById(int id);
    List<Audio> findAllAudios();
    Audio saveAudio(Audio audio);
    Audio updateAudio(Audio audio);
    boolean deleteAudio(int id);
    List<Audio> searchAudio(AudioFilterCriteria afc);

}
