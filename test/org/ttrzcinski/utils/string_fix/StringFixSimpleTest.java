package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.*;

class StringFixSimpleTest {

  @Test
  void simple_someValue() {
    // Arrange

    // Act
    String result = StringFix.simple(SOME_TO_TRIM);

    // Assert
    assertEquals(SOME, result);
  }

  @Test
  void simple_empty() {
    // Arrange

    // Act
    String result = StringFix.simple(EMPTY_TO_TRIM);

    // Assert
    assertEquals(EMPTY, result);
  }

  @Test
  void simple_null() {
    // Arrange

    // Act
    String result = StringFix.simple(NULL);

    // Assert
    assertEquals(EMPTY, result);
  }
}
