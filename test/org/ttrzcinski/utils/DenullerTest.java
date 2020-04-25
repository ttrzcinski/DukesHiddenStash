package org.ttrzcinski.utils;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.Denuller;

import static org.junit.jupiter.api.Assertions.*;

class DenullerTest {

  @Test
  void fix_object_withNull() {
    // Arrange
    Object testObject = null;

    // Act
    Object result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_object_withSomeObject() {
    // Arrange
    Object testObject = new Object();

    // Act
    Object result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withNull() {
    // Arrange
    HashMap<?, ?> testObject = null;

    // Act
    HashMap<?, ?> result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withEmpty() {
    // Arrange
    HashMap<?, ?> testObject = new HashMap<>();

    // Act
    HashMap<?, ?> result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withSomeNullOnlyHashMap() {
    // Arrange
    HashMap<String, ?> testObject = new HashMap<>();
    testObject.put("test1", null);

    // Act
    HashMap<?, ?> result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_hashMap_withSomeHashMap() {
    // Arrange
    HashMap<String, String> testObject = new HashMap<>();
    testObject.put("test1", "test_val");

    // Act
    HashMap<?, ?> result = Denuller.fix(testObject);

    // Assert
    assertNotNull(result);
  }

  @Test
  void fix_list_withNull() {
    // Arrange
    List<?> testObject = null;
    List expected = new ArrayList<>();

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void fix_list_withEmptyList() {
    // Arrange
    List<?> testObject = Collections.unmodifiableList(new ArrayList<>());
    List expected = new ArrayList<>();

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void fix_list_withListHavingOnlyNull() {
    // Arrange
    List<Object> testObject = new ArrayList<>();
    testObject.add(null);

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(testObject, result);
  }

  @Test
  void fix_list_withListHavingSomeItems() {
    // Arrange
    List<Object> testObject = new ArrayList<>();
    testObject.add(new Object());

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(testObject, result);
  }

  @Test
  void fix_list_withListHavingManyMixedItems() {
    // Arrange
    List<Object> testObject = new ArrayList<>();
    testObject.add(null);
    testObject.add(new Object());

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(testObject, result);
  }

  @Test
  void fix_list_withListHavingManyItems() {
    // Arrange
    List<Object> testObject = new ArrayList<>();
    testObject.add(new Object());
    testObject.add(new Object());

    // Act
    List<?> result = Denuller.fix(testObject);

    // Assert
    assertEquals(testObject, result);
  }
}
