package com.bryano.f1_stats.team_archive;

import java.io.Serializable;

public class TeamArchiveKey implements Serializable {
    private Integer year;
    private String team;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
