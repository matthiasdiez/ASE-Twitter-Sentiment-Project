package model.factories;

import model.core.Term;
import model.core.Tweet;
import model.factories.impl.TweetFactoryImpl;

import org.joda.time.DateTime;

public interface TweetFactory {

	public static final TweetFactory INSTANCE = new TweetFactoryImpl();

	public Tweet create(Term term, String tweet, DateTime dateTime);
}
