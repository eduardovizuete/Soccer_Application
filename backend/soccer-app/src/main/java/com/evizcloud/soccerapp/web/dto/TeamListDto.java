package com.evizcloud.soccerapp.web.dto;

import java.util.ArrayList;
import java.util.List;

public class TeamListDto {

    private List<TeamDto> teams;

    public TeamListDto() {
        teams = new ArrayList<>();
    }

    public TeamListDto(List<TeamDto> teams) {
        this.teams = teams;
    }

    public void addTeam(TeamDto team) {
        this.teams.add(team);
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTasks(List<TeamDto> teams) {
        this.teams = teams;
    }

}
