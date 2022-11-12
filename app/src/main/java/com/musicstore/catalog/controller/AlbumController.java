package com.musicstore.catalog.controller;

import com.musicstore.catalog.model.Album;
import com.musicstore.catalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() { return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable Integer id) {
        Optional<Album> returnAmount = repo.findById(id);
        if (returnAmount.isPresent() == false) {
            throw new IllegalArgumentException("No album with id" + id);
        }
        return returnAmount.get();

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Album addNewAlbum(@RequestBody Album album) {
        return repo.save(album);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@PathVariable Integer id, @RequestBody Album album) {
        repo.save(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) { repo.deleteById(id);}
}
