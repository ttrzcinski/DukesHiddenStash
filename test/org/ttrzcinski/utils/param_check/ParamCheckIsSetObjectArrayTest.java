package org.ttrzcinski.utils.param_check;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParamCheckIsSetObjectArrayTest {

  @Test
  void isSet_objectArray_withNull() {
    // Arrange
    Object[] testObjectArray = null;

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_objectArray_withEmpty() {
    // Arrange
    Object[] testObjectArray = new Object[]{};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_objectArray_withOneNull() {
    // Arrange
    Object[] testObjectArray = new Object[]{null};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_objectArray_withOneNullMixed() {
    // Arrange
    Object[] testObjectArray = new Object[]{null, new Object()};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_objectArray_withNotNull() {
    // Arrange
    Object[] testObjectArray = new Object[]{new Object()};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertTrue(result);
  }
}
