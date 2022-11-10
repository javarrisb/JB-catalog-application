package com.musicstore.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.catalog.model.Artist;
import com.musicstore.catalog.repository.ArtistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @MockBean
    private ArtistRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnHttpStatus200AndArtistListOnGetAll() throws Exception {
        Artist outputArtist = new Artist(1, "Taylor Swift" ,"@TSwift", "@Swifties");
        List<Artist> outputList = Arrays.asList(outputArtist);
        String outputJson = mapper.writeValueAsString(outputList);

        doReturn(outputList).when(repo).findAll();

        mockMvc.perform(get("/artists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetArtistById() throws Exception {
        Integer id = 1;
        Artist outputArtist = new Artist(1, "Taylor Swift", "@TSwift", "@Swifties");
        String outputJson = mapper.writeValueAsString(outputArtist);

        doReturn(Optional.of(outputArtist)).when(repo).findById(id);
        mockMvc.perform(get("/artists/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnArtist() throws Exception {
        Artist inputArtist = new Artist(1,"Taylor Swift", "@TSwift", "@Swifties");
        String inputJson = mapper.writeValueAsString(inputArtist);
        Artist outputArtist = new Artist(1, "Taylor Swift", "@TSwift", "@Swifties");
        String outputJson = mapper.writeValueAsString(outputArtist);

        doReturn(outputArtist).when(repo).save(inputArtist);
        mockMvc.perform(post("/artists")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnUpdate() throws Exception {
        Integer id = 1;
        Artist inputArtist = new Artist(1, "Taylor Swift", "@TSwift", "@Swifties");
        String inputJson = mapper.writeValueAsString(inputArtist);

        mockMvc.perform(put("/artists/{id}", id)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnHttpStatus204OnDelete() throws Exception {
        mockMvc.perform(delete("/artists/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetArtistByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/artists/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

 }