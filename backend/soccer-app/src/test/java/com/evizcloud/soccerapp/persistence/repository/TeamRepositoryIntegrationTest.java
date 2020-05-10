package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@SpringBootTest
public class TeamRepositoryIntegrationTest {

    @Autowired
    ITeamRepository teamRepository;

    @Test
    public void givenTeamCreated_thenFindByTeamNameMatchesSuccess() {
        Team team1 = new Team("Active Team", "Active Team", LocalDate.now(), LocalDate.now());
        Team team2 = new Team("Active Team", "Active Team", LocalDate.now(), LocalDate.now());
        Team team3 = new Team("Inactive Team", "Inactive Team", LocalDate.now(), LocalDate.now());
        Team team4 = new Team("Inactive Team", "Inactive Team", LocalDate.now(), LocalDate.now());

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        List<Team> retrievedTeams = teamRepository.findByNameMatches("Inactive");
        assertThat(retrievedTeams, contains(team3, team4));
    }

}
