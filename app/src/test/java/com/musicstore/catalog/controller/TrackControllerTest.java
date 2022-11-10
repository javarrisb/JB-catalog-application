package com.musicstore.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;;
import com.musicstore.catalog.model.Track;
import com.musicstore.catalog.repository.TrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
    @MockBean
    private TrackRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnHttpStatus200AndArtistListOnGetAll() throws Exception {
        Track outputTrack = new Track(1,1,"Let It Go", 180);
        List<Track> outputList = Arrays.asList(outputTrack);
        String outputJson = mapper.writeValueAsString(outputList);

        doReturn(outputList).when(repo).findAll();

        mockMvc.perform(get("/tracks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetTrackById() throws Exception {
        Integer id = 1;
        Track outputTrack = new Track(1,1,"Let It Go", 180);
        String outputJson = mapper.writeValueAsString(outputTrack);

        doReturn(Optional.of(outputTrack)).when(repo).findById(id);
        mockMvc.perform(get("/tracks/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnTrack() throws Exception {
        Track inputTrack = new Track(1,1,"Let It Go", 180);
        String inputJson = mapper.writeValueAsString(inputTrack);
        Track outputTrack = new Track(1,1,"Let It Go", 180);
        String outputJson = mapper.writeValueAsString(outputTrack);

        doReturn(outputTrack).when(repo).save(inputTrack);
        mockMvc.perform(post("/tracks")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnUpdate() throws Exception {
        Integer id = 1;
        Track inputTrack = new Track(1,1,"Let It Go", 180);
        String inputJson = mapper.writeValueAsString(inputTrack);

        mockMvc.perform(put("/tracks/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnHttpStatus204OnDelete() throws Exception {
        mockMvc.perform(delete("/tracks/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetTrackByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/tracks/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }
}
