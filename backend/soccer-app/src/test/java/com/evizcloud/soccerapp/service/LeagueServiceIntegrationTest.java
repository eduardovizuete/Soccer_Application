package com.evizcloud.soccerapp.service;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringJUnitConfig(value = TestConfig.class)
public class LeagueServiceIntegrationTest {

    @Autowired
    private ILeagueService leagueService;

    @Test
    public void whenSavingLeague_thenOK() {
        League savedLeague = leagueService.save(new League("name", LocalDate.now()));
        assertThat(savedLeague, is(notNullValue()));
    }

}
