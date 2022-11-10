package com.musicstore.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.catalog.model.Label;
import com.musicstore.catalog.repository.LabelRepository;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {

    @MockBean
    private LabelRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnHttpStatus200AndArtistListOnGetAll() throws Exception {
        Label outputLabel = new Label(1, "Sony", "www.sony.com");
        List<Label> outputList = Arrays.asList(outputLabel);
        String outputJson = mapper.writeValueAsString(outputList);

        doReturn(outputList).when(repo).findAll();

        mockMvc.perform(get("/labels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus200AndGetLabelById() throws Exception {
        Integer id = 1;
        Label outputLabel = new Label(1, "Sony", "www.sony.com");
        String outputJson = mapper.writeValueAsString(outputLabel);

        doReturn(Optional.of(outputLabel)).when(repo).findById(id);
        mockMvc.perform(get("/labels/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnHttpStatus201AndPostOnLabel() throws Exception {
        Label inputLabel = new Label(1, "Sony", "www.sony.com");
        String inputJson = mapper.writeValueAsString(inputLabel);
        Label outputLabel = new Label(1, "Sony", "www.sony.com");
        String outputJson = mapper.writeValueAsString(outputLabel);

        doReturn(outputLabel).when(repo).save(inputLabel);
        mockMvc.perform(post("/labels")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnHttpStatus204OnUpdate() throws Exception {
        Integer id = 1;
        Label inputLabel = new Label(1, "Sony", "www.sony.com");
        String inputJson = mapper.writeValueAsString(inputLabel);

        mockMvc.perform(put("/labels/{id}", id)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnHttpStatus204OnDelete() throws Exception {
        mockMvc.perform(delete("/labels/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWithHttpStatus422WhenGetLabelByIdNotFound() throws Exception {
        Integer id = 1;

        doReturn(Optional.empty()).when(repo).findById(id);
        mockMvc.perform(get("/labels/{id}", id))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }
}
