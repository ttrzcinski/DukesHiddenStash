package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.*;

class StringFixToNotNullTest {

  @Test
  void toNotNull_withNull() {
    // Arrange

    // Act
    String result = StringFix.toNotNull(NULL);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void toNotNull_withEmpty() {
    // Arrange

    // Act
    String result = StringFix.toNotNull(EMPTY);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void toNotNull_withTrimToEmpty() {
    // Arrange

    // Act
    String result = StringFix.toNotNull(EMPTY_TO_TRIM);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void toNotNull_withTrimToGood() {
    // Arrange

    // Act
    String result = StringFix.toNotNull(SOME_TO_TRIM);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void toNotNull_withGood() {
    // Arrange

    // Act
    String result = StringFix.toNotNull(SOME);

    // Assert
    assertEquals(SOME, result);
  }
}
