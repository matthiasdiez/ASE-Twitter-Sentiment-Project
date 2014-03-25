package model.repositories.impl;

import model.core.Analysis;
import model.repositories.AnalysisRepository;
import play.db.ebean.Model.Finder;

public class AnalysisRepositoryImpl extends AbstractBaseRepositoryImpl<Analysis> implements AnalysisRepository {

  private final Finder<Long, Analysis> finder = new Finder<Long, Analysis>(Long.class, Analysis.class);

  @Override
  protected Finder<Long, Analysis> finder() {
    return finder;
  }

}
