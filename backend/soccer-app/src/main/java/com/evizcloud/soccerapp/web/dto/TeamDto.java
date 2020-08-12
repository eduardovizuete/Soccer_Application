package com.evizcloud.soccerapp.web.dto;

import com.evizcloud.soccerapp.persistence.model.TeamStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TeamDto {

    private Long id;

    private String name;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreated;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFinished;

    private TeamStatus status;

    public TeamDto() {
    }

    public TeamDto(Long id, String name, String description, LocalDate dateCreated, LocalDate dateFinished, TeamStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

}
