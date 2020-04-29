package org.ttrzcinski.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


/**
 * Keeps list of items from a single menu view.
 */
public class ConsoleMenu {

  /**
   * Entry with Back to upper menu.
   */
  public static final String BACK_ENTRY = "0. ..BACK";

  /**
   * Kept list of items.
   */
  private List<String> items;

  /**
   * Marks, if menu should contain "..back" entry.
   */
  private boolean back;

  /**
   * Gives brief description or contains a question.
   */
  private String header;

  /**
   * Used for input of chosen option from console.
   */
  private final Scanner scanner;

  /**
   * Used divider line.
   */
  private static final String dividerLine = "-".repeat(44);

  /**
   * Creates new instance of console menu.
   */
  public ConsoleMenu() {
    this.scanner = new Scanner(System.in);

    this.items = new ArrayList<>();
  }

  /**
   * Adds header with given description to console menu.
   *
   * @param header given header
   * @return prepared instance of console menu
   */
  public final ConsoleMenu withHeader(String header) {
    if (ParamCheck.isSet(header)) {
      this.header = header;
    }
    return this;
  }

  /**
   * Sets mark of presenting back option in console menu.
   *
   * @return prepared instance of console menu
   */
  public final ConsoleMenu withBack() {
    this.back = true;
    return this;
  }

  /**
   * Sets mark of presenting back option in console menu, if value was positive.
   *
   * @param back wanted flag value
   * @return prepared instance of console menu
   */
  public final ConsoleMenu withBack(boolean back) {
    this.back = back;
    return this;
  }

  /**
   * Adds given item to menu options.
   *
   * @param item given item
   * @return prepared instance of console menu
   */
  public final ConsoleMenu withItem(String item) {
    if (ParamCheck.isSet(item)) {
      this.items.add(item);
    }
    return this;
  }

  /**
   * Adds given list of items to menu options.
   *
   * @param items given list of items
   * @return prepared instance of console menu
   */
  public final ConsoleMenu withItems(List<String> items) {
    if (ParamCheck.isSet(items)) {
      this.items.addAll(items);
    }
    return this;
  }

  /**
   * Prepare list with indexes to present in wanted output.
   *
   * @return list of items with indexes.
   */
  public final List<String> prepare() {
    List<String> listToShow = new ArrayList<>();
    // Process header
    if (ParamCheck.isSet(this.header)) {
      listToShow.add("%s%n%s%n".formatted(this.header, ConsoleMenu.dividerLine));
    }
    // Process back flag
    if (this.back) {
      listToShow.add(BACK_ENTRY);
    }
    // Process list of items
    if (!this.items.isEmpty()) {
      List<String> strings = this.items;
      IntStream.range(0, strings.size()).forEachOrdered(i -> {
        String item = strings.get(i);
        if (ParamCheck.isSet(item)) {
          listToShow.add("%d. %s".formatted(i + 1, item.trim()));
        }
      });
    }
    return listToShow;
  }

  /**
   * Shows on standard console prepared menu with list of items and default confirmation.
   */
  public final void show() {
    this.show(false);
  }

  /**
   * Shows on standard console prepared menu with list of items.
   *
   * @param userConfirmation marks, if user must confirm
   */
  public final void show(boolean userConfirmation) {
    List<String> listToShow = this.prepare();
    if (listToShow.isEmpty()) {
      listToShow.add("(MENU HAS NO ITEMS)");
    }
    listToShow.add(ConsoleMenu.dividerLine);
    //
    listToShow.forEach(System.out::println);
    if (userConfirmation) {
      listToShow.add("Press the right key to choose wanted option.");
      String choice = this.scanner.nextLine();
      System.out.printf("Your choice is %s.%n", choice);
    }
  }

  /**
   * Returns divider line for console.
   *
   * @return divider line
   */
  public String getDividerLine() {
    return ConsoleMenu.dividerLine;
  }
}
