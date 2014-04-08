package controllers;

import model.core.Term;
import model.repositories.TermRepository;
import play.mvc.Controller;
import play.mvc.Result;
import service.twitter.TwitterFetcherThread;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Application extends Controller {

  public static Result index() {
    return ok("Worker is running");
  }

  public static Result run(final Long termId) {
    final Twitter twitter = getTwitter();
    final Term term = TermRepository.INSTANCE.one(termId);

    final Runnable r = new TwitterFetcherThread(term, twitter);
    new Thread(r).start();

    return ok();
  }

  public static Twitter getTwitter() {
    final ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setOAuthConsumerKey("RtLzypNW6fGaJ4p9gtWw");
    cb.setOAuthConsumerSecret("yDIiCx1uceaMC0x7NNoCL9EIN638TvuW65ePer4ZAA");
    cb.setOAuthAccessToken("493939749-QDv7oyAcZvtsvxKYWCTtaqbSUXL2VqGteYAYTtVQ");
    cb.setOAuthAccessTokenSecret("1Idg8OkSLGtiLixv0zLnsa9G2lZ4LKO9PNFWeSuQLhOWV");
    final TwitterFactory twitterFactory = new TwitterFactory(cb.build());
    return twitterFactory.getInstance();
  }

}
