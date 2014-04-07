package model.factories.impl;

import model.core.Term;
import model.core.Tweet;
import model.factories.TweetFactory;

import org.joda.time.DateTime;

public class TweetFactoryImpl implements TweetFactory {

	@Override
	public Tweet create(Term term, String tweet, DateTime dateTime) {
		return new Tweet(term, tweet, dateTime);
	}
}
