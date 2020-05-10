package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITeamRepository extends CrudRepository<Team, Long> {

    @Query("select t from Team t where t.name like %?1%")
    List<Team> findByNameMatches(String name);

}
