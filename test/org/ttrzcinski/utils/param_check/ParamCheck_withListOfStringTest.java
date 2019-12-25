package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_withListOfStringTest {

  @Test
  void filterWithPatterns_withListOfString_withNull_withNull() {
    // Arrange
    List<String> testArguments = null;
    List<String> testPatterns = null;
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withEmpty_withNull() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    List<String> testPatterns = null;
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withEmpty_withEmpty() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    List<String> testPatterns = new ArrayList<>();
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withEmpty_withSome() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test");
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withSome_withSomeMatching() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    testArguments.add("test1");
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test1");
    testPatterns.add("test2");
    List<String> expected = new ArrayList<>();
    expected.add("test1");

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withSome_withOnlyMatching() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    testArguments.add("test1");
    testArguments.add("test2");
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test1");
    testPatterns.add("test2");
    List<String> expected = new ArrayList<>();
    expected.add("test1");
    expected.add("test2");

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withListOfString_withSome_withOnlyUnmatching() {
    // Arrange
    List<String> testArguments = new ArrayList<>();
    testArguments.add("test1");
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test2");
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }
}