package model.repositories;

import java.util.List;

import model.core.Term;
import model.core.Tweet;
import model.repositories.impl.TweetRepositoryImpl;

public interface TweetRepository extends BaseRepository<Tweet> {

	public static TweetRepository INSTANCE = new TweetRepositoryImpl();

	public List<Tweet> all(Term term);

}
