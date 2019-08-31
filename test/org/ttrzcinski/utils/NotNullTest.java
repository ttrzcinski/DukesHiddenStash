package org.ttrzcinski.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

class NotNullTest {

  @Test
  void fix_object_withNull() {
    // Arrange
    Object testObject = null;

    // Act
    Object result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_object_withSomeObject() {
    // Arrange
    Object testObject = new Object();

    // Act
    Object result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withNull() {
    // Arrange
    HashMap testObject = null;

    // Act
    HashMap result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withSomeHashMap() {
    // Arrange
    HashMap testObject = new HashMap();

    // Act
    HashMap result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_list_withNull() {
    // Arrange
    List testObject = null;

    // Act
    List result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_list_withSomeList() {
    // Arrange
    List testObject = new ArrayList();

    // Act
    List result = NotNull.fix(testObject);

    // Assert
    assertNotNull(result);
  }

}
