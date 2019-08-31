package org.ttrzcinski.utils;

import java.io.Closeable;
import java.io.IOException;

public class SafeClose {

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
