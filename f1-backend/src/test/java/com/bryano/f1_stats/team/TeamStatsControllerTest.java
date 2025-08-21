package com.bryano.f1_stats.team;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamStatsController.class)
class TeamStatsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamStatsService teamStatsService;

    private List<TeamStats> mockResponseData;

    @BeforeEach
    void setUp() {
        mockResponseData = List.of(
                new TeamStats(null, "Team1", "", "", "", "", "", 0, 0, "", 0),
                new TeamStats(null, "Team2", "", "", "", "", "", 0, 0, "", 0)
        );
    }

    @Test
    void getTeamsWithNoParams() throws Exception {
        Mockito.when(teamStatsService.getTeams()).thenReturn(mockResponseData);

        MvcResult result = mockMvc.perform(get("/api/v1/team"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TeamStats> teamStats = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertNotNull(teamStats);
        assertEquals(2, teamStats.size());
        assertEquals("Team1", teamStats.getFirst().getFullTeamName());
        assertEquals("Team2", teamStats.get(1).getFullTeamName());
    }

    @Test
    void getTeamsWithNameParam() throws Exception {
        Mockito
                .when(teamStatsService.getTeamsByName("team2"))
                .thenReturn(List.of(mockResponseData.get(1)));

        MvcResult result = mockMvc
                .perform(get("/api/v1/team")
                        .param("name", "team2"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TeamStats> teamStats = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertNotNull(teamStats);
        assertEquals(1, teamStats.size());
        assertEquals("Team2", teamStats.getFirst().getFullTeamName());
    }
}