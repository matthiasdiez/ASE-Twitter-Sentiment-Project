package model.repositories;

import java.util.List;

public interface BaseRepository<E> {

  /**
   * Returns the item of type E with a given id.
   * 
   * @param id
   *          id of the item to be returned
   * @return item of type E with the given id or <code>null</code>, if no entity
   *         with the given id exists in the database
   */
  public E one(final long id);

  /**
   * Returns all items of type E in the database.
   * 
   * @return {@link List} of all items of type E in the database
   */
  public List<E> all();

  /**
   * Stores an entity of type E into the database.
   * 
   * @param entity
   *          entity to be stored
   */
  public void store(final E entity);

  /**
   * Deletes an entity of type E with the given id in the database.
   * 
   * @param id
   *          id of the entity to be deleted
   */
  public void delete(final long id);

}
