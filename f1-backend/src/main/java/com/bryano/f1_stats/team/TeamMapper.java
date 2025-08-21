package com.bryano.f1_stats.team;

import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    TeamStats toEntity(ScrapedTeam record, Integer id) {
        return new TeamStats(
                id,
                record.fullTeamName(),
                record.base(),
                record.teamChief(),
                record.technicalChief(),
                record.chassis(),
                record.powerUnit(),
                record.firstTeamEntry(),
                record.worldChampionships(),
                record.highestRaceFinish(),
                record.polePositions()
        );
    }
}
