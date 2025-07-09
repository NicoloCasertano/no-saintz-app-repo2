package com.example.app.controllers;

import com.example.app.exceptions.EntityNotFoundException;
import com.example.app.models.dtos.WorkDto;
import com.example.app.models.entities.Audio;
import com.example.app.models.entities.User;
import com.example.app.models.entities.Work;
import com.example.app.models.searchCriteria.WorkFilterCriteria;
import com.example.app.models.services.FileStorageService;
import com.example.app.models.services.UserService;
import com.example.app.models.services.WorkService;
import org.apache.coyote.Response;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/works")
public class WorkController {
    private WorkService workService;
    private UserService userService;
    private FileStorageService fileStorageService;

    @Autowired
    public WorkController(WorkService workService, UserService userService, FileStorageService fileStorageService) {
        this.workService = workService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public ResponseEntity<List<WorkDto>> searchWork(@RequestParam(required = false) Integer workId,
                                                    @RequestParam(required = false) String title,
                                                    @RequestParam(required = false) Integer bpm,
                                                    @RequestParam(required = false) String key,
                                                    @RequestParam(required = false) List<User> user,
                                                    @RequestParam(required = false) LocalDate dataDiCreazione,
                                                    @RequestParam(required = false) LocalDate minData, //da rivedere ASAP la questione audio
                                                    @RequestParam(required = false) LocalDate maxData,
                                                    @RequestParam(defaultValue = "") String nota,
                                                    @RequestParam(defaultValue = "orderByDataDiCreazioneDesc") String sort) {
        WorkFilterCriteria wfc = new WorkFilterCriteria(workId, title, bpm, key, user, dataDiCreazione, minData, maxData, nota, sort);
        List<Work> works = workService.searchWork(wfc);
        return ResponseEntity.ok(works.stream().map(WorkDto::toDto).toList());
    }

    @GetMapping(value = "/all")
    public List<WorkDto> getAllWorks() {
        return workService.findAllWorks().stream().map(WorkDto::toDto).toList();
    }

    @GetMapping("/by-user/{userId}")
    public List<WorkDto> getWorksDoneByUserId(@PathVariable Integer userId) {
      return workService.findByUsersUserId(userId).stream().map(WorkDto::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<URI> createWork(@RequestBody WorkDto workDto) throws DataException, EntityNotFoundException {
        Work work = workDto.toWork();
        Work newWork = workService.saveWork(work);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newWork.getWorkId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WorkDto> uploadWork(
        @RequestParam("file")MultipartFile file,
        @RequestParam("title")String title,
        @RequestParam("bpm")Integer bpm,
        @RequestParam("key")String key,
        @RequestParam("dataDiCreazione")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDate dataDiCreazione,
        @RequestParam("userId") Integer userId) throws DataException, EntityNotFoundException, IOException {

      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      if(file.isEmpty()) throw new RuntimeException("File vuoto");

      String savedFilePath = fileStorageService.storeFile(file);

      User user = userService.findUserById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User", userId));

      if(file.isEmpty()) {
        System.out.println("File vuoto o non ricevuto");
        return ResponseEntity.badRequest().body(null);
      }
      Work w = new Work();
      w.setTitle(title);
      w.setBpm(bpm);
      w.setKey(key);
      w.setDataDiCreazione(dataDiCreazione);
      w.setUser(user);

      Audio audio = new Audio();
      audio.setFilePath(savedFilePath);
      audio.setOriginalFileName(fileName);
      w.setAudio(audio);

      Work newWork = workService.saveWork(w);
      WorkDto dto = WorkDto.toDto(newWork);
      URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .replacePath("api/works/{id}")
        .buildAndExpand(newWork.getWorkId())
        .toUri();

      return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWork(@PathVariable int id, @RequestBody WorkDto updateDto) throws DataException, EntityNotFoundException {
      if (id != updateDto.getWorkId()) {
        return ResponseEntity.badRequest().body(("Id del path e id del dto non corrispondono"));
      }
      Optional<Work> opw = workService.findWorkById(id);
      if (opw.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      Work updateWork = workService.updateWork(opw.get());
      return ResponseEntity.ok(WorkDto.toDto(updateWork));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorkById(@PathVariable Integer id) throws DataException, EntityNotFoundException {
        Optional<Work> opw = workService.findWorkById(id);
        if(opw.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWork(@PathVariable Integer id) {
        return workService.findWorkById(id)
          .map(w -> ResponseEntity.ok(WorkDto.toDto(w)))
          .orElse(ResponseEntity.notFound().build());
    }
}
