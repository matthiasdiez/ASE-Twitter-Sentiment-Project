package model.factories.impl;

import model.core.Term;
import model.factories.TermFactory;

public class TermFactoryImpl implements TermFactory {

  @Override
  public Term create(final String content) {
    return new Term(content);
  }

}
