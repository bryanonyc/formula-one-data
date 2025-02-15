package com.bryano.f1_stats.team;

import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    TeamStats toEntity(ScrapedTeam record) {
        return new TeamStats(
                record.fullTeamName(),
                record.base(),
                record.teamChief(),
                record.chassis(),
                record.powerUnit(),
                record.firstTeamEntry(),
                record.worldChampionships(),
                record.highestRaceFinish(),
                record.polePositions(),
                record.fastestLaps()
        );
    }
}
