package service.twitter;

import java.util.Timer;

public class ServiceTimer {

  public void startTimerTask() {
    final Timer twitterFetcher = new Timer();
    twitterFetcher.schedule(new TwitterFetcherTask(), 0, 60 * 1000);
  }
}