package model.factories;

import model.core.Analysis;
import model.core.AnalysisExecution;
import model.factories.impl.AnalysisExecutionFactoryImpl;

public interface AnalysisExecutionFactory {

  public static final AnalysisExecutionFactory INSTANCE = new AnalysisExecutionFactoryImpl();

  public AnalysisExecution create(Analysis analysis);

}
