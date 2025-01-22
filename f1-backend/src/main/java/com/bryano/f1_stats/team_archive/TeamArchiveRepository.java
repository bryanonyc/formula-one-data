package com.bryano.f1_stats.team_archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamArchiveRepository extends JpaRepository<TeamArchive, TeamArchiveKey> {
}
