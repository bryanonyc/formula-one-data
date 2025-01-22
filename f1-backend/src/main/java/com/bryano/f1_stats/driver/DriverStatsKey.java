package com.bryano.f1_stats.driver;

import java.io.Serializable;

public class DriverStatsKey implements Serializable {
    private String driver;
    private String team;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
