package com.evizcloud.soccerapp.service.impl;

import com.evizcloud.soccerapp.persistence.model.Team;
import com.evizcloud.soccerapp.persistence.repository.ITeamRepository;
import com.evizcloud.soccerapp.service.ITeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements ITeamService {

    private ITeamRepository teamRepository;

    public TeamServiceImpl(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team save(Team task) {
        return teamRepository.save(task);
    }

} 