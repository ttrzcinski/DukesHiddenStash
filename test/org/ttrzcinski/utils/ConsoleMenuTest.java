package org.ttrzcinski.utils;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.dictionaries.FakerString;
import org.ttrzcinski.utils.ConsoleMenu;

/**
 * Test methods of class org.ttrzcinski.utils.ConsoleMenu.
 *
 * @see ConsoleMenu
 */
class ConsoleMenuTest {

  private PrintStream originalErr;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  public void startRecordingErrStreams() {
    this.outContent = new ByteArrayOutputStream();
    this.originalErr = System.out;
    System.setOut(new PrintStream(this.outContent));
  }

  @AfterEach
  public void stopRecordingErrStream() {
    System.setErr(this.originalErr);
  }

  private boolean outputContains(String message) {
    if (this.outContent != null | message != null) {
      return this.outContent.toString().contains(message);
    }
    return false;
  }

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
    List<String> testItems = asList(FakerString.NULL);
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
    List<String> testItems = asList(FakerString.EMPTY);
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
    List<String> testItems = asList(FakerString.EMPTY_TO_TRIM);
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
    List<String> testItems = asList(
            FakerString.NULL,
            FakerString.EMPTY,
            FakerString.EMPTY_TO_TRIM
    );
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
    List<String> testItems = asList(
            FakerString.NULL,
            FakerString.EMPTY,
            FakerString.EMPTY_TO_TRIM,
            FakerString.SOME_TO_TRIM
    );
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
    List<String> testItems = asList(FakerString.SOME);
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
    List<String> testItems = asList(FakerString.SOME_TO_TRIM);
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
    List<String> testItems = asList(FakerString.OTHER);
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
    List<String> testItems = asList(FakerString.OTHER_TO_TRIM);
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
    List<String> testItems = Collections.unmodifiableList(asList(
            FakerString.SOME,
            FakerString.SOME_TO_TRIM,
            FakerString.SOME_TO_LEFT_TRIM,
            FakerString.SOME_TO_RIGHT_TRIM,
            FakerString.OTHER,
            FakerString.OTHER_TO_TRIM
    ));
    int expectedLength = 6;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    int countMatches = IntStream.range(0, Math.max(testItems.size(), result.size()))
            .map(i -> result.get(i).contains(testItems.get(i).trim()) ? 1 : 0).sum();
    assertEquals(expectedLength, countMatches);
  }

  @Test
  void show_withoutConfirmation_hasNoItemsMessage() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testObject.withItems(testItems).prepare();
    String testErrMsg = "(MENU HAS NO ITEMS)";

    // Act
    testObject.show();

    // Assert
    assertTrue(this.outputContains(testErrMsg));
  }

  @Test
  void show_withoutConfirmation_lacksNoItemsMessage() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = asList(
            FakerString.SOME,
            FakerString.SOME_TO_TRIM,
            FakerString.SOME_TO_LEFT_TRIM,
            FakerString.SOME_TO_RIGHT_TRIM,
            FakerString.OTHER,
            FakerString.OTHER_TO_TRIM
    );
    testObject.withItems(testItems).prepare();
    String testErrMsg = "(MENU HAS NO ITEMS)";

    // Act
    testObject.show();

    // Assert
    assertFalse(this.outputContains(testErrMsg));
  }

  @Test
  void show_withoutConfirmation_hasLine() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    testObject.withItems(testItems).prepare();
    String testDividerLine = testObject.getDividerLine();

    // Act
    testObject.show();

    // Assert
    assertTrue(this.outputContains(testDividerLine));
  }

  @Test
  void hasDividerLine() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    String expectedLine = "-".repeat(44);

    // Act
    String actualLine = testObject.getDividerLine();

    // Assert
    assertEquals(expectedLine, actualLine);
  }
}
