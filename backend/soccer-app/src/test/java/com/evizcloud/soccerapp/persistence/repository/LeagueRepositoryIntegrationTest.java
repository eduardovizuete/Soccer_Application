package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LeagueRepositoryIntegrationTest {

    @Autowired
    private ILeagueRepository leagueRepository;

    @Test
    public void whenSavingNewLeague_thenSuccess() {
        League league = new League(randomAlphabetic(6), LocalDate.now());

        assertNotNull(leagueRepository.save(league));
    }

    @Test
    public void givenLeague_whenFindById_thenSuccess() {
        League newLeague = new League(randomAlphabetic(6), LocalDate.now());
        leagueRepository.save(newLeague);

        Optional<League> retrievedLeague = leagueRepository.findById(newLeague.getId());

        assertEquals(retrievedLeague.get(), newLeague);
    }

    @Test
    public void givenLeagueCreated_whenFindByName_thenSuccess() {
        League newLeague = new League(randomAlphabetic(6), LocalDate.now());
        leagueRepository.save(newLeague);

        Optional<League> retrievedLeague = leagueRepository.findByName(newLeague.getName());
        assertEquals(retrievedLeague.get(), newLeague);
    }

    @Test
    public void givenLeagueCreated_whenFindByDateCreatedBetween_thenSuccess() {
        League oldLeague = new League(randomAlphabetic(6), LocalDate.now().minusYears(1));
        leagueRepository.save(oldLeague);

        League newLeague = new League(randomAlphabetic(6), LocalDate.now());
        leagueRepository.save(newLeague);

        League newLeague2 = new League(randomAlphabetic(6), LocalDate.now());
        leagueRepository.save(newLeague2);

        List<League> retreivedLeagues = leagueRepository.findByDateCreatedBetween(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1));
        assertThat(retreivedLeagues, hasItems(newLeague, newLeague2));
    }

}
