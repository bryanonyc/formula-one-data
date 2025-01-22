package com.bryano.f1_stats.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverStatsService {
    private final DriverStatsRepository driverStatsRepository;

    @Autowired
    public DriverStatsService(DriverStatsRepository driverStatsRepository) {
        this.driverStatsRepository = driverStatsRepository;
    }

    public List<DriverStats> getDrivers() {
        return driverStatsRepository.findAll();
    }

    public List<DriverStats> getDriversByName(String name) {
        return driverStatsRepository
                .findAll()
                .stream()
                .filter(driverStats -> driverStats.getDriver().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<DriverStats> getDriversByTeam(String team) {
        return driverStatsRepository
                .findAll()
                .stream()
                .filter(driverStats -> driverStats.getTeam().toLowerCase().contains(team.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<DriverStats> getDriversByCountry(String country) {
        return driverStatsRepository
                .findAll()
                .stream()
                .filter(driverStats -> driverStats.getCountry().toLowerCase().contains(country.toLowerCase()))
                .collect(Collectors.toList());
    }
}
