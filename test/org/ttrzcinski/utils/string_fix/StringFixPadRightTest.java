package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_RIGHT_TRIM;

class StringFix_padRightTest {

  @Test
  void padRight_someValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME_TO_RIGHT_TRIM;
    int passedLength = 6;

    // Act
    String result = StringFix.padRight(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_tooSmallValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = 3;

    // Act
    String result = StringFix.padRight(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_zeroValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = 0;

    // Act
    String result = StringFix.padRight(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_negativeValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = -1;

    // Act
    String result = StringFix.padRight(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }
}
