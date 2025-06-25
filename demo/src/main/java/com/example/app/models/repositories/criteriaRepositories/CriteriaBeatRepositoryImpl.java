package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.Beat;
import com.example.app.models.searchCriteria.BeatFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaBeatRepositoryImpl implements CriteriaBeatRepository{
    private EntityManager em;

    @Autowired
    public CriteriaBeatRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Beat> searchBeatByFilters(BeatFilterCriteria beatFilters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beat> query = cb.createQuery(Beat.class);
        Root<Beat> rootBeat = query.from(Beat.class);

        Predicate[] predicates = buildPredicates(cb, rootBeat, beatFilters);
        query.where(predicates);
        query.distinct(true);

        if (beatFilters.getSort() != null) {
            switch(beatFilters.getSort()) {
                case "orderByTitleAsc":
                    query.orderBy(cb.asc(rootBeat.get("title")));
                    break;
                case "orderByTitleDesc":
                    query.orderBy(cb.asc(rootBeat.get("title")));
                    break;
                case "orderByKeyAsc":
                    query.orderBy(cb.asc(rootBeat.get("key")));
                    break;
                case "orderByKeyDesc":
                    query.orderBy(cb.desc(rootBeat.get("key")));
                    break;
                case "orderByBpmAsc":
                    query.orderBy(cb.asc(rootBeat.get("bpm")));
                    break;
                case "orderByBpmDesc":
                    query.orderBy(cb.desc(rootBeat.get("bpm")));
                    break;
            }
        }
        int page = beatFilters.getPage();
        int size = beatFilters.getSize();


        return em.createQuery(query).setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    public Predicate[] buildPredicates(CriteriaBuilder cb, Root<Beat> root, BeatFilterCriteria filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getBeatTitle() != null) {
            predicates.add(cb.like(cb.lower(root.get("title")), "%" + filters.getBeatTitle() + "%"));
        }

        if (filters.getBeatKey() != null) {
            predicates.add(cb.like(cb.lower(root.get("key")), "%" + filters.getBeatKey() + "%"));
        }
        if (filters.getBeatBpm() != null) {
            predicates.add(cb.like(root.get("bpm"), "%" + filters.getBeatBpm() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }


}
