package http;

import com.bryano.f1_stats.driver.ScrapedDriver;
import com.bryano.f1_stats.driver_archive.ScrapedDriverArchive;
import com.bryano.f1_stats.team.ScrapedTeam;
import com.bryano.f1_stats.team_archive.ScrapedTeamArchive;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface DataScraperHttpClient {
    @GetExchange("/api/drivers/current")
    List<ScrapedDriver> scrapeDrivers();

    @GetExchange("/api/drivers/archive")
    List<ScrapedDriverArchive> scrapeDriverArchive();

    @GetExchange("/api/teams/current")
    List<ScrapedTeam> scrapeTeams();

    @GetExchange("/api/teams/archive")
    List<ScrapedTeamArchive> scrapeTeamArchive();
}
