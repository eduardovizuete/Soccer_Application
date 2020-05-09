package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;
import org.springframework.data.repository.CrudRepository;

public interface ILeagueRepository extends CrudRepository<League, Long> {

}
