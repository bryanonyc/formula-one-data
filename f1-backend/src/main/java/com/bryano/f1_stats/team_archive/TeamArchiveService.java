package com.bryano.f1_stats.team_archive;

import com.bryano.f1_stats.pojo.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamArchiveService {
    private final TeamArchiveRepository teamArchiveRepository;

    @Autowired
    public TeamArchiveService(TeamArchiveRepository teamArchiveRepository) {
        this.teamArchiveRepository = teamArchiveRepository;
    }

    public List<TeamArchive> getTeams() {
        return teamArchiveRepository.findAll();
    }

    public List<TeamArchive> getTeamsByYear(Integer year) {
        return teamArchiveRepository
                .findAll()
                .stream()
                .filter(teamArchive -> Objects.equals(teamArchive.getYear(), year))
                .collect(Collectors.toList());
    }

    public List<TeamArchive> getTeamsByTeam(String team) {
        return teamArchiveRepository
                .findAll()
                .stream()
                .filter(teamArchive -> teamArchive.getTeam().equalsIgnoreCase(team))
                .collect(Collectors.toList());
    }

    public List<SelectOption> getTeamNames() {
        return teamArchiveRepository
                .findAll()
                .stream()
                .map(TeamArchive::getTeam)
                .collect(Collectors.toSet())
                .stream()
                .sorted(String::compareTo)
                .map(name -> new SelectOption(name, name))
                .collect(Collectors.toList());
    }
}
