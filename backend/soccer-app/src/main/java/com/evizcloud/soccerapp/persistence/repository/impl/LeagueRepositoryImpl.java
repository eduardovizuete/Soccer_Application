package com.evizcloud.soccerapp.persistence.repository.impl;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.repository.ILeagueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeagueRepositoryImpl implements ILeagueRepository {

    List<League> leagues = new ArrayList<>();

    @Override
    public Optional<League> findById(Long id) {
        return leagues.stream().filter(league -> league.getId().equals(id)).findFirst();
    }

    @Override
    public League save(League league) {
        League existingLeague = findById(league.getId()).orElse(null);
        if (existingLeague == null) {
            leagues.add(league);
        } else {
            leagues.remove(existingLeague);
            League newLeague = new League(league);
            leagues.add(newLeague);
        }
        return league;
    }

}
