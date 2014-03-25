package model.repositories.impl;

import model.core.Term;
import model.repositories.TermRepository;
import play.db.ebean.Model.Finder;

public class TermRepositoryImpl extends AbstractBaseRepositoryImpl<Term> implements TermRepository {

  private final Finder<Long, Term> finder = new Finder<Long, Term>(Long.class, Term.class);

  @Override
  protected Finder<Long, Term> finder() {
    return finder;
  }

}
