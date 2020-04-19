package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;

import java.util.Optional;

public interface ILeagueRepository {

    Optional<League> findById(Long id);

    League save(League league);

}
