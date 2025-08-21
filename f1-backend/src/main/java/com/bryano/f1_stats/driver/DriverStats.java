package com.bryano.f1_stats.driver;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "driver_stats")
public class DriverStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String driver;

    private String team;

    private String country;

    private Integer podiums;

    private Float points;

    private Integer grandsPrixEntered;

    private Integer worldChampionships;

    private String highestRaceFinish;

    private String highestGridPosition;

    private String dateOfBirth;

    private String placeOfBirth;

    public DriverStats() {
    }

    public DriverStats(
            Integer id,
            String driver,
            String team,
            String country,
            Integer podiums,
            Float points,
            Integer grandsPrixEntered,
            Integer worldChampionships,
            String highestRaceFinish,
            String highestGridPosition,
            String dateOfBirth,
            String placeOfBirth
    ) {
        this.id = id;
        this.driver = driver;
        this.team = team;
        this.country = country;
        this.podiums = podiums;
        this.points = points;
        this.grandsPrixEntered = grandsPrixEntered;
        this.worldChampionships = worldChampionships;
        this.highestRaceFinish = highestRaceFinish;
        this.highestGridPosition = highestGridPosition;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(Integer podiums) {
        this.podiums = podiums;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public Integer getGrandsPrixEntered() {
        return grandsPrixEntered;
    }

    public void setGrandsPrixEntered(Integer grandsPrixEntered) {
        this.grandsPrixEntered = grandsPrixEntered;
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

    public String getHighestGridPosition() {
        return highestGridPosition;
    }

    public void setHighestGridPosition(String highestGridPosition) {
        this.highestGridPosition = highestGridPosition;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverStats driverStats1 = (DriverStats) o;
        return Objects.equals(id, driverStats1.id) && Objects.equals(driver, driverStats1.driver) && Objects.equals(team, driverStats1.team) && Objects.equals(country, driverStats1.country) && Objects.equals(podiums, driverStats1.podiums) && Objects.equals(points, driverStats1.points) && Objects.equals(grandsPrixEntered, driverStats1.grandsPrixEntered) && Objects.equals(worldChampionships, driverStats1.worldChampionships) && Objects.equals(highestRaceFinish, driverStats1.highestRaceFinish) && Objects.equals(highestGridPosition, driverStats1.highestGridPosition) && Objects.equals(dateOfBirth, driverStats1.dateOfBirth) && Objects.equals(placeOfBirth, driverStats1.placeOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, team, country, podiums, points, grandsPrixEntered, worldChampionships, highestRaceFinish, highestGridPosition, dateOfBirth, placeOfBirth);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", driver='" + driver + '\'' +
                ", team='" + team + '\'' +
                ", country='" + country + '\'' +
                ", podiums=" + podiums +
                ", points=" + points +
                ", grandsPrixEntered=" + grandsPrixEntered +
                ", worldChampionships=" + worldChampionships +
                ", highestRaceFinish='" + highestRaceFinish + '\'' +
                ", highestGridPosition=" + highestGridPosition +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}

