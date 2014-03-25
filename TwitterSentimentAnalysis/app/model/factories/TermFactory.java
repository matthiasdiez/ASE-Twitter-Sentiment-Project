package model.factories;

import model.core.Analysis;
import model.core.Term;
import model.factories.impl.TermFactoryImpl;

public interface TermFactory {

  public static final TermFactory INSTANCE = new TermFactoryImpl();

  public Term create(final Analysis analysis, final String content);

}
