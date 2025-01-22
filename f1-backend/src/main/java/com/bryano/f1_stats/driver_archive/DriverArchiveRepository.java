package com.bryano.f1_stats.driver_archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverArchiveRepository extends JpaRepository<DriverArchive, DriverArchiveKey> {
}
