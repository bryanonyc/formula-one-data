package com.bryano.f1_stats.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamStatsService {
    private final TeamStatsRepository teamStatsRepository;

    @Autowired
    public TeamStatsService(TeamStatsRepository teamStatsRepository) {
        this.teamStatsRepository = teamStatsRepository;
    }

    public List<TeamStats> getTeams() {
        return teamStatsRepository.findAll();
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
}
