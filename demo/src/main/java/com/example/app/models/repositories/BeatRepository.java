package com.example.app.models.repositories;

import com.example.app.models.entities.Beat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeatRepository extends JpaRepository<Beat, Integer> {
}
