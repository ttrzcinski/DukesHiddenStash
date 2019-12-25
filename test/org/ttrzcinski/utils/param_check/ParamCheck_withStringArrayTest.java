package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_withStringArrayTest {

  @Test
  void filterWithPatterns_withStringArray_withNull_withNull() {
    // Arrange
    String[] testArguments = null;
    List<String> testPatterns = null;
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withStringArray_withEmpty_withNull() {
    // Arrange
    String[] testArguments = new String[]{};
    List<String> testPatterns = null;
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withStringArray_withEmpty_withEmpty() {
    // Arrange
    String[] testArguments = new String[]{};
    List<String> testPatterns = new ArrayList<>();
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withStringArray_withEmpty_withSome() {
    // Arrange
    String[] testArguments = new String[]{};
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test");
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void filterWithPatterns_withStringArray_withSome_withSomeMatching() {
    // Arrange
    String[] testArguments = new String[]{"test1"};
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
  void filterWithPatterns_withStringArray_withSome_withOnlyMatching() {
    // Arrange
    String[] testArguments = new String[]{"test1", "test2"};
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
  void filterWithPatterns_withStringArray_withSome_withOnlyUnmatching() {
    // Arrange
    String[] testArguments = new String[]{"test1"};
    List<String> testPatterns = new ArrayList<>();
    testPatterns.add("test2");
    List<String> expected = new ArrayList<>();

    // Act
    List<String> result = ParamCheck.filterWithPatterns(testArguments, testPatterns);

    // Assert
    assertEquals(expected, result);
  }
}