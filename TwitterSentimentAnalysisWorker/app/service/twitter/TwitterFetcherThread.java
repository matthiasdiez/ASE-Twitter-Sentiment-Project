package service.twitter;

import java.util.ArrayList;
import java.util.List;

import model.core.Term;
import model.core.Tweet;
import model.repositories.TweetRepository;

import org.joda.time.DateTime;

import service.sentiment.SentimentClassifier;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterFetcherThread implements Runnable {

	Term term;
	private Twitter twitter;

	public TwitterFetcherThread(Term term, Twitter twitter) {
		this.term = term;
		this.twitter = twitter;
	}

	@Override
	public void run() {
		final Query query = new Query(term.getContent());
		query.setCount(100); // 100 max number of tweets that can be returned by one query
		QueryResult result;
		SentimentClassifier sentimentClassifier = new SentimentClassifier();
		try {
			result = twitter.search(query);
			final DateTime dateTime = DateTime.now();
			final ArrayList<String> tweets = new ArrayList<String>();
			for (final Status tweet : result.getTweets()) {
				tweets.add(tweet.getText());
				term.addTweet(tweet.getText(), new DateTime(tweet.getCreatedAt()));
			}
			double score = sentimentClassifier.getSentimentScore(tweets);
			term.addResult(score, dateTime);
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		List<Tweet> tweets = TweetRepository.INSTANCE.all(term);
		ArrayList<String> allTweets = new ArrayList<String>();
		for (Tweet tweet : tweets) {
			allTweets.add(tweet.getContent());
		}
		term.setOverallResult(sentimentClassifier.getSentimentScore(allTweets));
	}
}
