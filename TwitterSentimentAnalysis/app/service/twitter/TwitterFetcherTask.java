package service.twitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import play.libs.WS;
import model.core.Analysis;
import model.core.Term;
import model.repositories.AnalysisRepository;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFetcherTask extends TimerTask {

	@Override
	public void run() {
		final Set<Term> activeTerms = new HashSet<Term>();
		final List<Analysis> analyses = AnalysisRepository.INSTANCE.all();
		for (final Analysis analysis : analyses) {
			if (analysis.isActive()) {
				activeTerms.addAll(analysis.getTerms());
			}
		}
		for (final Term term : activeTerms) {
			WS.url("http://awseb-e-d-awsebloa-sa5puxjlfpjr-1662706129.eu-west-1.elb.amazonaws.com/run/" + term.getId()).get();
		}
		Thread.yield();
	}
}
