package com.fantasy.football.englishpremierleague.persistence;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;

public interface LeagueDetailsPersistenceService {
    boolean saveAll(LeagueSummary leagueSummary);

    boolean clearAll();
}
