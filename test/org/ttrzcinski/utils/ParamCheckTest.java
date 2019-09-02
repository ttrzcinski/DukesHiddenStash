package org.ttrzcinski.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ParamCheckTest {

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

  // TODO isSet List null
  // TODO isSet List empty
  // TODO isSet List not empty with null
  // TODO isSet List not empty with not null

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

  @Test
  void isPath_null() {
    // Arrange
    String testObject = "";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPath_empty() {
    // Arrange
    String testObject = "";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPath_emptyAfterTrim() {
    // Arrange
    String testObject = "    ";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPath_notEmptyAfterTrim_wrong() {
    // Arrange
    String testObject = "  sdasdasd\\asfasdasd\\asddasd  ";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPath_notEmptyAfterTrim_goodCatalog() {
    // Arrange
    String testObject = "  C:\\catalog  ";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isPath_wrong() {
    // Arrange
    String testObject = "sdasdasd\\asfasdasd\\asddasd";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void isPath_goodCatalogPath() {
    // Arrange
    String testObject = "C:\\catalog";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertTrue(result);
  }

  @Test
  void isPath_goodFilePath() {
    // Arrange
    String testObject = "C:\\catalog\\file.ext";

    // Act
    boolean result = ParamCheck.isPath(testObject);

    // Assert
    assertTrue(result);
  }

  // TODO UPDATE THOSE TWO METHODS COVERAGE
  @Test
  void filterWithPatterns() {
  }

  @Test
  void testFilterWithPatterns() {
  }
}