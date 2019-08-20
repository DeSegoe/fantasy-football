package com.fantasy.football.englishpremierleague.persistence;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.factories.clients.model.PlayerSummary;

public interface LeagueDetailsPersistenceService {
    boolean saveAll(LeagueSummary leagueSummary);
    PlayerSummary[] getAll();
    boolean clearAll();
}
