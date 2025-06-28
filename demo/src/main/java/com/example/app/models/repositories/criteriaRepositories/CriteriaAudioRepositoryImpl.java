package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.Audio;
import com.example.app.models.searchCriteria.AudioFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class CriteriaAudioRepositoryImpl implements CriteriaAudioRepository{
    private EntityManager em;

    @Autowired
    public CriteriaAudioRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Audio> searchAudioByFilters(AudioFilterCriteria audioFilters) {
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Audio> query = cb.createQuery(Audio.class);
        Root<Audio> rootAudio = query.from(Audio.class);

        Predicate[] predicates = buildPredicates(cb, rootAudio, audioFilters);
        query.where(predicates);
        query.distinct(true);

        switch(audioFilters.getAudioName()) {
            case "orderByAudioNameAsc":
                query.orderBy(cb.asc(rootAudio.get("audioName")));
                break;
            case "orderByAudioNameDesc":
                query.orderBy(cb.desc(rootAudio.get("audioName")));
                break;
        }
        if(audioFilters.getAudioName() != null) {
            String[] keywords = audioFilters.getAudioName().trim().toLowerCase().split("\\s+");

            Expression<String> name = cb.coalesce(rootAudio.get("nome"), "");
            for(String kw : keywords) {
                Arrays.stream(predicates).toList().add(cb.like(rootAudio.get("audioName"), "%" + kw + "%"));
            }
        }
        return em.createQuery(query).getResultList();
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Audio> rootAudio, AudioFilterCriteria audioFilters) {
        List<Predicate> predicates = new ArrayList<>();

        if(audioFilters.getAudioName() != null) {
            predicates.add(cb.like(cb.lower(rootAudio.get("audioName")), "%" + audioFilters.getAudioName() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
