package org.ttrzcinski.utils.param_check;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParamCheckIsSetObjectTest {

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
