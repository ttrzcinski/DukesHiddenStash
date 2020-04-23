package org.ttrzcinski.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.dictionaries.FakerString;
import org.ttrzcinski.utils.ConsoleMenu;

/**
 * Test methods of class org.ttrzcinski.utils.ConsoleMenu.
 *
 * @see ConsoleMenu
 */
class ConsoleMenuTest {

  @Test
  void emptyObject() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    int expectedLength = 0;

    // Act
    List<String> result = testObject.prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withHeader() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testHeader = "test_header";
    int expectedLength = 1;

    // Act
    List<String> result = testObject
        .withHeader(testHeader)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(testHeader));
  }

  @Test
  void withBack() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testEntry = ConsoleMenu.BACK_ENTRY;
    int expectedLength = 1;

    // Act
    List<String> result = testObject
        .withBack()
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(testEntry));
  }

  @Test
  void withBack_withFalse() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    boolean testBackStatus = false;
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withBack(testBackStatus)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withBack_withTrue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testEntry = ConsoleMenu.BACK_ENTRY;
    boolean testBackStatus = true;
    int expectedLength = 1;

    // Act
    List<String> result = testObject
        .withBack(testBackStatus)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(testEntry));
  }

  @Test
  void withItem_withNull() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testItem = FakerString.NULL;
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItem(testItem)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItem_withEmpty() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testItem = FakerString.EMPTY;
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItem(testItem)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItem_withEmptyToTrim() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testItem = FakerString.EMPTY_TO_TRIM;
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItem(testItem)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItem_withSome() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testItem = FakerString.SOME;
    int expectedLength = 1;

    // Act
    List<String> result = testObject
        .withItem(testItem)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains("1."));
    assertTrue(result.get(0).contains(testItem));
  }

  @Test
  void withItem_withSomeToTrim() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String testItem = FakerString.SOME_TO_TRIM;
    int expectedLength = 1;
    String testExpectedItem = FakerString.SOME;

    // Act
    List<String> result = testObject
        .withItem(testItem)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains("1."));
    assertTrue(result.get(0).contains(testExpectedItem));
  }

  @Test
  void withItems_withNull() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = null;
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withEmpty() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneNull() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.NULL);
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneEmpty() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.EMPTY);
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneEmptyToTrim() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.EMPTY_TO_TRIM);
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOnlyEmptyValues() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.NULL);
    testItems.add(FakerString.EMPTY);
    testItems.add(FakerString.EMPTY_TO_TRIM);
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withMixedValues() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.NULL);
    testItems.add(FakerString.EMPTY);
    testItems.add(FakerString.EMPTY_TO_TRIM);
    testItems.add(FakerString.SOME_TO_TRIM);
    int expectedLength = 0;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneSomeValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.SOME);
    int expectedLength = 1;
    String expectedItem = FakerString.SOME;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(expectedItem));
  }

  @Test
  void withItems_withOneSomeToTrimValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.SOME_TO_TRIM);
    int expectedLength = 1;
    String expectedItem = FakerString.SOME;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(expectedItem));
  }

  @Test
  void withItems_withOneOtherValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.OTHER);
    int expectedLength = 1;
    String expectedItem = FakerString.OTHER;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(expectedItem));
  }

  @Test
  void withItems_withOneOtherToTrimValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.OTHER_TO_TRIM);
    int expectedLength = 1;
    String expectedItem = FakerString.OTHER;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.get(0).contains(expectedItem));
  }

  @Test
  void withItems_withOnlySomeValues() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testItems.add(FakerString.SOME);
    testItems.add(FakerString.SOME_TO_TRIM);
    testItems.add(FakerString.SOME_TO_LEFT_TRIM);
    testItems.add(FakerString.SOME_TO_RIGHT_TRIM);
    testItems.add(FakerString.OTHER);
    testItems.add(FakerString.OTHER_TO_TRIM);
    int expectedLength = 6;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    for (int i = 0; i < Math.max(testItems.size(), result.size()); i++) {
      assertTrue(result.get(i).contains(testItems.get(i).trim()));
    }
  }

  /*@Test
  void withItems() {
    // Arrange
    Object testObject = null;

    // Act
    Object result = withItems(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void prepare() {
    // Arrange
    Object testObject = null;

    // Act
    Object result = prepare(testObject);

    // Assert
    assertFalse(result);
  }

  @Test
  void show() {
    // Arrange
    Object testObject = null;

    // Act
    Object result = show(testObject);

    // Assert
    assertFalse(result);
  }*/
}
