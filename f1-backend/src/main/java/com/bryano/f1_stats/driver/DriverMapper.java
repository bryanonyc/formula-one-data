package com.bryano.f1_stats.driver;

import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    DriverStats toEntity(ScrapedDriver record) {
        return new DriverStats(
                record.driver(),
                record.team(),
                record.country(),
                record.podiums(),
                Float.valueOf(record.points()),
                record.grandsPrixEntered(),
                record.worldChampionships(),
                record.highestRaceFinish(),
                record.highestGridPosition(),
                record.dateOfBirth(),
                record.placeOfBirth()
        );
    }
}
