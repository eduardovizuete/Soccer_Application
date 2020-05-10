package com.evizcloud.soccerapp.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dateFinished;

    private TeamStatus status;

    public Team() {

    }

    public Team(String name, String description, LocalDate dateCreated, LocalDate dateFinished) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.status = TeamStatus.ACTIVE;
    }

    public Team(Team task) {
        this(task.getName(), task.getDescription(), task.getDateCreated(), task.getDateFinished());
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

    public void setDateFinished(LocalDate dueDate) {
        this.dateFinished = dueDate;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(description, team.description) &&
                Objects.equals(dateCreated, team.dateCreated) &&
                Objects.equals(dateFinished, team.dateFinished) &&
                status == team.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateCreated, dateFinished, status);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateFinished=" + dateFinished +
                ", status=" + status +
                '}';
    }
}
