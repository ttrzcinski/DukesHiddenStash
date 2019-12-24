package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isPathTest {

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
}