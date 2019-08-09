package com.fantasy.football.englishpremierleague;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.factories.persistence.LeagueDetailsPersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class EnglishPremierLeagueApplication {

    @Autowired
    FantasyFootballDataSource fantasyFootballDataSource;

    @Autowired
    private LeagueDetailsPersistenceService leagueDetailsPersistenceService;

    @Scheduled(fixedDelay = 1000*60*5L)
    private void downloadAndPersistData() {
        try {
            LeagueSummary leagueSummary = fantasyFootballDataSource.retrieveLeagueDetails();
            leagueDetailsPersistenceService.clearAll();
            leagueDetailsPersistenceService.saveAll(leagueSummary);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EnglishPremierLeagueApplication.class, args);
    }
}
