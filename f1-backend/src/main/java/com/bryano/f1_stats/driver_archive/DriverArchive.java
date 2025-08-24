package com.bryano.f1_stats.driver_archive;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "driver_archive")
public class DriverArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;

    private String position;

    private String driver;

    private String nationality;

    private String car;

    private Float points;

    public DriverArchive() {
    }

    public DriverArchive(
            Integer id,
            Integer year,
            String position,
            String driver,
            String nationality,
            String car,
            Float points
    ) {
        this.id = id;
        this.year = year;
        this.position = position;
        this.driver = driver;
        this.nationality = nationality;
        this.car = car;
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
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
        DriverArchive that = (DriverArchive) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(position, that.position) && Objects.equals(driver, that.driver) && Objects.equals(nationality, that.nationality) && Objects.equals(car, that.car) && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, position, driver, nationality, car, points);
    }

    @Override
    public String toString() {
        return "DriverArchive{" +
                "id=" + id +
                ", year=" + year +
                ", position='" + position + '\'' +
                ", driver='" + driver + '\'' +
                ", nationality='" + nationality + '\'' +
                ", car='" + car + '\'' +
                ", points=" + points +
                '}';
    }
}
