package model.repositories;

import model.core.AnalysisExecution;
import model.repositories.impl.AnalysisExecutionRepositoryImpl;

public interface AnalysisExecutionRepository extends BaseRepository<AnalysisExecution> {

  public static final AnalysisExecutionRepository INSTANCE = new AnalysisExecutionRepositoryImpl();

}
