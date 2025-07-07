package com.example.app.controllers;

import com.example.app.exceptions.EntityNotFoundException;
import com.example.app.models.dtos.WorkDto;
import com.example.app.models.entities.User;
import com.example.app.models.entities.Work;
import com.example.app.models.searchCriteria.WorkFilterCriteria;
import com.example.app.models.services.WorkService;
import org.apache.coyote.Response;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/works")
public class WorkController {
    private WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
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
    public List<Work> getAllWorks() {
        return workService.findAllWorks();
    }

    @GetMapping("/by-user/{userId}")
    public List<Work> getWorksDoneByUserId(@PathVariable int userId) {
      return workService.findByUserUserId(userId);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable int id, @RequestBody WorkDto updateDto) throws DataException, EntityNotFoundException {
        if(id != updateDto.getWorkId()) {
            return ResponseEntity.badRequest().body(("Id del path e id del dto non corrispondono"));
        }
        Optional<Work> opw = workService.findWorkById(id);
        if(opw.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Work updateWork = workService.updateWork(opw.get());
        return ResponseEntity.ok(WorkDto.toDto(updateWork));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkById(@PathVariable Integer id) throws DataException, EntityNotFoundException {
        Optional<Work> opw = workService.findWorkById(id);
        if(opw.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }
}
