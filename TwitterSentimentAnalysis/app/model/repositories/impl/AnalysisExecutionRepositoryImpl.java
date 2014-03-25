package model.repositories.impl;

import model.core.AnalysisExecution;
import model.repositories.AnalysisExecutionRepository;
import play.db.ebean.Model.Finder;

public class AnalysisExecutionRepositoryImpl extends AbstractBaseRepositoryImpl<AnalysisExecution> implements AnalysisExecutionRepository {

  private final Finder<Long, AnalysisExecution> finder = new Finder<Long, AnalysisExecution>(Long.class, AnalysisExecution.class);

  @Override
  protected Finder<Long, AnalysisExecution> finder() {
    return finder;
  }

}
