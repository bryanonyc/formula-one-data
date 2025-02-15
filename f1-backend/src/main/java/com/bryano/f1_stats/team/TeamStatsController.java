package com.bryano.f1_stats.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/team")
public class TeamStatsController {
    private final TeamStatsService teamStatsService;

    @Autowired
    public TeamStatsController(TeamStatsService teamStatsService) {
        this.teamStatsService = teamStatsService;
    }

    @GetMapping
    public List<TeamStats> getTeams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String base,
            @RequestParam(required = false) String chief
    ) {
        if (name != null) {
            return teamStatsService.getTeamsByName(name);
        } else if (base != null) {
            return teamStatsService.getTeamsByBase(base);
        } else if (chief != null) {
            return teamStatsService.getTeamsByChief(chief);
        } else {
            return teamStatsService.getTeams();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/current")
    public List<TeamStats> getTeamUpdated() throws IOException, InterruptedException {
        return teamStatsService.getUpdatedTeams();
    }
}
