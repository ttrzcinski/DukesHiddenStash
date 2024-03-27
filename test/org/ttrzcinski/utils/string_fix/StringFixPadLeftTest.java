package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_LEFT_TRIM;

class StringFix_padLeftTest {

  @Test
  void padLeft_someValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME_TO_LEFT_TRIM;
    int passedLength = 6;

    // Act
    String result = StringFix.padLeft(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_tooSmallValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = 3;

    // Act
    String result = StringFix.padLeft(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_zeroValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = 3;

    // Act
    String result = StringFix.padLeft(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_negativeValue() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = -1;

    // Act
    String result = StringFix.padLeft(testObject, -1);

    // Assert
    assertEquals(expected, result);
  }
}
