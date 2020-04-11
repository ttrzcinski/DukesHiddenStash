package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isSet_stringArrayTest {

  @Test
  void isSet_stringArray_withNull() {
    // Arrange
    String[] testObjectArray = null;

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_stringArray_withEmpty() {
    // Arrange
    String[] testObjectArray = new String[]{};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_stringArray_withOneNull() {
    // Arrange
    String[] testObjectArray = new String[]{null};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_stringArray_withOneNullMixed() {
    // Arrange
    String[] testObjectArray = new String[]{null, "test"};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_stringArray_withNotNull() {
    // Arrange
    String[] testObjectArray = new String[]{"test"};

    // Act
    boolean result = ParamCheck.isSet(testObjectArray);

    // Assert
    assertTrue(result);
  }
}