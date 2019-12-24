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
    if (resource == null) {
      return;
    }
    try {
      resource.close();
    } catch (IOException ioe_1) {
      System.err.println("Couldn't close passed resource.");
      ioe_1.printStackTrace();
    }
  }
}
