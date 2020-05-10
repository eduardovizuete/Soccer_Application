package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILeagueRepository extends CrudRepository<League, Long> {

    Optional<League> findByName(String name);

    List<League> findByDateCreatedBetween(LocalDate start, LocalDate end);

}
