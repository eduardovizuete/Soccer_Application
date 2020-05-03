package com.evizcloud.soccerapp.persistence.repository.impl;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.repository.ILeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LeagueRepositoryImpl implements ILeagueRepository {

    private static final Logger LOG =LoggerFactory.getLogger(LeagueRepositoryImpl.class);

    List<League> leagues = new ArrayList<>();

    @Override
    public Optional<League> findById(Long id) {
        LOG.info("Retrieving League using LeagueRepositoryImpl");
        return leagues.stream().filter(league -> league.getId().equals(id)).findFirst();
    }

    @Override
    public League save(League league) {
        LOG.info("Saving League using LeagueRepositoryImpl");
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
