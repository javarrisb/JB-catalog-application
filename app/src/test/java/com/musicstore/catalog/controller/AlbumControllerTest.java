package com.musicstore.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.catalog.model.Album;
import com.musicstore.catalog.repository.AlbumRepository;
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
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    @MockBean
    private AlbumRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnHttpStatus200AndAlbumListOnGetAll() throws Exception {
        Album outputAlbum = new Album(1, "Bless Me", 4, "2022-09-11", 4, 28.99);
        List<Album> outputList = Arrays.asList(outputAlbum);
        String outputJson = mapper.writeValueAsString(outputList);

        doReturn(outputList).when(repo).findAll();

        mockMvc.perform(get("/albums"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetAlbumById() throws Exception {
        Integer id = 1;
        Album outputAlbum = new Album(1, "Bless Me", 4, "2022-09-11", 4, 28.99);
        String outputJson = mapper.writeValueAsString(outputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repo).findById(id);
        mockMvc.perform(get("/albums/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnAlbum() throws Exception {
        Album inputAlbum = new Album(1, "Frozen", 1, "2022-09-02", 4, 30.99);
        String inputJson = mapper.writeValueAsString(inputAlbum);
        Album outputAlbum = new Album(1, "Bless Me", 4, "2022-09-11", 4, 28.99);
        String outputJson = mapper.writeValueAsString(outputAlbum);

        doReturn(outputAlbum).when(repo).save(inputAlbum);
        mockMvc.perform(post("/albums")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnUpdate() throws Exception {
        Integer id = 1;
        Album inputAlbum = new Album(1, "Bless Me", 4, "2022-09-11", 4, 28.99);
        String inputJson = mapper.writeValueAsString(inputAlbum);

        mockMvc.perform(put("/albums/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnHttpStatus204OnDelete() throws Exception {
        mockMvc.perform(delete("/albums/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetAlbumByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/albums/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }
}
