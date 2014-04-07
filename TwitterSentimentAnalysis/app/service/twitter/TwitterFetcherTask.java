package service.twitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import model.core.Analysis;
import model.core.Term;
import model.repositories.AnalysisRepository;
import twitter4j.Twitter;
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
		List<Analysis> analyses = AnalysisRepository.INSTANCE.all();
		for (final Analysis analysis : analyses) {
			if (analysis.isActive()) {
				activeTerms.addAll(analysis.getTerms());
			}
		}
		for (final Term term : activeTerms) {
			Runnable r = new TwitterFetcherThread(term, twitter);
			new Thread(r).start();
		}
		Thread.yield();
	}
}
