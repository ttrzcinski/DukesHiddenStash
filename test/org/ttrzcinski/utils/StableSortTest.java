package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods of class org.ttrzcinski.utils.StableSort.
 *
 * @see StableSort
 */
class StableSortTest {

  @Test
  void stableSort_withNull() {
    // Arrange-Act
    String result = StableSort.stableSort(null);

    // Assert
    assertNull(result);
  }

  @Test
  void stableSort_withNotNull() {
    // Arrange
    String testObject = "900 24";

    String result = StableSort.stableSort(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void stableSort_withMulti() {
    // Arrange
    String testObject = "900 24 8 51 20202 333 1 222 42";

    String result = StableSort.stableSort(testObject);

    // Assert
    assertTrue(result.startsWith("1"));
  }
}
