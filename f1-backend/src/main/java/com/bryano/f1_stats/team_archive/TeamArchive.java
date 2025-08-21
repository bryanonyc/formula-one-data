package com.bryano.f1_stats.team_archive;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class TeamArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;

    private String position;

    private String team;

    private Float points;

    public TeamArchive() {
    }

    public TeamArchive(
            Integer id,
            Integer year,
            String position,
            String team,
            Float points
    ) {
        this.id = id;
        this.year = year;
        this.position = position;
        this.team = team;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamArchive that = (TeamArchive) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(position, that.position) && Objects.equals(team, that.team) && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, position, team, points);
    }

    @Override
    public String toString() {
        return "TeamArchive{" +
                "id=" + id +
                ", year=" + year +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                ", points=" + points +
                '}';
    }
}
