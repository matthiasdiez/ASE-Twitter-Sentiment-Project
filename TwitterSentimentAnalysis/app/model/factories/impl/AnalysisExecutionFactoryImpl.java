package model.factories.impl;

import model.core.Analysis;
import model.core.AnalysisExecution;
import model.factories.AnalysisExecutionFactory;

public class AnalysisExecutionFactoryImpl implements AnalysisExecutionFactory {

  @Override
  public AnalysisExecution create(final Analysis analysis) {
    return new AnalysisExecution(analysis);
  }

}
