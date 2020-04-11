package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isBetweenTest {

  @Test
  void inBetween_outLeft() {
    // Arrange
    int testObject = 0;
    int left = 1;
    int right = 2;

    // Act
    boolean result = ParamCheck.inBetween(testObject, left, right);

    // Assert
    assertFalse(result);
  }

  @Test
  void inBetween_outRight() {
    // Arrange
    int testObject = 3;
    int left = 1;
    int right = 2;

    // Act
    boolean result = ParamCheck.inBetween(testObject, left, right);

    // Assert
    assertFalse(result);
  }

  @Test
  void inBetween_rightInTight() {
    // Arrange
    int testObject = 1;
    int left = 1;
    int right = 1;

    // Act
    boolean result = ParamCheck.inBetween(testObject, left, right);

    // Assert
    assertTrue(result);
  }

  @Test
  void inBetween_rightInWide() {
    // Arrange
    int testObject = 0;
    int left = -1;
    int right = 1;

    // Act
    boolean result = ParamCheck.inBetween(testObject, left, right);

    // Assert
    assertTrue(result);
  }
}