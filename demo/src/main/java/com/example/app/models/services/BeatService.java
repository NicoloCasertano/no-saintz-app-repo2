package com.example.app.models.services;

import com.example.app.models.entities.Beat;

import java.util.List;
import java.util.Optional;

public interface BeatService {
    Optional<Beat> findBeatById(int id);

    List<Beat> findAllBeats();

    Beat saveBeat(Beat beat);

    Beat updateBeat(Beat beat);

    boolean deleteBeat(int id);

}
