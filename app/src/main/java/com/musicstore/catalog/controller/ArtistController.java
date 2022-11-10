package com.musicstore.catalog.controller;

import com.musicstore.catalog.model.Artist;
import com.musicstore.catalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    ArtistRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable int id) {
        Optional<Artist> returnAmount = repo.findById(id);
        if (returnAmount.isPresent() == false) {
            throw new IllegalArgumentException("No artist with id" + id);
        }
        return returnAmount.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist addArtist(@RequestBody Artist artist) { return  repo.save(artist);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody Artist artist) { repo.save(artist);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable int id) {repo.deleteById(id);}
}
