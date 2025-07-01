package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.User;
import com.example.app.models.entities.Work;
import com.example.app.models.searchCriteria.WorkFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaWorkRepositoryImpl implements CriteriaWorkRepository {
    private EntityManager em;

    @Autowired
    public CriteriaWorkRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Work> searchWorkByFilters(WorkFilterCriteria workFilters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Work> query = cb.createQuery(Work.class);
        Root<Work> rootWork = query.from(Work.class);

        Join<Work, User> userJoin = null;
        if(workFilters.getUser() != null && !workFilters.getUser().isEmpty()) {
            userJoin = rootWork.join("userSet");
        }
        Predicate[] predicates = buildPredicates(cb, rootWork, workFilters, userJoin);
        query.where(predicates);

        if (userJoin != null) {
            query.groupBy(rootWork.get("id"));
            query.having(
                    cb.equal(
                            cb.countDistinct(userJoin.get("id")),
                            workFilters.getUser().size()
                    )
            );
        }
        query.distinct(true);

        // Sorting dinamico
        if (workFilters.getSort() != null) {
            switch (workFilters.getSort()) {
                case "orderByTitleAsc":
                    query.orderBy(cb.asc(rootWork.get("title")));
                    break;
                case "orderByTitleDesc":
                    query.orderBy(cb.desc(rootWork.get("title")));
                    break;
                case "orderByDataDiCreazioneAsc":
                    query.orderBy(cb.asc(rootWork.get("dataDiCreazione")));
                    break;
                case "orderByDataDiCreazioneDesc":
                    query.orderBy(cb.desc(rootWork.get("dataDiCreazione")));
                    break;
            }
        }
        int page = workFilters.getPage(); // da 0 in poi
        int size = workFilters.getSize(); // numero per pagina

        return em.createQuery(query).setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Work> root, WorkFilterCriteria filters, Join<Work, User> userJoin) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getTitle() != null) {
            predicates.add(cb.like(cb.lower(root.get("title")), "%" + filters.getTitle().toLowerCase() + "%"));
        }

        if (filters.getDataDiCreazione() != null) {
            predicates.add(cb.equal(root.get("dataDiCreazione"), filters.getDataDiCreazione()));
        }

        if (filters.getMinData() != null && filters.getMaxData() != null) {
            predicates.add(cb.between(root.get("dataDiCreazione"), filters.getMinData(), filters.getMaxData()));
        } else if (filters.getMinData() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiCreazione"), filters.getMinData()));
        } else if (filters.getMaxData() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dataDiCreazione"), filters.getMaxData()));
        }

        if (filters.getUser() != null) {
            predicates.add(cb.equal(userJoin.get("id"), filters.getUser()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
