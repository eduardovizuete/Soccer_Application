package com.evizcloud.soccerapp.web.dto;

import java.time.LocalDate;
import java.util.Set;

public class LeagueDto {

    private Long id;

    private String name;

    private LocalDate dateCreated;

    private Set<TeamDto> teams;

    public LeagueDto() {
    }

    public LeagueDto(Long id, String name, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamDto> teams) {
        this.teams = teams;
    }

}
