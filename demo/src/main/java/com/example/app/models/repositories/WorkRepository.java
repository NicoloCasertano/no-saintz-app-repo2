package com.example.app.models.repositories;

import com.example.app.models.entities.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
}
