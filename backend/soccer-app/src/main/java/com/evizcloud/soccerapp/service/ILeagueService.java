package com.evizcloud.soccerapp.service;

import com.evizcloud.soccerapp.persistence.model.League;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ILeagueService {

    Optional<League> findById(Long id);

    League save(League league);

}
