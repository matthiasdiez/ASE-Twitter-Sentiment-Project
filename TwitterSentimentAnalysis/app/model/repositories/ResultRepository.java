package model.repositories;

import model.core.SentimentResult;
import model.repositories.impl.ResultRepositoryImpl;

public interface ResultRepository extends BaseRepository<SentimentResult> {

  public static ResultRepository INSTANCE = new ResultRepositoryImpl();

}
