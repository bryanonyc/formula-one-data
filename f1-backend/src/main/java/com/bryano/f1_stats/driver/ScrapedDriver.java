package com.bryano.f1_stats.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapedDriver(
        @JsonProperty("Driver") String driver,
        @JsonProperty("Team") String team,
        @JsonProperty("Country") String country,
        @JsonProperty("Podiums") Integer podiums,
        @JsonProperty("Points") String points,
        @JsonProperty("Grands Prix entered") Integer grandsPrixEntered,
        @JsonProperty("World Championships") Integer worldChampionships,
        @JsonProperty("Highest race finish") String highestRaceFinish,
        @JsonProperty("Highest grid position") Integer highestGridPosition,
        @JsonProperty("Date of birth") String dateOfBirth,
        @JsonProperty("Place of birth") String placeOfBirth
) {
}
