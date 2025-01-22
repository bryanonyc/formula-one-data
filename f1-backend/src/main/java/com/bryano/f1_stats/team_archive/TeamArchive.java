package com.bryano.f1_stats.team_archive;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(TeamArchiveKey.class)
public class TeamArchive {
    @Id
    private Integer year;

    private String position;

    @Id
    private String team;

    private Float points;

    public TeamArchive() {}

    public TeamArchive(Integer year, String position, String team, Float points) {
        this.year = year;
        this.position = position;
        this.team = team;
        this.points = points;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }
}
