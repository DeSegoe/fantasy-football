package com.fantasy.football.englishpremierleague.factories.persistence;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceFactory {

    @Autowired
    private DynamoDB dynamoDB;

    @Bean
    public LeagueDetailsPersistenceService createDataStore() {
        return new LeagueDetailsPersistenceServiceDynamoDB(dynamoDB);
    }
}
