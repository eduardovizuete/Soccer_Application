package com.evizcloud.soccerapp.web.controller;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.model.Team;
import com.evizcloud.soccerapp.service.ILeagueService;
import com.evizcloud.soccerapp.web.dto.LeagueDto;
import com.evizcloud.soccerapp.web.dto.TeamDto;
import org.springframework.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/leagues")
public class LeagueController {

    private ILeagueService leagueService;

    public LeagueController(ILeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/{id}")
    public LeagueDto findOne(@PathVariable Long id) {
        League entity = leagueService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @PostMapping
    public void create(@RequestBody LeagueDto newLeague) {
        League entity = convertToEntity(newLeague);
        this.leagueService.save(entity);
    }

    protected LeagueDto convertToDto(League entity) {
        LeagueDto dto = new LeagueDto(entity.getId(), entity.getName(), entity.getDateCreated());
        dto.setTeams(entity.getTeams()
                .stream()
                .map(t -> convertTeamToDto(t))
                .collect(Collectors.toSet()));

        return dto;
    }

    protected League convertToEntity(LeagueDto dto) {
        League league = new League(dto.getName(), dto.getDateCreated());
        if (!StringUtils.isEmpty(dto.getId())) {
            league.setId(dto.getId());
        }
        return league;
    }

    protected TeamDto convertTeamToDto(Team entity) {
        TeamDto dto = new TeamDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getDateCreated(), entity.getDateFinished(), entity.getStatus());
        return dto;
    }

    protected Team convertTeamToEntity(TeamDto dto) {
        Team team = new Team(dto.getName(), dto.getDescription(), dto.getDateCreated(), dto.getDateFinished(), dto.getStatus());
        if (!StringUtils.isEmpty(dto.getId())) {
            team.setId(dto.getId());
        }
        return team;
    }

}