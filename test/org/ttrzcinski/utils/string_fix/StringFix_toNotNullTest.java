package org.ttrzcinski.utils.string_fix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.EMPTY;
import static org.ttrzcinski.dictionaries.FakerString.EMPTY_TO_TRIM;
import static org.ttrzcinski.dictionaries.FakerString.NULL;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_TRIM;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

class StringFix_toNotNullTest {

  @Test
  void toNotNull_withNull() {
    // Arrange
    String testObject = NULL;
    String expected = EMPTY;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toNotNull_withEmpty() {
    // Arrange
    String testObject = EMPTY;
    String expected = EMPTY;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toNotNull_withTrimToEmpty() {
    // Arrange
    String testObject = EMPTY_TO_TRIM;
    String expected = EMPTY;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toNotNull_withTrimToGood() {
    // Arrange
    String testObject = SOME_TO_TRIM;
    String expected = SOME;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toNotNull_withGood() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }
}
