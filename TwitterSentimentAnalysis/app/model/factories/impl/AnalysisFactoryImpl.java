package model.factories.impl;

import model.core.Analysis;
import model.core.Customer;
import model.factories.AnalysisFactory;

public class AnalysisFactoryImpl implements AnalysisFactory {

  @Override
  public Analysis create(final Customer owner) {
    return new Analysis(owner);
  }

}
