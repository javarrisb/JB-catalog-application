package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelRepositoryTest {

    @Autowired
    TrackRepository trackRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    ArtistRepository artistRepo;

    @Autowired
    LabelRepository labelRepo;

    @Before
    public void setUp() throws Exception {
        trackRepo.deleteAll();
        albumRepo.deleteAll();
        artistRepo.deleteAll();
        labelRepo.deleteAll();
    }

    @Test
    public void addLabel() {
        Label label = new Label();
        label.setName("RCA");
        label.setWebsite("www.rca.com");
        labelRepo.save(label);
    }

    @Test
    public void getLabel() {
        Label label = new Label();
        label.setName("RCA");
        label.setWebsite("www.rca.com");
        labelRepo.save(label);

        Optional<Label> label1 = labelRepo.findById(label.getId());
        assertEquals(label1.get(), label);
    }

    @Test
    public void getAllLabels() {
        Label label = new Label();
        label.setName("RCA");
        label.setWebsite("www.rca.com");
        labelRepo.save(label);

        label = new Label();
        label.setName("Rocafella");
        label.setWebsite("www.rocafella.com");
        labelRepo.save(label);

        List<Label> labelList = labelRepo.findAll();
        assertEquals(labelList.size(), 2);
    }

    @Test
    public void updateLabel() {
        Label label = new Label();
        label.setName("RCA");
        label.setWebsite("www.rca.com");
        labelRepo.save(label);

         label = new Label();
        label.setName("Reach Records");
        label.setWebsite("www.reach-records.com");
        labelRepo.save(label);

        Optional<Label> label1 = labelRepo.findById(label.getId());
        assertEquals(label1.get(), label);
    }

    @Test
    public void deleteLabel() {
        Label label = new Label();
        label.setName("RCA");
        label.setWebsite("www.rca.com");
        labelRepo.save(label);

        Optional<Label> label1 = labelRepo.findById(label.getId());

        labelRepo.deleteById(label.getId());

        label1 = labelRepo.findById(label.getId());

        assertFalse(label1.isPresent());
    }

}
