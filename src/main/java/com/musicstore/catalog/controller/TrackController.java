package com.musicstore.catalog.controller;

import com.musicstore.catalog.model.Track;
import com.musicstore.catalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    TrackRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getTracks() { return repo.findAll();}

     @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable Integer id) {
        Optional<Track> returnAmount = repo.findById(id);
        if (returnAmount.isPresent() == false) {
            throw new IllegalArgumentException("No track with id" + id);
        }
        return returnAmount.get();
     }

     @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Track addNewTrack(@RequestBody Track track) {return  repo.save(track);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@PathVariable Integer id, @RequestBody Track track) {
        repo.save(track);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Integer id) { repo.deleteById(id);}

}
