package com.evizcloud.soccerapp.service;

import com.evizcloud.soccerapp.exception.TeamNotSavedException;
import com.evizcloud.soccerapp.persistence.model.Team;

public interface ITeamService {

    Iterable<Team> findAll();

    Team save(Team task) throws TeamNotSavedException;
    
}
