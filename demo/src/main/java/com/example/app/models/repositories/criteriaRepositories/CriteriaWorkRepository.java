package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.Work;
import com.example.app.models.searchCriteria.WorkFilterCriteria;
import java.util.List;

public interface CriteriaWorkRepository {
    List<Work> searchWorkByFilters(WorkFilterCriteria workFilters);
}
