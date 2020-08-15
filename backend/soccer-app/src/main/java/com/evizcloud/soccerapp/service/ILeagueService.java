package com.evizcloud.soccerapp.service;

import com.evizcloud.soccerapp.exception.TeamNotSavedException;
import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.model.Team;

import java.util.List;
import java.util.Optional;

public interface ILeagueService {

    Iterable<League> findAll();

    Optional<League> findById(Long id);

    Iterable<League> findByName(String name);

    League save(League league);

    void createProjectWithTasks() throws TeamNotSavedException;

    League addTeams(League league, List<Team> teams);

    void delete(Long id);

}
