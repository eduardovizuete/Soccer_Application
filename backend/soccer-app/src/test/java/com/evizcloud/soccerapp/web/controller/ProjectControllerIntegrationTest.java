package com.evizcloud.soccerapp.web.controller;

import com.evizcloud.soccerapp.service.ILeagueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ILeagueService leagueService;

    @Test
    public void whenFormWithMissingField_thenErrorTriggered() throws Exception {
        mockMvc.perform(post("/leagues").param("name", ""))
                .andExpect(view().name("new-league"))
                .andExpect(model().attributeHasFieldErrors("league", "name"));
    }

}