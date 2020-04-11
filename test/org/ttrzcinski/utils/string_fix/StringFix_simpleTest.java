package org.ttrzcinski.utils.string_fix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.EMPTY;
import static org.ttrzcinski.dictionaries.FakerString.EMPTY_TO_TRIM;
import static org.ttrzcinski.dictionaries.FakerString.NULL;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_TRIM;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

class StringFix_simpleTest {

  @Test
  void simple_someValue() {
    // Arrange
    String testObject = SOME_TO_TRIM;
    String expected = SOME;

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void simple_empty() {
    // Arrange
    String testObject = EMPTY_TO_TRIM;
    String expected = EMPTY;

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void simple_null() {
    // Arrange
    String testObject = NULL;
    String expected = EMPTY;

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }
}