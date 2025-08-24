package com.bryano.f1_stats.setup;

import com.bryano.f1_stats.driver.DriverMapper;
import com.bryano.f1_stats.driver.DriverStats;
import com.bryano.f1_stats.driver.DriverStatsRepository;
import com.bryano.f1_stats.driver_archive.DriverArchive;
import com.bryano.f1_stats.driver_archive.DriverArchiveMapper;
import com.bryano.f1_stats.driver_archive.DriverArchiveRepository;
import com.bryano.f1_stats.http.DataScraperHttpClient;
import com.bryano.f1_stats.team.TeamMapper;
import com.bryano.f1_stats.team.TeamStats;
import com.bryano.f1_stats.team.TeamStatsRepository;
import com.bryano.f1_stats.team_archive.TeamArchive;
import com.bryano.f1_stats.team_archive.TeamArchiveMapper;
import com.bryano.f1_stats.team_archive.TeamArchiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    private final DataScraperHttpClient dataScraperHttpClient;
    private final DriverStatsRepository driverStatsRepository;
    private final DriverArchiveRepository driverArchiveRepository;
    private final TeamStatsRepository teamStatsRepository;
    private final TeamArchiveRepository teamArchiveRepository;
    private final DriverMapper driverMapper;
    private final DriverArchiveMapper driverArchiveMapper;
    private final TeamMapper teamMapper;
    private final TeamArchiveMapper teamArchiveMapper;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    InitialDataLoader(
            DataScraperHttpClient dataScraperHttpClient,
            DriverStatsRepository driverStatsRepository,
            DriverArchiveRepository driverArchiveRepository,
            TeamStatsRepository teamStatsRepository,
            TeamArchiveRepository teamArchiveRepository,
            DriverMapper driverMapper,
            DriverArchiveMapper driverArchiveMapper,
            TeamMapper teamMapper,
            TeamArchiveMapper teamArchiveMapper
    ) {
        this.dataScraperHttpClient = dataScraperHttpClient;
        this.driverStatsRepository = driverStatsRepository;
        this.driverArchiveRepository = driverArchiveRepository;
        this.teamStatsRepository = teamStatsRepository;
        this.teamArchiveRepository = teamArchiveRepository;
        this.driverMapper = driverMapper;
        this.driverArchiveMapper = driverArchiveMapper;
        this.teamMapper = teamMapper;
        this.teamArchiveMapper = teamArchiveMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking {} for initial data.", databaseUrl);

        loadDriversIfNotExists();

        loadDriverArchiveIfNotExists();

        loadTeamsIfNotExists();

        loadTeamArchiveIfNotExists();

        log.info("Initial data load completed. Application is ready.");
    }

    private void loadDriversIfNotExists() {
        if (driverStatsRepository.count() == 0L) {
            log.info("No driver data found. Loading...");
            List<DriverStats> saveList = dataScraperHttpClient
                    .scrapeDrivers()
                    .stream()
                    .map(r -> driverMapper.toEntity(r, null))
                    .toList();
            driverStatsRepository.saveAll(saveList);
            log.info("Inserted {} records into driver_stats table", saveList.size());
        } else {
            log.info("Driver data found.");
        }
    }

    private void loadDriverArchiveIfNotExists() {
        if (driverArchiveRepository.count() == 0L) {
            log.info("No driver archive data found. Loading...");
            List<DriverArchive> saveList = dataScraperHttpClient
                    .scrapeDriverArchive()
                    .stream()
                    .map(driverArchiveMapper::toEntity)
                    .toList();
            driverArchiveRepository.saveAll(saveList);
            log.info("Inserted {} records into driver_archive table", saveList.size());
        } else {
            log.info("Driver archive data found.");
        }
    }

    private void loadTeamsIfNotExists() {
        if (teamStatsRepository.count() == 0L) {
            log.info("No team data found. Loading...");
            List<TeamStats> saveList = dataScraperHttpClient
                    .scrapeTeams()
                    .stream()
                    .map(r -> teamMapper.toEntity(r, null))
                    .toList();
            teamStatsRepository.saveAll(saveList);
            log.info("Inserted {} records into team_stats table", saveList.size());
        } else {
            log.info("Team data found.");
        }
    }

    private void loadTeamArchiveIfNotExists() {
        if (teamArchiveRepository.count() == 0L) {
            log.info("No team archive data found. Loading...");
            List<TeamArchive> saveList = dataScraperHttpClient
                    .scrapeTeamArchive()
                    .stream()
                    .map(teamArchiveMapper::toEntity)
                    .toList();
            teamArchiveRepository.saveAll(saveList);
            log.info("Inserted {} records into team_archive table", saveList.size());
        } else {
            log.info("Team archive data found.");
        }
    }
}
