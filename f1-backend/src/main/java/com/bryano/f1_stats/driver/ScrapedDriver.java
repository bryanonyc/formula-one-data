package com.bryano.f1_stats.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapedDriver(
        @JsonProperty("driver") String driver,
        @JsonProperty("team") String team,
        @JsonProperty("country") String country,
        @JsonProperty("podiums") Integer podiums,
        @JsonProperty("points") String points,
        @JsonProperty("grands_prix_entered") Integer grandsPrixEntered,
        @JsonProperty("world_championships") Integer worldChampionships,
        @JsonProperty("highest_race_finish") String highestRaceFinish,
        @JsonProperty("highest_grid_position") String highestGridPosition,
        @JsonProperty("date_of_birth") String dateOfBirth,
        @JsonProperty("place_of_birth") String placeOfBirth
) {
}
