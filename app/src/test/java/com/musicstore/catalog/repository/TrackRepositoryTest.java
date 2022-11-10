package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Album;
import com.musicstore.catalog.model.Artist;
import com.musicstore.catalog.model.Label;
import com.musicstore.catalog.model.Track;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

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
    public void addTrack() {
        // Have to create Artist, Label and Album
        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);

        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

    }

    @Test
    public void getTrack() {

        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);
        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

        Optional<Track> track1 = trackRepo.findById(track.getId());
        assertEquals(track1.get(), track);
    }

    @Test
    public void getAllTracks(){

        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);
        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Album album1 = new Album();
        album.setTitle("Now 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2022, 4, 11));
        album.setListPrice(new BigDecimal("14.99"));
        album1 = albumRepo.save(album1);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

        track = new Track();
        track.setTitle("Its All Over");
        track.setRuntime(150);
        track.setAlbumId(album1.getId());
        track = trackRepo.save(track);

        List<Track> trackList = trackRepo.findAll();

        assertEquals(trackList.size(), 2);
    }

    @Test
    public void getTracksByAlbumId() {
        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);
        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Album album1 = new Album();
        album.setTitle("Now 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2022, 4, 11));
        album.setListPrice(new BigDecimal("14.99"));
        album1 = albumRepo.save(album1);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

        track = new Track();
        track.setTitle("Its All Over");
        track.setRuntime(150);
        track.setAlbumId(album1.getId());
        track = trackRepo.save(track);

        track = new Track();
        track.setTitle("Love");
        track.setRuntime(200);
        track.setAlbumId(album1.getId());
        track = trackRepo.save(track);

        List<Track> trackList = trackRepo.findByAlbumId(album.getId());
        assertEquals(trackList.size(), 1);

        trackList = trackRepo.findByAlbumId(album1.getId());
        assertEquals(trackList.size(), 2);
    }

    @Test
    public void updateTrack() {
        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);

        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

        track.setTitle("Rumors");
        track.setRuntime(200);

        trackRepo.save(track);

        Optional<Track> track1 = trackRepo.findById(track.getId());
        assertEquals(track1.get(), track);
    }

    @Test
    public void deleteTrack() {
        Artist artist = new Artist();
        artist.setName("Elevation Worship");
        artist.setInstagram("@ElevationWorship");
        artist.setTwitter("@EWorship");
        artist = artistRepo.save(artist);

        Label label = new Label();
        label.setName("Sony");
        label.setWebsite("www.sony.com");
        label = labelRepo.save(label);

        Album album = new Album();
        album.setTitle("Wow 2022");
        album.setArtistId(artist.getId());
        album.setLabelId(label.getId());
        album.setReleaseDate(LocalDate.of(2011, 2, 9));
        album.setListPrice(new BigDecimal("12.99"));
        album = albumRepo.save(album);

        Track track = new Track();
        track.setTitle("Look At Me Now");
        track.setRuntime(185);
        track.setAlbumId(album.getId());
        track = trackRepo.save(track);

        Optional<Track> track1 = trackRepo.findById(track.getId());
        trackRepo.deleteById(track.getId());

        track1 = trackRepo.findById(track.getId());

        assertFalse(track1.isPresent());
    }
}
