package com.example.app.controllers;

import com.example.app.models.entities.Audio;
import com.example.app.models.repositories.AudioRepository;
import com.example.app.models.services.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/audio")
public class AudioController {
    private AudioService audioService;
    private AudioRepository audioRepo;

    @Autowired
    public AudioController(AudioService audioService, AudioRepository audioRepo) {
        this.audioService = audioService;
        this.audioRepo = audioRepo;
    }

    @PostMapping("/api/audio/upload")
    public ResponseEntity<Audio> uploadAudio(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadDir = "uploads/audio/";

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Audio audio = new Audio(filePath.toString(), file.getOriginalFilename());
        audioRepo.save(audio);

        return ResponseEntity.ok(audio);
    }
}
