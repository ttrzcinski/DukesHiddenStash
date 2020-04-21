package org.ttrzcinski.utils.param_check;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParamCheck_isSet_ListTest {

    @Test
    void isSet_List_withNull() {
        // Arrange
        List<String> testList = null;

        // Act
        boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withEmpty() {
    // Arrange
    List<String> testList = new ArrayList<>();

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withOneNull() {
    // Arrange
      List<String> testList = new ArrayList<>();
    testList.add(null);

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withOneNullMixed() {
    // Arrange
      List<String> testList = new ArrayList<>();
    testList.add(null);
    testList.add("some");

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withSome() {
    // Arrange
      List<String> testList = new ArrayList<>();
    testList.add("some");

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertTrue(result);
  }

  @Test
  void isSet_List_withManySome() {
    // Arrange
      List<String> testList = new ArrayList<>();
    testList.add("some1");
    testList.add("some2");

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertTrue(result);
  }
}
