package com.bryano.f1_stats.driver;

import http.DataScraperHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverStatsService {
    private final DriverStatsRepository driverStatsRepository;
    private final DataScraperHttpClient dataScraperHttpClient;
    private final DriverMapper driverMapper;

    @Autowired
    public DriverStatsService(DriverStatsRepository driverStatsRepository, DataScraperHttpClient dataScraperHttpClient, DriverMapper driverMapper) {
        this.driverStatsRepository = driverStatsRepository;
        this.dataScraperHttpClient = dataScraperHttpClient;
        this.driverMapper = driverMapper;
    }

    public List<DriverStats> getDrivers() {
        return driverStatsRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(DriverStats::getTeam)
                        .thenComparing(DriverStats::getDriver)
                )
                .toList();
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

    public List<DriverStats> getUpdatedDrivers() throws IOException, InterruptedException {
        List<ScrapedDriver> scrapedDrivers = dataScraperHttpClient.scrapeDrivers();
        List<DriverStats> existingDrivers = driverStatsRepository.findAll();
        List<DriverStats> updatedDrivers = scrapedDrivers.stream()
                .map(scraped -> {
                    DriverStats existingDriver = existingDrivers.stream()
                            .filter(driver -> driver.getDriver().equalsIgnoreCase(scraped.driver()))
                            .findFirst()
                            .orElse(null);
                    return driverMapper.toEntity(scraped, existingDriver != null ? existingDriver.getId() : null);
                })
                .toList();
        driverStatsRepository.saveAll(updatedDrivers);
        return getDrivers();
    }
}
