package http;

import com.bryano.f1_stats.driver.ScrapedDriver;
import com.bryano.f1_stats.team.ScrapedTeam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface DataScraperHttpClient {
    @GetExchange("/api/drivers/current")
    List<ScrapedDriver> getDrivers();

    @GetExchange("/api/teams/current")
    List<ScrapedTeam> getTeams();
}
