package model.factories;

import model.core.Analysis;
import model.core.Term;

public interface TermFactory {

  public Term create(final Analysis analysis, final String content);

}
