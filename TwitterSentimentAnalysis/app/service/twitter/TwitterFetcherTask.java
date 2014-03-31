package service.twitter;

import java.util.ArrayList;
import java.util.TimerTask;

import model.core.Term;
import model.repositories.TermRepository;
import model.repositories.TweetRepository;
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
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("RtLzypNW6fGaJ4p9gtWw");
		cb.setOAuthConsumerSecret("yDIiCx1uceaMC0x7NNoCL9EIN638TvuW65ePer4ZAA");
		cb.setOAuthAccessToken("493939749-QDv7oyAcZvtsvxKYWCTtaqbSUXL2VqGteYAYTtVQ");
		cb.setOAuthAccessTokenSecret("1Idg8OkSLGtiLixv0zLnsa9G2lZ4LKO9PNFWeSuQLhOWV");
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance();
	}

	@Override
	public void run() {
		ArrayList<Term> terms = (ArrayList<Term>) TermRepository.INSTANCE.all();
		try {
			for (Term term : terms) {
				Query query = new Query(term.getContent());
				query.setCount(100); // 100 max number of tweets that can be returned by one query
				QueryResult result = twitter.search(query);
				for (Status tweet : result.getTweets()) {
					long createdAt = tweet.getCreatedAt().getTime();
					// check if tweet has already been added to the term
					if (term.getTweet(tweet.getId()) == null) {
						// only add tweet to analysis map if it has not been analyzed with the last analysis
						if (createdAt < term.getLatestSentimentAnalysis().getMillis()) {
							TweetRepository.INSTANCE.store(tweet);
							term.addStatus(tweet);
							term.save();
						}
					}
				}
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
