package org.ttrzcinski.interfaces;

/**
 * Pattern for creating classes faster with control of it's number.
 *
 * @param <T> given type
 */
public interface IShop<T> {

  /**
   * Creates new instance overwriting current instance.
   *
   * @return new instance
   */
  T createNew();

  /**
   * Returns the handle to currently kept instance.
   *
   * @return current instance
   */
  T getCurrent();

  /**
   * Persists current instance with new unique id in repository.
   *
   * @return true, if done, false means errors while saving
   */
  boolean saveCurrent();

  /**
   * Loads the last known instance from repository.
   *
   * @return last known instance
   */
  T loadTheNewest();

  /**
   * Loads the instance with given name.
   *
   * @param uniqueName given name
   * @return instance, if exists, null otherwise
   */
  T loadFromRepo(String uniqueName);
}
