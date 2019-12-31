package org.ttrzcinski.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Closeable;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Test methods of class org.ttrzcinski.utils.SafeClose.
 *
 * @see SafeClose
 */
class SafeCloseTest {

  @Test
  void close_withSomething() {
    // Arrange
    Closeable testObject = new Closeable() {
      boolean closed = false;

      @Override
      public void close() throws IOException {
        closed = true;
      }

      public boolean isClosed() {
        return closed;
      }
    };

    boolean closed;
    try {
      // Act
      SafeClose.close(testObject);
      closed = true;
    } catch (Exception e_1) {
      closed = false;
    }
    assertTrue(closed);
  }

  @Test
  void close_withNull() {
    // Arrange
    Closeable testObject = null;

    // Act
    SafeClose.close(testObject);

    // Assert
    boolean closed;
    try {
      // Act
      SafeClose.close(testObject);
      closed = true;
    } catch (Exception e_1) {
      closed = false;
    }
    assertTrue(closed);
  }
}
