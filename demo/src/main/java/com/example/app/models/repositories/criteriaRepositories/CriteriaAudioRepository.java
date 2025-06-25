package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.Audio;
import com.example.app.models.searchCriteria.AudioFilterCriteria;

import java.util.List;

public interface CriteriaAudioRepository {
    List<Audio> searchAudioByFilters(AudioFilterCriteria audioFilters);
}
