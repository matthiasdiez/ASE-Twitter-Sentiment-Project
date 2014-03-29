package model.repositories.impl;

import play.db.ebean.Model.Finder;
import model.core.Result;
import model.repositories.TweetRepository;
import twitter4j.Status;

public class TweetRepositoryImpl extends AbstractBaseRepositoryImpl<Status> implements TweetRepository {

	private final Finder<Long, Status> finder = new Finder<Long, Status>(Long.class, Status.class);

	@Override
	protected Finder<Long, Status> finder() {
		return finder;
	}

}
