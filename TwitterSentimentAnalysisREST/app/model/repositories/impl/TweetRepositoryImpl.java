package model.repositories.impl;

import java.util.List;

import model.core.Term;
import model.core.Tweet;
import model.repositories.TweetRepository;
import play.db.ebean.Model.Finder;

public class TweetRepositoryImpl extends AbstractBaseRepositoryImpl<Tweet> implements TweetRepository {

	private final Finder<Long, Tweet> finder = new Finder<Long, Tweet>(Long.class, Tweet.class);

	@Override
	protected Finder<Long, Tweet> finder() {
		return finder;
	}

	@Override
	public List<Tweet> all(final Term term) {
		return finder.where().eq("term", term).findList();
	}

}