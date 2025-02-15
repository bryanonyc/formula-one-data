package com.bryano.f1_stats.team;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapedTeam(
        @JsonProperty("Base") String base,
        @JsonProperty("Chassis") String chassis,
        @JsonProperty("Fastest Laps") Integer fastestLaps,
        @JsonProperty("First Team Entry") Integer firstTeamEntry,
        @JsonProperty("Full Team Name") String fullTeamName,
        @JsonProperty("Highest Race Finish") String highestRaceFinish,
        @JsonProperty("Pole Positions") Integer polePositions,
        @JsonProperty("Power Unit") String powerUnit,
        @JsonProperty("Team Chief") String teamChief,
        @JsonProperty("Technical Chief") String technicalChief,
        @JsonProperty("World Championships") Integer worldChampionships
) {
}
