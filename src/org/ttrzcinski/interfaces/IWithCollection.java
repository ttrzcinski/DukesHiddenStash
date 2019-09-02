package org.ttrzcinski.interfaces;

/**
 * Adds methods to make it easier to work with many common collections.
 *
 * @param <T> passed type of collection
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public interface IWithCollection<T> {

  /**
   * Checks, if passed key exists within collection.
   *
   * @param key given key
   * @return true means, key was found, false otherwise
   */
  boolean contains(String key);

  /**
   * Removes all known entries.
   */
  void clear();

  /**
   * Return entry with key, if key exists.
   *
   * @param key passed key
   * @return entry with passed key, if found, null otherwise
   */
  T get(String key);
}
