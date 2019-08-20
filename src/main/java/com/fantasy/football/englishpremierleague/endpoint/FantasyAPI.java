package com.fantasy.football.englishpremierleague.endpoint;

import com.fantasy.football.englishpremierleague.factories.clients.model.PlayerSummary;
import com.fantasy.football.englishpremierleague.persistence.LeagueDetailsPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FantasyAPI {

    @Autowired
    private LeagueDetailsPersistenceService leagueDetailsPersistenceService;

    @GetMapping("fantasy-football/players")
    public PlayerSummary[] getLatestPlayerDetails() {
        return leagueDetailsPersistenceService.getAll();
    }
}
