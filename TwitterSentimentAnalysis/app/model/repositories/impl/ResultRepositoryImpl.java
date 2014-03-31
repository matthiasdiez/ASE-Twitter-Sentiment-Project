package model.repositories.impl;

import model.core.Result;
import model.repositories.ResultRepository;
import play.db.ebean.Model.Finder;

public class ResultRepositoryImpl extends AbstractBaseRepositoryImpl<Result> implements ResultRepository {

  private final Finder<Long, Result> finder = new Finder<Long, Result>(Long.class, Result.class);

  @Override
  protected Finder<Long, Result> finder() {
    return finder;
  }

}
