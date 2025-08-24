package com.bryano.f1_stats.team;

import com.bryano.f1_stats.http.DataScraperHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamStatsService {
    private final TeamStatsRepository teamStatsRepository;
    private final DataScraperHttpClient dataScraperHttpClient;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamStatsService(TeamStatsRepository teamStatsRepository, DataScraperHttpClient dataScraperHttpClient, TeamMapper teamMapper) {
        this.teamStatsRepository = teamStatsRepository;
        this.dataScraperHttpClient = dataScraperHttpClient;
        this.teamMapper = teamMapper;
    }

    public List<TeamStats> getTeams() {
        return teamStatsRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(TeamStats::getFullTeamName))
                .toList();
    }

    public List<TeamStats> getTeamsByName(String name) {
        return teamStatsRepository
                .findAll()
                .stream()
                .filter(teamStats -> teamStats.getFullTeamName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<TeamStats> getTeamsByBase(String base) {
        return teamStatsRepository
                .findAll()
                .stream()
                .filter(teamStats -> teamStats.getBase().toLowerCase().contains(base.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<TeamStats> getTeamsByChief(String chief) {
        return teamStatsRepository
                .findAll()
                .stream()
                .filter(teamStats -> teamStats.getTeamChief().toLowerCase().contains(chief.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<TeamStats> getUpdatedTeams() throws IOException, InterruptedException {
        List<ScrapedTeam> scrapedTeams = dataScraperHttpClient.scrapeTeams();
        List<TeamStats> existingTeams = teamStatsRepository.findAll();
        List<TeamStats> updatedTeams = scrapedTeams.stream()
                .map(scraped -> {
                    TeamStats existingTeam = existingTeams.stream()
                            .filter(team -> team.getFullTeamName().equalsIgnoreCase(scraped.fullTeamName()))
                            .findFirst()
                            .orElse(null);
                    return teamMapper.toEntity(scraped, existingTeam != null ? existingTeam.getId() : null);
                })
                .toList();
        teamStatsRepository.saveAll(updatedTeams);
        return getTeams();
    }
}
