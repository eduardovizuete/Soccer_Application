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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/leagues")
public class LeagueController {

    final private ILeagueService leagueService;

    public LeagueController(ILeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public String getLeagues(Model model) {
        Iterable<League> leagues = leagueService.findAll();
        List<LeagueDto> leagueDtos = new ArrayList<>();
        leagues.forEach(p -> leagueDtos.add(convertToDto(p)));
        model.addAttribute("leagues", leagueDtos);
        return "leagues";
    }

    @GetMapping("/new")
    public String newLeague(Model model) {
        model.addAttribute("league", new LeagueDto());
        return "new-league";
    }

    @GetMapping(value = "/{id}")
    public String getProject(@PathVariable Long id, Model model) {
        League league = leagueService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("league", convertToDto(league));

        return "league";
    }

    @PostMapping
    public String addLeague(@Valid @ModelAttribute("league") LeagueDto newLeague, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-league";
        }

        leagueService.save(convertToEntity(newLeague));
        return "redirect:/leagues";
    }

    @GetMapping("/{id}/add-teams")
    public String getProjectEditPage(@PathVariable Long id, Model model) {
        League league = leagueService.findById(id)
                .orElse(new League());
        model.addAttribute("league", league);
        TeamListDto teamsForm = new TeamListDto();
        for (int i = 1; i <= 3; i++) {
            teamsForm.addTeam(new TeamDto());
        }
        model.addAttribute("teamsForm", teamsForm);
        return "add-teams";
    }

    @PostMapping("{id}/save-teams")
    public String saveTeams(@ModelAttribute TeamListDto teamsForm, @PathVariable Long id, Model model) {
        League league = leagueService.findById(id)
                .orElse(new League());
        leagueService.addTeams(league, teamsForm.getTeams()
                .stream()
                .map(this::convertTeamToEntity)
                .collect(Collectors.toList()));
        model.addAttribute("league", league);

        return "redirect:/leagues/" + league.getId();
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