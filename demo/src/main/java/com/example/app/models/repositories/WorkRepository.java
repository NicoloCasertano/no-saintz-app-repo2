package com.example.app.models.repositories;

import com.example.app.models.entities.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {

  List<Work> findByUserUserId(int userId);
}
