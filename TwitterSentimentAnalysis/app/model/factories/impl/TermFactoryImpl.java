package model.factories.impl;

import model.core.Analysis;
import model.core.Term;
import model.factories.TermFactory;

public class TermFactoryImpl implements TermFactory {

  @Override
  public Term create(final Analysis analysis, final String content) {
    return new Term(analysis, content);
  }

}
