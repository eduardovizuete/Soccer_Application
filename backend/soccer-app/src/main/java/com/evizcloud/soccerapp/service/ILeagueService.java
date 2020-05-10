package com.evizcloud.soccerapp.service;

import com.evizcloud.soccerapp.exception.TeamNotSavedException;
import com.evizcloud.soccerapp.persistence.model.League;

import java.util.Optional;

public interface ILeagueService {

    Iterable<League> findAll();

    Optional<League> findById(Long id);

    League save(League league);

    void createProjectWithTasks() throws TeamNotSavedException;

}
