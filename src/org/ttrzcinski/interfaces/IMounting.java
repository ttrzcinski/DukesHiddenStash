package org.ttrzcinski.interfaces;

/**
 * Interface for accessing collection with mount(upsert) and unmount(remove).
 *
 * @param <T> passed object as value type
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public interface IMounting<T> {

  /**
   * Mounts (inserts or updates) given value in collection.
   *
   * @param key passed key
   * @param value value of mounted object
   */
  void mount(String key, T value);

  /**
   * Removes value with given key.
   *
   * @param key passed key
   */
  void unmount(String key);
}
