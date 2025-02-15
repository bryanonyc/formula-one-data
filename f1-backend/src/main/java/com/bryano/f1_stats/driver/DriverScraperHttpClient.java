package com.bryano.f1_stats.driver;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface DriverScraperHttpClient {
    @GetExchange("/api/drivers/current")
    List<ScrapedDriver> getAll();
}
