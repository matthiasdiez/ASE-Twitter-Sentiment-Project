package service.twitter;

import java.util.Timer;

public class ServiceTimer {

  public void startTimerTask() {
    final Timer twitterFetcher = new Timer();
    // schedules TwitterFetcher Task to run every 30 seconds
    twitterFetcher.schedule(new TwitterFetcherTask(), 0, 60 * 1000);
  }
}