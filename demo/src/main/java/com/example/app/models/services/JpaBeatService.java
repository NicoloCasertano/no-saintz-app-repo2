package com.example.app.models.services;

import com.example.app.models.entities.Beat;
import com.example.app.models.repositories.BeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaBeatService implements BeatService{
    private BeatRepository beatRepo;

    @Autowired
    public JpaBeatService(BeatRepository beatRepo) {
        this.beatRepo = beatRepo;
    }

    @Override
    public Optional<Beat> findBeatById(int id) {
        return beatRepo.findById(id);
    }

    @Override
    public List<Beat> findAllBeats() {
        List<Beat> beats = beatRepo.findAll();
        return beats;
    }

    @Override
    public Beat saveBeat(Beat beat) {
        return beatRepo.save(beat);
    }

    @Override
    public Beat updateBeat(Beat beat) {
        return saveBeat(beat);
    }

    @Override
    public boolean deleteBeat(int id) {
        beatRepo.deleteById(id);
        return true;
    }
}
