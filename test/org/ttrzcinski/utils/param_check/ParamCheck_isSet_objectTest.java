package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isSet_objectTest {

  @Test
  void isSet_object_withNull() {
    // Arrange
    Object testObject = null;

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_object_withNotNull() {
    // Arrange
    Object testObject = new Object();

    // Act
    boolean result = ParamCheck.isSet(testObject);

    // Assert
    assertTrue(result);
  }
}