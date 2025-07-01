package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.User;
import com.example.app.models.searchCriteria.UserFilterCriteria;
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
public class CriteriaUserRepositoryImpl implements CriteriaUserRepository{
    private EntityManager em;

    @Autowired
    public CriteriaUserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> searchUserByFilters(UserFilterCriteria userFilters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> rootUser = query.from(User.class);

        Predicate[] predicates = buildPredicates(cb, rootUser, userFilters);
        query.where(predicates);
        query.distinct(true);

        if (userFilters.getSort() != null) {
            switch (userFilters.getSort()) {
                case "orderByNameAsc":
                    query.orderBy(cb.asc(rootUser.get("userName")));
                    break;
                case "orderByNameDesc":
                    query.orderBy(cb.desc(rootUser.get("userName")));
                    break;
                case "orderByArtNameAsc":
                    query.orderBy(cb.asc(rootUser.get("artName")));
                    break;
                case "orderByArtNameDesc":
                    query.orderBy(cb.desc(rootUser.get("artName")));
                    break;
            }
        }
        int page = userFilters.getPage();
        int size = userFilters.getSize();

        return em.createQuery(query).setFirstResult(page * size).setMaxResults(size).getResultList();
    }

    public Predicate[] buildPredicates(CriteriaBuilder cb, Root<User> root, UserFilterCriteria filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getUserName() != null) {
            predicates.add(cb.like(cb.lower(root.get("userName")),"%" + filters.getUserName() + "%"));
        }
        if (filters.getArtName() != null) {
            predicates.add(cb.like(cb.lower(root.get("artName")), "%" + filters.getArtName() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }

}
