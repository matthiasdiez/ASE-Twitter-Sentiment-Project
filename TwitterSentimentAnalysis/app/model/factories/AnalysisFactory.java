package model.factories;

import model.core.Analysis;
import model.core.Customer;
import model.factories.impl.AnalysisFactoryImpl;

public interface AnalysisFactory {

  public static final AnalysisFactory INSTANCE = new AnalysisFactoryImpl();

  public Analysis create(final Customer owner, String name);

}
