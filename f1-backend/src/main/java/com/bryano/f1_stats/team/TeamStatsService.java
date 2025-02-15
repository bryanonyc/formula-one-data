package com.bryano.f1_stats.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamStatsService {
    private final TeamStatsRepository teamStatsRepository;
    private final TeamScraperHttpClient teamScraperHttpClient;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamStatsService(TeamStatsRepository teamStatsRepository, TeamScraperHttpClient teamScraperHttpClient, TeamMapper teamMapper) {
        this.teamStatsRepository = teamStatsRepository;
        this.teamScraperHttpClient = teamScraperHttpClient;
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

    public List<ScrapedTeam> getUpdatedTeams() throws IOException, InterruptedException {
        List<ScrapedTeam> updatedTeams = teamScraperHttpClient.getAll();
        List<TeamStats> entities = updatedTeams.stream().map(teamMapper::toEntity).toList();
        teamStatsRepository.saveAll(entities);
        return updatedTeams;
    }
}
