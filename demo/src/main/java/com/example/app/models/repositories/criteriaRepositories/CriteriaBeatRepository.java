package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.Beat;
import com.example.app.models.searchCriteria.BeatFilterCriteria;

import java.util.List;

public interface CriteriaBeatRepository {
    List<Beat> searchBeatByFilters(BeatFilterCriteria beatFilters);
}
