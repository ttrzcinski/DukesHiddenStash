package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_LEFT_TRIM;

class StringFixPadLeftTest {

  @Test
  void padLeft_someValue() {
    // Arrange
    int passedLength = 6;

    // Act
    String result = StringFix.padLeft(SOME, passedLength);

    // Assert
    assertEquals(SOME_TO_LEFT_TRIM, result);
  }

  @Test
  void padLeft_tooSmallValue() {
    // Arrange
    int passedLength = 3;

    // Act
    String result = StringFix.padLeft(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void padLeft_zeroValue() {
    // Arrange
    int passedLength = 3;

    // Act
    String result = StringFix.padLeft(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void padLeft_negativeValue() {
    // Arrange
    int passedLength = -1;

    // Act
    String result = StringFix.padLeft(SOME, -1);

    // Assert
    assertEquals(SOME, result);
  }
}
