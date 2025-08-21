package com.bryano.f1_stats.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverStatsRepository extends JpaRepository<DriverStats, Integer> {
}
