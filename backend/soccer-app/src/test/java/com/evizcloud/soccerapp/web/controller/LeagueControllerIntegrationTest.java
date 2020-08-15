package com.evizcloud.soccerapp.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.evizcloud.soccerapp.web.dto.LeagueDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class LeagueControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static ObjectWriter writer;
    private static ObjectReader reader;

    @BeforeAll
    public static void mapperSetup() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        writer = mapper.writerFor(LeagueDto.class);
        reader = mapper.readerFor(LeagueDto.class);
    }

    @Test
    public void givenDefaultLeaguesPersisted_whenRequestAllLeagues_thenRetrieveListWithEntities() throws Exception {
        // @formatter:off
        this.mvc.perform(get("/leagues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
        // @formatter:on
    }

    @Test
    public void givenDefaultLeaguesPersisted_whenRequestProjectById_thenRetrieveEntity() throws Exception {
        // @formatter:off
        this.mvc.perform(get("/leagues/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("League 1")));
        // @formatter:on
    }

    @Test
    public void givenNewLeague_whenCreateLeague_thenGetEndpointRetrieveEntity() throws Exception {
        // @formatter:off
        LeagueDto newLeague = new LeagueDto(3L, "new league", LocalDate.now());

        this.mvc.perform(post("/leagues").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(newLeague)))
                .andExpect(status().isCreated());

        this.mvc.perform(get("/leagues/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("new league")));
        // @formatter:on
    }

    @Test
    public void givenDefaultLeaguesPersisted_whenUpdateLeague_thenGetEndpointRetrieveEntity() throws Exception {
        // @formatter:off
        LeagueDto createdLeague = createLeague();
        LeagueDto updatedLeague = new LeagueDto(createdLeague.getId(), "updated league", LocalDate.now());

        this.mvc.perform(put("/leagues/" + createdLeague.getId()).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(updatedLeague)))
                .andExpect(status().isOk());

        this.mvc.perform(get("/leagues/" + createdLeague.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdLeague.getId().intValue())))
                .andExpect(jsonPath("$.name", is("updated league")));
        // @formatter:on
    }

    @Test
    public void givenNewLeague_whenDeleteLeague_thenGetEntityRetrieves404() throws Exception {
        // @formatter:off
        LeagueDto createdLeague = createLeague();

        this.mvc.perform(delete("/leagues/" + createdLeague.getId()))
                .andExpect(status().isNoContent());

        this.mvc.perform(get("/leagues/" + createdLeague.getId()))
                .andExpect(status().isNotFound());
        // @formatter:on
    }

    private LeagueDto createLeague() throws Exception {
        // @formatter:off
        LeagueDto newLeague = new LeagueDto(null,
                "new league",
                LocalDate.now());
        MvcResult mvcResult = this.mvc.perform(post("/leagues").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(newLeague)))
                .andExpect(status().isCreated()).andReturn();

        LeagueDto createdLeague = reader.readValue(mvcResult.getResponse().getContentAsByteArray());
        this.mvc.perform(get("/leagues/" + createdLeague.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdLeague.getId().intValue())))
                .andExpect(jsonPath("$.name", is("new league")));

        return createdLeague;
        // @formatter:on
    }

    private static String asJsonString(final Object obj) throws Exception {
        return writer.writeValueAsString(obj);
    }

}