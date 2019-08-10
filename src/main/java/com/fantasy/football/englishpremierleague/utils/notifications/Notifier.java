package com.fantasy.football.englishpremierleague.utils.notifications;

public interface Notifier {
    void sendNotification(Throwable throwable);
    void sendNotification(String subject,String message);
}
