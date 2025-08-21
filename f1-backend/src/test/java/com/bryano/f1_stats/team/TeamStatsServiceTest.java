package com.bryano.f1_stats.team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamStatsServiceTest {
    @Mock
    private TeamStatsRepository teamStatsRepository;

    @InjectMocks
    private TeamStatsService teamStatsService;

    private List<TeamStats> mockTeamStats;

    @BeforeEach
    void setUp() {
        mockTeamStats = List.of(
                new TeamStats(null, "Team1", "", "", "", "", "", 0, 0, "", 0),
                new TeamStats(null, "Team2", "", "", "", "", "", 0, 0, "", 0)
        );
    }

    @Test
    void getTeams() {
        when(teamStatsRepository.findAll()).thenReturn(mockTeamStats);

        List<TeamStats> result = teamStatsService.getTeams();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team1", result.getFirst().getFullTeamName());
        assertEquals("Team2", result.get(1).getFullTeamName());

        // Verify the repository method was called
        verify(teamStatsRepository).findAll();
    }

    @Test
    void getTeamsByName_returns_exact_match() {
        when(teamStatsRepository.findAll()).thenReturn(mockTeamStats);

        List<TeamStats> result = teamStatsService.getTeamsByName("Team2");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Team2", result.getFirst().getFullTeamName());

        // Verify the repository method was called
        verify(teamStatsRepository).findAll();
    }

    @Test
    void getTeamsByName_returns_partial_match() {
        when(teamStatsRepository.findAll()).thenReturn(mockTeamStats);

        List<TeamStats> result = teamStatsService.getTeamsByName("team");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team1", result.getFirst().getFullTeamName());
        assertEquals("Team2", result.get(1).getFullTeamName());

        // Verify the repository method was called
        verify(teamStatsRepository).findAll();
    }

    @Test
    void getTeamsByName_returns_empty_list_on_no_match() {
        when(teamStatsRepository.findAll()).thenReturn(mockTeamStats);

        List<TeamStats> result = teamStatsService.getTeamsByName("FakeName");

        assertNotNull(result);
        assertEquals(0, result.size());

        // Verify the repository method was called
        verify(teamStatsRepository).findAll();
    }
}