package com.example.app.models.services;

import com.example.app.models.entities.Work;
import com.example.app.models.searchCriteria.WorkFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    Optional<Work> findWorkById(Integer id);

    List<Work> findAllWorks();

    Work saveWork(Work work);

    Work updateWork(Work work);

    boolean deleteWork(Integer id);

    List<Work> searchWork(WorkFilterCriteria filters);

    List<Work> findByUsersUserId(Integer id);


}
