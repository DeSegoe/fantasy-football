package com.fantasy.football.englishpremierleague.datasource;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;

public interface FantasyFootballDataSource {
    LeagueSummary retrieveLeagueDetails();
}
