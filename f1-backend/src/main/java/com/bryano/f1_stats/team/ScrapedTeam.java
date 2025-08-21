package com.bryano.f1_stats.team;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapedTeam(
        @JsonProperty("base") String base,
        @JsonProperty("chassis") String chassis,
        @JsonProperty("fastest_laps") Integer fastestLaps,
        @JsonProperty("first_team_entry") Integer firstTeamEntry,
        @JsonProperty("full_team_name") String fullTeamName,
        @JsonProperty("highest_race_finish") String highestRaceFinish,
        @JsonProperty("pole_positions") Integer polePositions,
        @JsonProperty("power_unit") String powerUnit,
        @JsonProperty("team_chief") String teamChief,
        @JsonProperty("technical_chief") String technicalChief,
        @JsonProperty("world_championships") Integer worldChampionships
) {
}
