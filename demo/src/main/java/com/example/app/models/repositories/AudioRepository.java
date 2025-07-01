package com.example.app.models.repositories;

import com.example.app.models.entities.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Integer> {
}
