package com.evizcloud.soccerapp.persistence.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateCreated;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id")
    private Set<Team> teams;

    public League() { }

    public League(String name, LocalDate dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.teams = new HashSet<>();
    }

    public League(League league) {
        this(league.getName(), league.getDateCreated());
        this.teams = league.getTeams()
                .stream()
                .collect(Collectors.toSet());
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return Objects.equals(id, league.id) &&
                Objects.equals(name, league.name) &&
                Objects.equals(dateCreated, league.dateCreated) &&
                Objects.equals(teams, league.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateCreated, teams);
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", teams=" + teams +
                '}';
    }

}
