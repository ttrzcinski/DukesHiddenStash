package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isArgumentTest {

  @Test
  void isArgument_wrongSide() {
    // Arrange
    String testObject = "h-";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_tooLong() {
    // Arrange
    String testObject = "---h";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_longGood() {
    // Arrange
    String testObject = "--h";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isArgument_shortGood() {
    // Arrange
    String testObject = "-h";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isArgument_tooShort() {
    // Arrange
    String testObject = "h";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_notEmptyAfterTrim_invalid() {
    // Arrange
    String testObject = "  h ";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_notEmptyAfterTrim_valid() {
    // Arrange
    String testObject = "  -h ";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isArgument_emptyAfterTrim() {
    // Arrange
    String testObject = "   ";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_empty() {
    // Arrange
    String testObject = "";

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isArgument_null() {
    // Arrange
    String testObject = null;

    // Act
    boolean result = ParamCheck.isArgument(testObject);

    // Assert
    assertFalse(result);
  }
}