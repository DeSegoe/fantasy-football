package com.fantasy.football.englishpremierleague.utils;

import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;

public interface ImageDownloader {
    void downloadAndSaveImages(LeagueSummary leagueSummary);
}
