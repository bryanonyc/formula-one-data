package com.bryano.f1_stats.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverStatsController {
    private final DriverStatsService driverStatsService;

    @Autowired
    public DriverStatsController(DriverStatsService driverStatsService) {
        this.driverStatsService = driverStatsService;
    }

    @GetMapping
    public List<DriverStats> getDriverStats(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String country
    ) {
        if (name != null) {
            return driverStatsService.getDriversByName(name);
        } else if (team != null) {
            return driverStatsService.getDriversByTeam(team);
        } else if (country != null) {
            return driverStatsService.getDriversByCountry(country);
        } else {
            return driverStatsService.getDrivers();
        }
    }
}
