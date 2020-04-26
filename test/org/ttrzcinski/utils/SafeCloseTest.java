package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.SafeClose;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
      // Assert
    assertTrue(closed);
  }

  @Test
  void close_withSomethingThrowing() {
    // Arrange
    Closeable testObject = new Closeable() {
      boolean closed = false;

      @Override
      public void close() throws IOException {
        closed = true;
        throw new IOException("This resource suppose to be closed properly.");
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
    // Assert
    assertTrue(closed);
  }

  @Test
  void close_withPrintOutThrow() {
    // Arrange
    Closeable testObject = new Closeable() {
      boolean closed = false;

      @Override
      public void close() throws IOException {
        closed = true;
        throw new IOException("This resource suppose to be closed properly.");
      }

      public boolean isClosed() {
        return closed;
      }
    };

    String testErrMsg = "Couldn't close passed resource.";
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(errContent));
    try {
      // Act
      SafeClose.close(testObject);
    } catch (Exception e_1) {; }
    // Assert
    assertTrue(errContent.toString().contains(testErrMsg));
    System.setErr(originalErr);
  }

  @Test
  void close_withExceptionStackThrow() {
    // Arrange
    Closeable testObject = new Closeable() {
      boolean closed = false;

      @Override
      public void close() throws IOException {
        closed = true;
        throw new IOException("This resource suppose to be closed properly.");
      }

      public boolean isClosed() {
        return closed;
      }
    };

    String testErrMsg = "IOException";
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(errContent));
    try {
      // Act
      SafeClose.close(testObject);
    } catch (Exception e_1) {;}
    // Assert
    assertTrue(errContent.toString().contains(testErrMsg));
    System.setErr(originalErr);
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
