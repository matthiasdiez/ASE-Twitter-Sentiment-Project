package service.twitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import model.core.Analysis;
import model.core.Term;
import model.repositories.AnalysisRepository;
import play.Play;
import play.libs.WS;

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
      final String workerUrl = Play.application().configuration().getString("worker.url");
      WS.url(workerUrl + "/run/" + term.getId()).get();
    }
    Thread.yield();
  }
}
