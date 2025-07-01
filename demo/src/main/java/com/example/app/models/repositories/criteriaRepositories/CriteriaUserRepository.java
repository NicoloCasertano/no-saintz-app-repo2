package com.example.app.models.repositories.criteriaRepositories;

import com.example.app.models.entities.User;
import com.example.app.models.searchCriteria.UserFilterCriteria;

import java.util.List;

public interface CriteriaUserRepository {
    List<User> searchUserByFilters(UserFilterCriteria userFilters);
}
