package com.bryano.f1_stats.team_archive;

import com.bryano.f1_stats.pojo.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/team/archive")
public class TeamArchiveController {
    private final TeamArchiveService teamArchiveService;

    @Autowired
    public TeamArchiveController(TeamArchiveService teamArchiveService) {
        this.teamArchiveService = teamArchiveService;
    }

    @GetMapping
    public List<TeamArchive> getTeams(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) Integer year
    ) {
        if (team != null) {
            return teamArchiveService.getTeamsByTeam(team);
        } else if (year != null) {
            return teamArchiveService.getTeamsByYear(year);
        } else {
            return teamArchiveService.getTeams();
        }
    }

    @GetMapping(path = "/names")
    public List<SelectOption> getTeamNames() {
        return teamArchiveService.getTeamNames();
    }
}
