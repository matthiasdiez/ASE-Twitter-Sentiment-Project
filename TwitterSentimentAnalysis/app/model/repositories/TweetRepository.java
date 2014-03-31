package model.repositories;

import model.repositories.impl.TweetRepositoryImpl;
import twitter4j.Status;

public interface TweetRepository extends BaseRepository<Status> {
	
	  public static final TweetRepository INSTANCE = new TweetRepositoryImpl();
	
}
