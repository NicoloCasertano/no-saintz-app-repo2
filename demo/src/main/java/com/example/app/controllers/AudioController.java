package com.example.app.controllers;

import com.example.app.models.dtos.AudioDto;
import com.example.app.models.entities.Audio;
import com.example.app.models.repositories.AudioRepository;
import com.example.app.models.services.AudioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin (origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/audios")
public class AudioController {
    private AudioService audioService;
    private AudioRepository audioRepo;
    private Path fileStorageLocation;

    @Autowired
    public AudioController(AudioService audioService, AudioRepository audioRepo, Path fileStorageLocation) {
        this.audioService = audioService;
        this.audioRepo = audioRepo;
        this.fileStorageLocation = fileStorageLocation;
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getAudio(@PathVariable String fileName) throws IOException {
      Path filePath = fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());

      if(!resource.exists() || !resource.isReadable()) {
        return ResponseEntity.notFound().build();
      }
      String contentType = Files.probeContentType(filePath);
      return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
        .body(resource);
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AudioDto> uploadAudio(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadDir = "uploads";
        String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir).resolve(storedFileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Audio audio = new Audio(storedFileName, file.getOriginalFilename());
        audioRepo.save(audio);

        return ResponseEntity.ok(AudioDto.toDto(audio));
    }
}
