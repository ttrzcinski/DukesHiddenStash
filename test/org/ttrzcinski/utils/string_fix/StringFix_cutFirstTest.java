package org.ttrzcinski.utils.string_fix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.*;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

class StringFix_cutFirstTest {

  @Test
  void cutFirst_withNull() {
    // Arrange
    String testObject = NULL;
    String expected = NULL;
    int passedLength = -1;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withEmpty_withNegative() {
    // Arrange
    String testObject = EMPTY;
    String expected = EMPTY;
    int passedLength = -1;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withEmpty_withZero() {
    // Arrange
    String testObject = EMPTY;
    String expected = EMPTY;
    int passedLength = 0;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withEmpty_withTooLong() {
    // Arrange
    String testObject = EMPTY;
    String expected = EMPTY;
    int passedLength = 1;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withSome_withNegative() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = -1;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withSome_withZero() {
    // Arrange
    String testObject = SOME;
    String expected = SOME;
    int passedLength = 0;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withSome_withLengthWithin() {
    // Arrange
    String testObject = SOME;
    int passedLength = 2;
    String expected = SOME.substring(passedLength);

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withSome_withWholeLength() {
    // Arrange
    String testObject = SOME;
    String expected = EMPTY;
    int passedLength = SOME.length();

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void cutFirst_withSome_withTooLong() {
    // Arrange
    String testObject = SOME;
    String expected = EMPTY;
    int passedLength = SOME.length() + 2;

    // Act
    String result = StringFix.cutFirst(testObject, passedLength);

    // Assert
    assertEquals(expected, result);
  }
}
