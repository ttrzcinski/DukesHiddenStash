package org.ttrzcinski.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.dictionaries.FakerString;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

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
    return this.outContent != null && message != null && this.outContent.toString().contains(message);
  }

  @Test
  void emptyObject() {
    // Arrange
    var testObject = new ConsoleMenu();
    var expectedLength = 0;

    // Act
    var result = testObject.prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withHeader() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testHeader = "test_header";
    var expectedLength = 1;

    // Act
    var result = testObject
            .withHeader(testHeader)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(testHeader));
  }

  @Test
  void withBack() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testEntry = ConsoleMenu.BACK_ENTRY;
    var expectedLength = 1;

    // Act
    var result = testObject
            .withBack()
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(testEntry));
  }

  @Test
  void withBack_withFalse() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testBackStatus = false;
    var expectedLength = 0;

    // Act
    var result = testObject
            .withBack(testBackStatus)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withBack_withTrue() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testEntry = ConsoleMenu.BACK_ENTRY;
    var testBackStatus = true;
    var expectedLength = 1;

    // Act
    var result = testObject
            .withBack(testBackStatus)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(testEntry));
  }

  @Test
  void withItem_withNull() {
    // Arrange
    var expectedLength = 0;

    // Act
    var result = new ConsoleMenu()
            .withItem(FakerString.NULL)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItem_withEmpty() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItem = FakerString.EMPTY;
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItem(testItem)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  // TODO CHANGE TO USAGE OF STREAM PARAMS AS @data
  @Test
  void withItem_withEmptyToTrim() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItem = FakerString.EMPTY_TO_TRIM;
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItem(testItem)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItem_withSome() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItem = FakerString.SOME;
    var expectedLength = 1;

    // Act
    var result = testObject
            .withItem(testItem)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains("1."));
    assertTrue(result.getFirst().contains(testItem));
  }

  @Test
  void withItem_withSomeToTrim() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItem = FakerString.SOME_TO_TRIM;
    var expectedLength = 1;
    var testExpectedItem = FakerString.SOME;

    // Act
    var result = testObject
            .withItem(testItem)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains("1."));
    assertTrue(result.getFirst().contains(testExpectedItem));
  }

  @Test
  void withItems_withNull() {
    // Arrange
    var testObject = new ConsoleMenu();
    List<String> testItems = null;
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withEmpty() {
    // Arrange
    var testObject = new ConsoleMenu();
    List<String> testItems = new ArrayList<>();
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneNull() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = Collections.singletonList(FakerString.NULL);
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneEmpty() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = List.of(FakerString.EMPTY);
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneEmptyToTrim() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = List.of(FakerString.EMPTY_TO_TRIM);
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOnlyEmptyValues() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = asList(
            FakerString.NULL,
            FakerString.EMPTY,
            FakerString.EMPTY_TO_TRIM
    );
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withMixedValues() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = asList(
            FakerString.NULL,
            FakerString.EMPTY,
            FakerString.EMPTY_TO_TRIM,
            FakerString.SOME_TO_TRIM
    );
    var expectedLength = 0;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
  }

  @Test
  void withItems_withOneSomeValue() {
    // Arrange
    var testObject = new ConsoleMenu();
    var testItems = List.of(FakerString.SOME);
    var expectedLength = 1;
    var expectedItem = FakerString.SOME;

    // Act
    var result = testObject
            .withItems(testItems)
            .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(expectedItem));
  }

  @Test
  void withItems_withOneSomeToTrimValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = List.of(FakerString.SOME_TO_TRIM);
    int expectedLength = 1;
    String expectedItem = FakerString.SOME;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(expectedItem));
  }

  @Test
  void withItems_withOneOtherValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = List.of(FakerString.OTHER);
    int expectedLength = 1;
    String expectedItem = FakerString.OTHER;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(expectedItem));
  }

  @Test
  void withItems_withOneOtherToTrimValue() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = List.of(FakerString.OTHER_TO_TRIM);
    int expectedLength = 1;
    String expectedItem = FakerString.OTHER;

    // Act
    List<String> result = testObject
        .withItems(testItems)
        .prepare();

    // Assert
    assertEquals(expectedLength, result.size());
    assertTrue(result.getFirst().contains(expectedItem));
  }

  @Test
  void withItems_withOnlySomeValues() {
    // Arrange
    ConsoleMenu testObject = new ConsoleMenu();
    List<String> testItems = List.of(
            FakerString.SOME,
            FakerString.SOME_TO_TRIM,
            FakerString.SOME_TO_LEFT_TRIM,
            FakerString.SOME_TO_RIGHT_TRIM,
            FakerString.OTHER,
            FakerString.OTHER_TO_TRIM);
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
