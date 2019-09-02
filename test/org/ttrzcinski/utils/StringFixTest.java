package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFixTest {

  @Test
  void toNotNull_withNull() {
    // Arrange
    String testObject = null;

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals("", result);
  }

  @Test
  void toNotNull_withEmpty() {
    // Arrange
    String testObject = "";

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals("", result);
  }

  @Test
  void toNotNull_withTrimToEmpty() {
    // Arrange
    String testObject = "   ";

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals("", result);
  }

  @Test
  void toNotNull_withTrimToGood() {
    // Arrange
    String testObject = "  test  ";
    String expected = testObject.trim();

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toNotNull_withGood() {
    // Arrange
    String testObject = "test";

    // Act
    String result = StringFix.toNotNull(testObject);

    // Assert
    assertEquals(testObject, result);
  }

  @Test
  void simple_someValue() {
    // Arrange
    String testObject = "  TEST  ";
    String expected = "test";

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void simple_empty() {
    // Arrange
    String testObject = "    ";
    String expected = "";

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void simple_null() {
    // Arrange
    String testObject = null;
    String expected = "";

    // Act
    String result = StringFix.simple(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_someValue() {
    // Arrange
    String testObject = "test";
    String expected = "test      ";

    // Act
    String result = StringFix.padRight(testObject, 10);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_tooSmallValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padRight(testObject, 3);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_zeroValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padRight(testObject, 0);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padRight_negativeValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padRight(testObject, -1);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_someValue() {
    // Arrange
    String testObject = "test";
    String expected = "      test";

    // Act
    String result = StringFix.padLeft(testObject, 10);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_tooSmallValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padLeft(testObject, 3);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_zeroValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padLeft(testObject, 0);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void padLeft_negativeValue() {
    // Arrange
    String testObject = "test";
    String expected = "test";

    // Act
    String result = StringFix.padLeft(testObject, -1);

    // Assert
    assertEquals(expected, result);
  }
}