package com.evizcloud.soccerapp.web.controller;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.persistence.model.Team;
import com.evizcloud.soccerapp.service.ILeagueService;
import com.evizcloud.soccerapp.web.dto.LeagueDto;
import com.evizcloud.soccerapp.web.dto.TeamDto;
import com.evizcloud.soccerapp.web.dto.TeamListDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/leagues")
public class LeagueController {

    final private ILeagueService leagueService;

    public LeagueController(ILeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/{id}")
    public LeagueDto findOne(@PathVariable Long id) {
        League entity = leagueService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found"));
        return convertToDto(entity);
    }

    // PathVariable with Regular Expressions
    // request example http://localhost:8080/leagues/teamA-12/1
    @GetMapping(value = "/{team}-{subcategoryId:\\d\\d}/{id}")
    public LeagueDto findOneRegExp(@PathVariable Long id, @PathVariable String team, @PathVariable Integer subcategoryId) {
        League entity = leagueService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found"));
        return convertToDto(entity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeagueDto create(@RequestBody LeagueDto newLeague) {
        League entity = convertToEntity(newLeague);
        return this.convertToDto(this.leagueService.save(entity));
    }

    // request example http://http://localhost:8080/leagues?name=1
    @GetMapping
    public Collection<LeagueDto> findLeagues(@RequestParam(name = "name", defaultValue = "") String name) {
        Iterable<League> allLeagues = this.leagueService.findByName(name);
        List<LeagueDto> projectDtos = new ArrayList<>();
        allLeagues.forEach(p -> projectDtos.add(convertToDto(p)));
        return projectDtos;
    }

    @PutMapping("/{id}")
    public LeagueDto updateLeague(@PathVariable("id") Long id, @RequestBody LeagueDto updatedLeague) {
        League projectEntity = convertToEntity(updatedLeague);
        return this.convertToDto(this.leagueService.save(projectEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeague(@PathVariable("id") Long id) {
        leagueService.delete(id);
    }

    protected LeagueDto convertToDto(League entity) {
        LeagueDto dto = new LeagueDto(entity.getId(), entity.getName(), entity.getDateCreated());
        dto.setTeams(entity.getTeams()
                .stream()
                .map(this::convertTeamToDto)
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
        return new TeamDto(entity.getId(), entity.getName(),
                entity.getDescription(), entity.getDateCreated(),
                entity.getDateFinished(), entity.getStatus());
    }

    protected Team convertTeamToEntity(TeamDto dto) {
        Team team = new Team(dto.getName(), dto.getDescription(), dto.getDateCreated(), dto.getDateFinished(), dto.getStatus());
        if (!StringUtils.isEmpty(dto.getId())) {
            team.setId(dto.getId());
        }
        return team;
    }

}