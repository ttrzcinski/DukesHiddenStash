package org.ttrzcinski.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Safely closes resource.
 */
public final class SafeClose {

  /**
   * Kept instance of class.
   */
  private static SafeClose instance;

  /**
   * Hidden constructor.
   */
  private SafeClose() {
  }

  /**
   * Returns the only instance of class.
   *
   * @return instance of class
   */
  public static SafeClose getInstance() {
    if (instance == null) {
      instance = new SafeClose();
    }
    return instance;
  }

  /**
   * Closes any given resource.
   *
   * @param resource closeable resource
   */
  public static void close(final Closeable resource) {
    // Check, if resource is null
    if (resource == null) {
      return;
    }
    // Try to close it
    try {
      resource.close();
    } catch (IOException ioe) {
      System.err.println("Couldn't close passed resource.");
      ioe.printStackTrace();
    }
  }
}
