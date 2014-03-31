package model.repositories;

import model.core.Result;
import model.repositories.impl.ResultRepositoryImpl;

public interface ResultRepository extends BaseRepository<Result> {

  public static ResultRepository INSTANCE = new ResultRepositoryImpl();

}
