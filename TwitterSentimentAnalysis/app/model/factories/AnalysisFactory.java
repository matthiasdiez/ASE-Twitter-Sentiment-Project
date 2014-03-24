package model.factories;

import model.core.Analysis;
import model.core.Customer;

public interface AnalysisFactory {

  public Analysis create(final Customer owner);

}
