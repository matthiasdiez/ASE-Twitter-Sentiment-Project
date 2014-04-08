package model.repositories.impl;

import model.core.SentimentResult;
import model.repositories.ResultRepository;
import play.db.ebean.Model.Finder;

public class ResultRepositoryImpl extends AbstractBaseRepositoryImpl<SentimentResult> implements ResultRepository {

  private final Finder<Long, SentimentResult> finder = new Finder<Long, SentimentResult>(Long.class, SentimentResult.class);

  @Override
  protected Finder<Long, SentimentResult> finder() {
    return finder;
  }

}
