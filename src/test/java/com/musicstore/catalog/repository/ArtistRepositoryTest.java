package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    ArtistRepository artistRepository;

    @Before
    public void setUp() throws Exception {
        artistRepository.deleteAll();
    }

    @Test
    public void addArtist() {

        Artist artist = new Artist();
        artist.setName("Lecrae");
        artist.setInstagram("@Lcrae");
        artist.setTwitter("@116Lecrae");

        artist = artistRepository.save(artist);

    }

    @Test
    public void getArtist() {
        Artist artist = new Artist();
        artist.setName("Lecrae");
        artist.setInstagram("@Lcrae");
        artist.setTwitter("@116Lecrae");

        artist = artistRepository.save(artist);

        Optional<Artist> artist1 = artistRepository.findById(artist.getId());
        
        assertEquals(artist1.get(), artist);
    }


   @Test
    public void updateArtist() {
       Artist artist = new Artist();
       artist.setName("Lecrae");
       artist.setInstagram("@Lcrae");
       artist.setTwitter("@116Lecrae");

       artist = artistRepository.save(artist);

       artist.setName("Jay-Z");
       artist.setInstagram("@JazyZ");
       artist.setTwitter("@Hov");

       artistRepository.save(artist);

       Optional<Artist> artist1 = artistRepository.findById(artist.getId());
       assertEquals(artist1.get(), artist);
   }

  /** @Test
    public void deleteArtist() {
       Artist artist = new Artist();
       artist.setName("Lecrae");
       artist.setInstagram("@Lcrae");
       artist.setTwitter("@116Lecrae");


       Optional<Artist> artist1 = artistRepository.findById(artist.getId());
       artistRepository.deleteById(artist.getId());

       assertTrue(artist1.isPresent());
   }
     **/

  @Test
    public void getAllArtists() {
      Artist artist = new Artist();
      artist.setName("Lecrae");
      artist.setInstagram("@Lcrae");
      artist.setTwitter("@116Lecrae");

      artist = artistRepository.save(artist);

       artist = new Artist();
      artist.setName("Jay-Z");
      artist.setInstagram("@JayZ");
      artist.setTwitter("@Hov");

      artist = artistRepository.save(artist);

      List<Artist> artList = artistRepository.findAll();
      assertEquals(artList.size(), 2);
  }
}