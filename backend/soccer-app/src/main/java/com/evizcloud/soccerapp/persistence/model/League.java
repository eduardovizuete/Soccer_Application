package com.evizcloud.soccerapp.persistence.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class League {

    private Long id;
    private String name;
    private LocalDate dateCreated;

    public League(Long id, String name, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public League(String name, LocalDate dateCreated) {
        this.id = new Random().nextLong();
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public League(League league) {
        this(league.getId(), league.getName(), league.getDateCreated());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return id.equals(league.id) &&
                name.equals(league.name) &&
                dateCreated.equals(league.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateCreated);
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
