package model.factories;

import model.core.Term;
import model.factories.impl.TermFactoryImpl;

public interface TermFactory {

  public static final TermFactory INSTANCE = new TermFactoryImpl();

  public Term create(final String content);

}
