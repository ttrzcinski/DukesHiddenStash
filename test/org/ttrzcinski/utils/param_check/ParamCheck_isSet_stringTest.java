package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isSet_stringTest {

  @Test
  void isSet_string_withNull() {
    // Arrange
    String testObject = null;

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_string_withEmpty() {
    // Arrange
    String testObject = "";

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_string_withEmptyAfterTrim() {
    // Arrange
    String testObject = "   ";

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_string_withNotEmptyAfterTrim() {
    // Arrange
    String testObject = "  test  ";

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isSet_string_withNotNull() {
    // Arrange
    String testObject = "test";

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertTrue(result);
  }
}
