package com.evizcloud.soccerapp.service.impl;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.repository.ILeagueRepository;
import com.evizcloud.soccerapp.service.ILeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeagueServiceImpl implements ILeagueService {

    private static final Logger LOG = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final ILeagueRepository leagueRepository;

    public LeagueServiceImpl(ILeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
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
}
