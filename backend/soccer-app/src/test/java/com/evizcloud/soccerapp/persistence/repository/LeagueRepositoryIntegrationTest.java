package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void givenDataCreated_whenFindAllPaginated_thenSuccess() {
        Page<League> retrievedLeagues = leagueRepository.findAll(PageRequest.of(0, 2));

        assertThat(retrievedLeagues.getContent(), hasSize(2));
    }

    @Test
    public void givenDataCreated_whenFindAllSort_thenSuccess() {
        List<League> retrievedLeagues = (List<League>) leagueRepository.findAll(Sort.by(Sort.Order.asc("name")));

        List<League> sortedLeagues = retrievedLeagues.stream().collect(Collectors.toList());
        sortedLeagues.sort(Comparator.comparing(League::getName));

        assertEquals(sortedLeagues, retrievedLeagues);
    }

    @Test
    public void givenDataCreated_whenFindAllPaginatedAndSort_thenSuccess() {
        Iterable<League> retrievedLeagues = leagueRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Order.asc("name"))));

        List<League> projectList = new ArrayList<>();
        retrievedLeagues.forEach(projectList::add);

        assertThat(projectList, hasSize(2));
    }

}
