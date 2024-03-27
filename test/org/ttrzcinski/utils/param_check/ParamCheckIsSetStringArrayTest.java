package org.ttrzcinski.utils.param_check;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParamCheckIsSetStringArrayTest {

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
