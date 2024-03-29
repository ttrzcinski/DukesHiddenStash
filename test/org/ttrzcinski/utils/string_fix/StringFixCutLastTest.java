package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.*;

class StringFixCutLastTest {

  @Test
  void cutLast_withNull() {
    // Arrange
    int passedLength = -1;

    // Act
    String result = StringFix.cutLast(NULL, passedLength);

    // Assert
    assertEquals(NULL, result);
  }

  @Test
  void cutLast_withEmpty_withNegative() {
    // Arrange
    int passedLength = -1;

    // Act
    String result = StringFix.cutLast(EMPTY, passedLength);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void cutLast_withEmpty_withZero() {
    // Arrange
    int passedLength = 0;

    // Act
    String result = StringFix.cutLast(EMPTY, passedLength);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void cutLast_withEmpty_withTooLong() {
    // Arrange
    int passedLength = 1;

    // Act
    String result = StringFix.cutLast(EMPTY, passedLength);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void cutLast_withSome_withNegative() {
    // Arrange
    int passedLength = -1;

    // Act
    String result = StringFix.cutLast(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void cutLast_withSome_withZero() {
    // Arrange
    int passedLength = 0;

    // Act
    String result = StringFix.cutLast(SOME, passedLength);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void cutLast_withSome_withLengthWithin() {
    // Arrange
    int passedLength = 2;
    String expected = SOME.substring(0, passedLength);

    // Act
    String result = StringFix.cutLast(SOME, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutLast_withSome_withWholeLength() {
    // Arrange
    int passedLength = SOME.length();

    // Act
    String result = StringFix.cutLast(SOME, passedLength);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void cutLast_withSome_withTooLong() {
    // Arrange
    int passedLength = SOME.length() + 2;

    // Act
    String result = StringFix.cutLast(SOME, passedLength);

    // Assert
    assertEquals(EMPTY, result);
  }
}
