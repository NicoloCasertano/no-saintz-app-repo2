package com.example.app.controllers;

import com.example.app.models.entities.Beat;
import com.example.app.models.services.BeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/beats")
public class BeatController {
    private BeatService beatService;

    @Autowired
    public BeatController(BeatService beatService) {
        this.beatService = beatService;
    }

    @GetMapping
    public List<Beat> getAllBeats() {
        return beatService.findAllBeats();
    }

}
