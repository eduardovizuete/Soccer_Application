package com.evizcloud.soccerapp.service.impl;

import com.evizcloud.soccerapp.exception.TeamNotSavedException;
import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.model.Team;
import com.evizcloud.soccerapp.persistence.repository.ILeagueRepository;
import com.evizcloud.soccerapp.service.ILeagueService;
import com.evizcloud.soccerapp.service.ITeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeagueServiceImpl implements ILeagueService {

    private static final Logger LOG = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final ILeagueRepository leagueRepository;

    private final ITeamService teamService;

    public LeagueServiceImpl(ILeagueRepository leagueRepository, ITeamService teamService) {
        this.leagueRepository = leagueRepository;
        this.teamService = teamService;

    }

    @Override
    public Iterable<League> findAll() {
        LOG.info("Project Service >> Finding All Projects");
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> findById(Long id) {
        LOG.info("League Service >> Finding League By Id {}", id);
        return leagueRepository.findById(id);
    }

    @Override
    public League save(League league) {
        LOG.info("League Service >> Saving League {}", league);
        return leagueRepository.save(league);
    }

    @Transactional(rollbackOn = TeamNotSavedException.class)
    @Override
    public void createProjectWithTasks() throws TeamNotSavedException {
        League league = new League("League 1", LocalDate.now());

        League newLeague = save(league);

        Team team1 = new Team("Team 1", "League 1 Team 1", LocalDate.now(), LocalDate.now()
                .plusDays(7));

        teamService.save(team1);

        Set<Team> teams = new HashSet<>();
        teams.add(team1);

        newLeague.setTeams(teams);

        save(newLeague);
    }

    @Override
    public League addTeams(League league, List<Team> teams) {
        league.getTeams()
                .addAll(teams.stream()
                        .filter(t -> !StringUtils.isEmpty(t.getName()))
                        .collect(Collectors.toList()));
        leagueRepository.save(league);

        return league;
    }

}
