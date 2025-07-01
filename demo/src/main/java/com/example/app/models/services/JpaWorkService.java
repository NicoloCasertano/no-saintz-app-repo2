package com.example.app.models.services;

import com.example.app.models.entities.Work;
import com.example.app.models.repositories.WorkRepository;
import com.example.app.models.repositories.criteriaRepositories.CriteriaWorkRepository;
import com.example.app.models.searchCriteria.WorkFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaWorkService implements WorkService {
    private WorkRepository workRepo;
    private CriteriaWorkRepository criteriaWorkRepo;

    @Autowired
    public JpaWorkService(WorkRepository workRepo, CriteriaWorkRepository criteriaWorkRepo) {
        this.workRepo = workRepo;
        this.criteriaWorkRepo = criteriaWorkRepo;
    }

    @Override
    public Optional<Work> findWorkById(int id) {
        return workRepo.findById(id);
    }

    @Override
    public List<Work> findAllWorks() {
        return workRepo.findAll();
    }

    @Override
    public Work saveWork(Work work) {
        return workRepo.save(work);
    }

    @Override
    public Work updateWork(Work work) {
        return saveWork(work);
    }

    @Override
    public boolean deleteWork(int id) {
        workRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Work> searchWork(WorkFilterCriteria filters) {
        return criteriaWorkRepo.searchWorkByFilters(filters);
    }
}
