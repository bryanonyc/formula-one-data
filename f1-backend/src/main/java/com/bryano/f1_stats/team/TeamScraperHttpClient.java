package com.bryano.f1_stats.team;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface TeamScraperHttpClient {
    @GetExchange("/api/teams/current")
    List<ScrapedTeam> getAll();
}