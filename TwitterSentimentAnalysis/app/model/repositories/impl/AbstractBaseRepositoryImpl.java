package model.repositories.impl;

import java.util.List;

import model.repositories.BaseRepository;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;

public abstract class AbstractBaseRepositoryImpl<E> implements BaseRepository<E> {

  protected abstract Finder<Long, E> finder();

  /**
   * {@inheritDoc}
   */
  @Override
  public E one(final long id) {
    return finder().byId(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<E> all() {
    return finder().all();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void store(final E entity) {
    Ebean.save(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(final long id) {
    Ebean.delete(finder().ref(id));
  }

}
