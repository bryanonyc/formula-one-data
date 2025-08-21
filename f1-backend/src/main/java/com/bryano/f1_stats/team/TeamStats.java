package com.bryano.f1_stats.team;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "team_stats")
public class TeamStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullTeamName;

    private String base;

    private String teamChief;

    private String technicalChief;

    private String chassis;

    private String powerUnit;

    private Integer firstTeamEntry;

    private Integer worldChampionships;

    private String highestRaceFinish;

    private Integer polePositions;

    public TeamStats() {
    }

    public TeamStats(
            Integer id,
            String fullTeamName,
            String base,
            String teamChief,
            String technicalChief,
            String chassis,
            String powerUnit,
            Integer firstTeamEntry,
            Integer worldChampionships,
            String highestRaceFinish,
            Integer polePositions
    ) {
        this.id = id;
        this.fullTeamName = fullTeamName;
        this.base = base;
        this.teamChief = teamChief;
        this.technicalChief = technicalChief;
        this.chassis = chassis;
        this.powerUnit = powerUnit;
        this.firstTeamEntry = firstTeamEntry;
        this.worldChampionships = worldChampionships;
        this.highestRaceFinish = highestRaceFinish;
        this.polePositions = polePositions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTechnicalChief() {
        return technicalChief;
    }

    public void setTechnicalChief(String technicalChief) {
        this.technicalChief = technicalChief;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamStats teamStats = (TeamStats) o;
        return Objects.equals(id, teamStats.id) && Objects.equals(fullTeamName, teamStats.fullTeamName) && Objects.equals(base, teamStats.base) && Objects.equals(teamChief, teamStats.teamChief) && Objects.equals(technicalChief, teamStats.technicalChief) && Objects.equals(chassis, teamStats.chassis) && Objects.equals(powerUnit, teamStats.powerUnit) && Objects.equals(firstTeamEntry, teamStats.firstTeamEntry) && Objects.equals(worldChampionships, teamStats.worldChampionships) && Objects.equals(highestRaceFinish, teamStats.highestRaceFinish) && Objects.equals(polePositions, teamStats.polePositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullTeamName, base, teamChief, technicalChief, chassis, powerUnit, firstTeamEntry, worldChampionships, highestRaceFinish, polePositions);
    }

    @Override
    public String toString() {
        return "TeamStats{" +
                "id=" + id +
                ", fullTeamName='" + fullTeamName + '\'' +
                ", base='" + base + '\'' +
                ", teamChief='" + teamChief + '\'' +
                ", technicalChief='" + technicalChief + '\'' +
                ", chassis='" + chassis + '\'' +
                ", powerUnit='" + powerUnit + '\'' +
                ", firstTeamEntry=" + firstTeamEntry +
                ", worldChampionships=" + worldChampionships +
                ", highestRaceFinish='" + highestRaceFinish + '\'' +
                ", polePositions=" + polePositions +
                '}';
    }
}
