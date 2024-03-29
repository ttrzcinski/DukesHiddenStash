package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
      private boolean closed = false;

      @Override
      public void close() {
        this.closed = true;
      }

      public boolean isClosed() {
        return this.closed;
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
    Exception shouldNotHappen = null;
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
      // Assert
    } catch (Exception e_1) {
      shouldNotHappen = e_1;
    } finally {
      assertNotNull(shouldNotHappen);
    }
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

    Exception thatMustHappen = null;

    String testErrMsg = "IOException";
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(errContent));
    try {
      // Act
      SafeClose.close(testObject);
    } catch (Exception e_1) {
      thatMustHappen = e_1;
    } finally {
      // Assert
      assertNotNull(thatMustHappen);
    }
    assertTrue(errContent.toString().contains(testErrMsg));
    System.setErr(originalErr);
  }

  @Test
  void close_withNull() {
    // Arrange
    boolean closed;
    try {
      // Act
      SafeClose.close(null);
      closed = true;
    } catch (Exception e_1) {
      closed = false;
    }
    // Assert
    assertTrue(closed);
  }
}
