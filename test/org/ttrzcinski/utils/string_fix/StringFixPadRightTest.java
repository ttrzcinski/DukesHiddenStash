package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.SOME;
import static org.ttrzcinski.dictionaries.FakerString.SOME_TO_RIGHT_TRIM;

class StringFixPadRightTest {

  @Test
  void padRight_someValue() {
    // Arrange
    int passedLength = 6;

    // Act
    String result = StringFix.padRight(SOME, passedLength);

    // Assert
    assertEquals(SOME_TO_RIGHT_TRIM, result);
  }

  @Test
  void padRight_tooSmallValue() {
    // Arrange
    int passedLength = 3;

    // Act
    String result = StringFix.padRight(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void padRight_zeroValue() {
    // Arrange
    int passedLength = 0;

    // Act
    String result = StringFix.padRight(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void padRight_negativeValue() {
    // Arrange
    int passedLength = -1;

    // Act
    String result = StringFix.padRight(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }
}
