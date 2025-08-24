package com.bryano.f1_stats.driver_archive;

import org.springframework.stereotype.Component;

@Component
public class DriverArchiveMapper {
    public DriverArchive toEntity(ScrapedDriverArchive record) {
        return new DriverArchive(
                null,
                record.year(),
                record.position(),
                record.driver(),
                record.nationality(),
                record.car(),
                Float.valueOf(record.points())
        );
    }
}
