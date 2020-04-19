package com.evizcloud.soccerapp.service.impl;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.repository.ILeagueRepository;
import com.evizcloud.soccerapp.service.ILeagueService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ILeagueServiceImpl implements ILeagueService {

    private ILeagueRepository leagueRepository;

    public ILeagueServiceImpl(ILeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public Optional<League> findById(Long id) {
        return leagueRepository.findById(id);
    }

    @Override
    public League save(League league) {
        return leagueRepository.save(league);
    }
}
