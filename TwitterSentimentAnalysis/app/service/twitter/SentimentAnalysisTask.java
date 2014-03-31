package service.twitter;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import model.core.Term;
import model.repositories.TermRepository;
import twitter4j.Status;

public class SentimentAnalysisTask extends TimerTask {

	@Override
	public void run() {
		List<Term> terms = TermRepository.INSTANCE.all();
		for (Term term : terms) {
			Map<Long, Status> tweetsToAnalyze = term.getTweetsToAnalyze();
			
			// --> perform sentiment analysis on tweets in map
			// --> create Result and add to term
			
		}
	}
}
