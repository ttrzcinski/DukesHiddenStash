package test.ttrzcinski.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StableSort;

/**
 * Test methods of class org.ttrzcinski.utils.StableSort.
 *
 * @see StableSort
 */
class StableSortTest {

  @Test
  void stableSort_withNull() {
    // Arrange
    String testObject = null;

    String result = StableSort.stableSort(testObject);

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
