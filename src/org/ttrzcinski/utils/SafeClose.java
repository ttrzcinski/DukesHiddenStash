package org.ttrzcinski.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Safely closes resource.
 */
public class SafeClose {

  /**
   * Closes any given resource.
   *
   * @param resource closeable resource
   */
  public static void close(Closeable resource) {
    if (resource != null) {
      try {
        resource.close();
      } catch (IOException ioex) {
        System.err.println(ioex);
      }
    }
  }
}
