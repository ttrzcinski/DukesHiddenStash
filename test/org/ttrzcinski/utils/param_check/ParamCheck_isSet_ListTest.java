package org.ttrzcinski.utils.param_check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

class ParamCheck_isSet_ListTest {

  @Test
  void isSet_List_withNull() {
    // Arrange
    List testList = null;

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withEmpty() {
    // Arrange
    List testList = new ArrayList();

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withOneNull() {
    // Arrange
    List testList = new ArrayList();
    testList.add(null);

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertFalse(result);
  }

  @Test
  void isSet_List_withOneNullMixed() {
    // Arrange
    List testList = new ArrayList();
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
    List testList = new ArrayList();
    testList.add("some");

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertTrue(result);
  }

  @Test
  void isSet_List_withManySome() {
    // Arrange
    List testList = new ArrayList();
    testList.add("some1");
    testList.add("some2");

    // Act
    boolean result = ParamCheck.isSet(testList);

    // Assert
    assertTrue(result);
  }
}