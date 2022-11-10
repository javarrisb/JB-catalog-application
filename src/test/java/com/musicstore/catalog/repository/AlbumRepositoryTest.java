package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Album;
import com.musicstore.catalog.model.Artist;
import com.musicstore.catalog.model.Label;
import com.musicstore.catalog.model.Track;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlbumRepositoryTest {

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
    public void addAlbum() {
        Label label = new Label();
        label.setName("Motown");
        label.setWebsite("www.motown.com");
        label = labelRepo.save(label);

        Artist artist = new Artist();
        artist.setName("Marvin Gaye");
        artist.setInstagram("@MarvinG");
        artist.setTwitter("@MGaye");
        artist = artistRepo.save(artist);

        Album album = new Album();
        album.setTitle("Let's Get It On");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(1980, 2, 5));
        album.setListPrice(new BigDecimal("9.99"));

        album = albumRepo.save(album);
    }

    @Test
    public void getAlbum() {
        Label label = new Label();
        label.setName("Motown");
        label.setWebsite("www.motown.com");
        label = labelRepo.save(label);

        Artist artist = new Artist();
        artist.setName("Marvin Gaye");
        artist.setInstagram("@MarvinG");
        artist.setTwitter("@MGaye");
        artist = artistRepo.save(artist);


        Album album = new Album();
        album.setTitle("Let's Get It On");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(1980, 2, 5));
        album.setListPrice(new BigDecimal("9.99"));

        
        album = albumRepo.save(album);

        Optional<Album> album1 = albumRepo.findById(album.getId());
    }

    @Test
    public void getAllAlbums() {
        Label label = new Label();
        label.setName("Motown");
        label.setWebsite("www.motown.com");
        label = labelRepo.save(label);

        Artist artist = new Artist();
        artist.setName("Marvin Gaye");
        artist.setInstagram("@MarvinG");
        artist.setTwitter("@MGaye");
        artist = artistRepo.save(artist);

        Album album = new Album();
        album.setTitle("Let's Get It On");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(1980, 2, 5));
        album.setListPrice(new BigDecimal("9.99"));

        album = albumRepo.save(album);

        album = new Album();
        album.setTitle("Restoration");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2013, 6, 2));
        album.setListPrice(new BigDecimal("20.99"));

        album = albumRepo.save(album);

        List<Album> albumList = albumRepo.findAll();

        Assert.assertEquals(albumList.size(), 2);
    }

    @Test
    public void updateAlbum() {
        Label label = new Label();
        label.setName("Motown");
        label.setWebsite("www.motown.com");
        label = labelRepo.save(label);

        Artist artist = new Artist();
        artist.setName("Marvin Gaye");
        artist.setInstagram("@MarvinG");
        artist.setTwitter("@MGaye");
        artist = artistRepo.save(artist);

        Album album = new Album();
        album.setTitle("Let's Get It On");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(1980, 2, 5));
        album.setListPrice(new BigDecimal("9.99"));

        album = albumRepo.save(album);

        album.setTitle("Thank You");
        album.setReleaseDate(LocalDate.of(2015, 5,7));
        album.setListPrice(new BigDecimal("35.99"));

        albumRepo.save(album);

        Optional<Album> album1 = albumRepo.findById(album.getId());

    }

    @Test
    public void deleteAlbum() {
        Label label = new Label();
        label.setName("Motown");
        label.setWebsite("www.motown.com");
        label = labelRepo.save(label);

        Artist artist = new Artist();
        artist.setName("Marvin Gaye");
        artist.setInstagram("@MarvinG");
        artist.setTwitter("@MGaye");
        artist = artistRepo.save(artist);

        Album album = new Album();
        album.setTitle("Let's Get It On");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(1980, 2, 5));
        album.setListPrice(new BigDecimal("9.99"));

        album = albumRepo.save(album);

        Optional<Album> album1 = albumRepo.findById(album.getId());

        albumRepo.deleteById(album.getId());

        album1 = albumRepo.findById(album.getId());

        Assert.assertFalse(album1.isPresent());
        
    }


}