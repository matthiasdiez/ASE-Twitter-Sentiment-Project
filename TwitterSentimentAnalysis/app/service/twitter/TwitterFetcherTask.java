package service.twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import model.core.Analysis;
import model.core.Term;
import model.repositories.AnalysisRepository;
import model.repositories.impl.AnalysisRepositoryImpl;

import org.joda.time.DateTime;

import service.sentiment.SentimentClassifier;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFetcherTask extends TimerTask {
	private static Twitter twitter;

	public TwitterFetcherTask() {
		final ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("RtLzypNW6fGaJ4p9gtWw");
		cb.setOAuthConsumerSecret("yDIiCx1uceaMC0x7NNoCL9EIN638TvuW65ePer4ZAA");
		cb.setOAuthAccessToken("493939749-QDv7oyAcZvtsvxKYWCTtaqbSUXL2VqGteYAYTtVQ");
		cb.setOAuthAccessTokenSecret("1Idg8OkSLGtiLixv0zLnsa9G2lZ4LKO9PNFWeSuQLhOWV");
		final TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance();
	}

	@Override
	public void run() {
		final Set<Term> activeTerms = new HashSet<Term>();
		AnalysisRepository repo = new AnalysisRepositoryImpl();
		List<Analysis> analyses = repo.all();
		for (final Analysis analysis : analyses) {
			if (analysis.isActive()) {
				activeTerms.addAll(analysis.getTerms());
			}
		}
		try {
			for (final Term term : activeTerms) {
				final Query query = new Query(term.getContent());
				query.setCount(100); // 100 max number of tweets that can be returned by
										// one query
				final QueryResult result = twitter.search(query);
				final DateTime dateTime = DateTime.now();
				final ArrayList<String> tweets = new ArrayList<String>();
				for (final Status tweet : result.getTweets()) {
					tweets.add(tweet.getText());
				}
				SentimentClassifier sentimentClassifier = new SentimentClassifier();
				double score = sentimentClassifier.getSentimentScore(tweets);
				term.addResult(score, dateTime);
			}
		} catch (final TwitterException e) {
			e.printStackTrace();
		}
		Thread.yield();
	}
}
