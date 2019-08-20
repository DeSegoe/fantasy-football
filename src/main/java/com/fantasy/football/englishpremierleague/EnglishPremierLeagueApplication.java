package com.fantasy.football.englishpremierleague;

import com.fantasy.football.englishpremierleague.datasource.FantasyFootballDataSource;
import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.notifications.Notifier;
import com.fantasy.football.englishpremierleague.persistence.LeagueDetailsPersistenceService;
import com.fantasy.football.englishpremierleague.utils.ImageDownloader;
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

    @Autowired
    private ImageDownloader imageDownloader;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24 * 7L)
    private void downloadImages() {
        try {
            LeagueSummary leagueSummary = fantasyFootballDataSource.retrieveLeagueDetails();
            imageDownloader.downloadAndSaveImages(leagueSummary);
            notifier.sendNotification(
                    String.format("Notification time %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                    "Successfully downloaded images");
        } catch (Exception e) {
            e.printStackTrace();
            notifier.sendNotification(e);
        }
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24L)
    private void downloadAndPersistData() {
        try {
            LeagueSummary leagueSummary = fantasyFootballDataSource.retrieveLeagueDetails();
            leagueDetailsPersistenceService.saveAll(leagueSummary);
            notifier.sendNotification(
                    String.format("Notification time %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                    "Successfully retrieved fantasy football data");
        } catch (Exception e) {
            e.printStackTrace();
            notifier.sendNotification(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EnglishPremierLeagueApplication.class, args);
    }
}
