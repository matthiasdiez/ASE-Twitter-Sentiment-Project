package model.repositories;

import model.core.Analysis;
import model.repositories.impl.AnalysisRepositoryImpl;

public interface AnalysisRepository extends BaseRepository<Analysis> {

  public static final AnalysisRepository INSTANCE = new AnalysisRepositoryImpl();

}
