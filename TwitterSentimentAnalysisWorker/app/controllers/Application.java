package controllers;

import java.util.ArrayList;
import java.util.List;

import model.core.Term;
import model.core.Tweet;
import model.repositories.TermRepository;
import model.repositories.TweetRepository;

import org.joda.time.DateTime;

import play.mvc.Controller;
import play.mvc.Result;
import service.sentiment.SentimentClassifier;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Application extends Controller {

  public static Result index() {
    return ok("Worker is running");
  }

  public static Result run(final Long termId) {
    final Twitter twitter = getTwitter();

    final Term term = TermRepository.INSTANCE.one(termId);

    final Query query = new Query(term.getContent());
    query.setCount(100); // 100 max number of tweets that can be returned by one
                         // query
    QueryResult result;
    final SentimentClassifier sentimentClassifier = new SentimentClassifier();
    try {
      result = twitter.search(query);
      final DateTime dateTime = DateTime.now();
      final ArrayList<String> tweets = new ArrayList<String>();
      for (final twitter4j.Status tweet : result.getTweets()) {
        tweets.add(tweet.getText());
        term.addTweet(tweet.getText(), new DateTime(tweet.getCreatedAt()));
      }
      final double score = sentimentClassifier.getSentimentScore(tweets);
      term.addResult(score, dateTime);
    }
    catch (final TwitterException e) {
      e.printStackTrace();
    }

    final List<Tweet> tweets = TweetRepository.INSTANCE.all(term);
    final ArrayList<String> allTweets = new ArrayList<String>();
    for (final Tweet tweet : tweets) {
      allTweets.add(tweet.getContent());
    }
    term.setOverallResult(sentimentClassifier.getSentimentScore(allTweets));

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
