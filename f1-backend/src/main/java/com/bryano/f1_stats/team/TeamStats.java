package com.bryano.f1_stats.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_stats")
public class TeamStats {
    @Id
    @Column(unique = true)
    private String fullTeamName;

    private String base;

    private String teamChief;

    private String chassis;

    private String powerUnit;

    private Integer firstTeamEntry;

    private Integer worldChampionships;

    private String highestRaceFinish;

    private Integer polePositions;

    private Integer fastestLaps;

    public TeamStats() {}

    public TeamStats(String fullTeamName, String base, String teamChief, String chassis, String powerUnit, Integer firstTeamEntry, Integer worldChampionships, String highestRaceFinish, Integer polePositions, Integer fastestLaps) {
        this.fullTeamName = fullTeamName;
        this.base = base;
        this.teamChief = teamChief;
        this.chassis = chassis;
        this.powerUnit = powerUnit;
        this.firstTeamEntry = firstTeamEntry;
        this.worldChampionships = worldChampionships;
        this.highestRaceFinish = highestRaceFinish;
        this.polePositions = polePositions;
        this.fastestLaps = fastestLaps;
    }

    public String getFullTeamName() {
        return fullTeamName;
    }

    public void setFullTeamName(String fullTeamName) {
        this.fullTeamName = fullTeamName;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTeamChief() {
        return teamChief;
    }

    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        this.powerUnit = powerUnit;
    }

    public Integer getFirstTeamEntry() {
        return firstTeamEntry;
    }

    public void setFirstTeamEntry(Integer firstTeamEntry) {
        this.firstTeamEntry = firstTeamEntry;
    }

    public Integer getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(Integer worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public String getHighestRaceFinish() {
        return highestRaceFinish;
    }

    public void setHighestRaceFinish(String highestRaceFinish) {
        this.highestRaceFinish = highestRaceFinish;
    }

    public Integer getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(Integer polePositions) {
        this.polePositions = polePositions;
    }

    public Integer getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(Integer fastestLaps) {
        this.fastestLaps = fastestLaps;
    }
}
