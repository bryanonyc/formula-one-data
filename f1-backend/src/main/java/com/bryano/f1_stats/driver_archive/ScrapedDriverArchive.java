package com.bryano.f1_stats.driver_archive;

public record ScrapedDriverArchive(
        Integer year,
        String position,
        String driver,
        String nationality,
        String car,
        String points
) {
}
