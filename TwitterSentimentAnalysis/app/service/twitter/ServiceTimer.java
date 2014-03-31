package service.twitter;

import java.util.Timer;

public class ServiceTimer {

	public static void startTimerTasks() {
		Timer twitterFetcher = new Timer();
		twitterFetcher.schedule(new TwitterFetcherTask(), 0, 30 * 1000); // schedules TwitterFetcher Task to run every 30 seconds

		// same same for sentiment analysis task
	}
}