package model.factories;

import model.core.Analysis;
import model.core.AnalysisExecution;

public interface AnalysisExecutionFactory {

  public AnalysisExecution create(Analysis analysis);

}
