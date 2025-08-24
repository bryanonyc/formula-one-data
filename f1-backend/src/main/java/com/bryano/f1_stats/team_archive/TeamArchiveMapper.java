package com.bryano.f1_stats.team_archive;

import org.springframework.stereotype.Component;

@Component
public class TeamArchiveMapper {
    public TeamArchive toEntity(ScrapedTeamArchive record) {
        return new TeamArchive(
                null,
                record.year(),
                record.position(),
                record.team(),
                Float.valueOf(record.points())
        );
    }
}
