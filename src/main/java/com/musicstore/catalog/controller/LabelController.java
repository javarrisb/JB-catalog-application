package com.musicstore.catalog.controller;


import com.musicstore.catalog.model.Label;
import com.musicstore.catalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    LabelRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getLabels() {return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabelById(@PathVariable int id) {
        Optional<Label> returnAmount = repo.findById(id);
        if (returnAmount.isPresent() == false) {
            throw new IllegalArgumentException("No label with id" + id);
        }
        return returnAmount.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Label addNewLabel(@RequestBody Label label) {return repo.save(label);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody Label label) {
        repo.save(label);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) { repo.deleteById(id);}
}
