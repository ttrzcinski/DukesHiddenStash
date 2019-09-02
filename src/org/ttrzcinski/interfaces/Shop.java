package org.ttrzcinski.interfaces;

/**
 * Pattern for creating classes faster with control of it's number.
 */
public interface Shop<T> {

  /**
   * Creates new instance overwriting current instance.
   *
   * @return new instance
   */
  public T createNew();

  /**
   * Returns the handle to currently kept instance.
   *
   * @return current instance
   */
  public T getCurrent();

  /**
   * Persists current instance with new unique id in repository.
   *
   * @return true, if done, false means errors while saving
   */
  public boolean saveCurrent();

  /**
   * Loads the last known instance from repository.
   *
   * @return last known instance
   */
  public T LoadTheNewest();

  /**
   * Loads the instance with given name.
   *
   * @param uniqueName given name
   * @return instance, if exists, null otherwise
   */
  public T loadFromRepo(String uniqueName);
}
