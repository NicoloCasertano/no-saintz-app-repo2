package com.example.app.models.services;

import com.example.app.models.entities.Audio;
import com.example.app.models.repositories.AudioRepository;
import com.example.app.models.repositories.criteriaRepositories.CriteriaAudioRepository;
import com.example.app.models.searchCriteria.AudioFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JpaAudioService implements AudioService {
    private AudioRepository audioRepo;
    private CriteriaAudioRepository criteriaAudioRepo;

    @Autowired
    public JpaAudioService(AudioRepository audioRepo, CriteriaAudioRepository criteriaAudioRepo) {
        this.audioRepo = audioRepo;
        this.criteriaAudioRepo = criteriaAudioRepo;
    }

    @Override
    public Optional<Audio> findAudioById(int id) {
        return audioRepo.findById(id);
    }

    @Override
    public List<Audio> findAllAudios() {
        return audioRepo.findAll();
    }

    @Override
    public Audio saveAudio(Audio audio) {
        return audioRepo.save(audio);
    }

    @Override
    public Audio updateAudio(Audio audio) {
        return saveAudio(audio);
    }

    @Override
    public boolean deleteAudio(int id) {
        audioRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Audio> searchAudio(AudioFilterCriteria filters) {
        return criteriaAudioRepo.searchAudioByFilters(filters);
    }
}
