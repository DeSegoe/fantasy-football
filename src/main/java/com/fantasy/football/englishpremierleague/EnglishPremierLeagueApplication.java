package com.fantasy.football.englishpremierleague;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.factories.persistence.LeagueDetailsPersistenceService;

import com.fantasy.football.englishpremierleague.utils.notifications.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class EnglishPremierLeagueApplication {

    @Autowired
    FantasyFootballDataSource fantasyFootballDataSource;

    @Autowired
    private Notifier notifier;

    @Autowired
    private LeagueDetailsPersistenceService leagueDetailsPersistenceService;

    @Scheduled(fixedDelay = 1000*60*60*24L)
    private void downloadAndPersistData() {
        try {
            LeagueSummary leagueSummary = fantasyFootballDataSource.retrieveLeagueDetails();
            leagueDetailsPersistenceService.saveAll(leagueSummary);
            notifier.sendNotification(
                    String.format("Notification time %s",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                    "Successfully retrieved fantasy football data");
        }
        catch(Exception e) {
            e.printStackTrace();
            notifier.sendNotification(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EnglishPremierLeagueApplication.class, args);
    }
}
