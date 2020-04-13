package org.ttrzcinski.utils;

import lombok.experimental.UtilityClass;

import java.io.Closeable;
import java.io.IOException;

/**
 * Safely closes resources.
 */
@UtilityClass
public final class SafeClose {

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
