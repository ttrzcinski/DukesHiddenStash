package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isPositiveTest {

  @Test
  void isPositive_withNegative() {
    // Arrange
    int testObject = -1;

    // Act
    boolean result = ParamCheck.isPositive(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPositive_withZero() {
    // Arrange
    int testObject = 1;

    // Act
    boolean result = ParamCheck.isPositive(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isPositive_withPositive() {
    // Arrange
    int testObject = 1;

    // Act
    boolean result = ParamCheck.isPositive(testObject);

    // Assert
    assertTrue(result);
  }
}