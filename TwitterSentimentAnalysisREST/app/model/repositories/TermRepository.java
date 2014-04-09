package model.repositories;

import model.core.Term;
import model.repositories.impl.TermRepositoryImpl;

public interface TermRepository extends BaseRepository<Term> {

  public static final TermRepository INSTANCE = new TermRepositoryImpl();

  public Term one(String content);

}
