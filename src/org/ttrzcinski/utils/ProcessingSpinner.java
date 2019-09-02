package org.ttrzcinski.utils;

/**
 * Will show processing in CLI, like spinner in browser.
 */
public class ProcessingSpinner {

  /**
   * Kept line of processing.
   */
  private String keptLine = "";

  /**
   * Prints to console given line.
   *
   * @param line counted line
   */
  private void print(String line) {
    // Stop, if finished
    if (this.keptLine.length() > line.length()) {
      String temp = " ".repeat(this.keptLine.length());
      if (temp.length() > 1) {
        System.out.printf("\r%s", temp);
      }
    }
    System.out.printf("\r%s", line);
    this.keptLine = line;
  }

  /**
   * Counts current spin frame.
   *
   * @param iterator passed value of iterator
   * @param line line to present
   */
  private void spin(int iterator, String line) {
    if (iterator % 4 == 0) {
      this.print(String.format("< - > %s", line));
    } else if (iterator % 4 == 1) {
      this.print(String.format("< \\ > %s", line));
    } else if (iterator % 4 == 2) {
      this.print(String.format("< | > %s", line));
    } else if (iterator % 4 == 3) {
      this.print(String.format("< / > %s", line));
    }
  }

  /**
   * Presents a single line process in CLI.
   *
   * @param limit wanted limit of process
   * @param message given message
   * @return true, if worked, false otherwise
   * @throws InterruptedException if thread pool exploded
   */
  public boolean runProcess(int limit, String message) throws InterruptedException {
    final ProcessingSpinner processingSpinner = new ProcessingSpinner();
    int i = 0;
    if (i <= limit) {
      do {
        processingSpinner.spin(i, String.format("%d/%d - %s", i, limit, message));
        // TODO HERE ADD CALL THROUGH REFLECTION TO ACTUAL PROCESSING
        Thread.sleep(200);
        i += 1;
      } while (i <= limit);
      return true;
    }
    return false;
  }

  /**
   * Shows processing with spinner without an object.
   *
   * @param limit limit of things to process
   * @param message message to be presented
   * @throws InterruptedException if thread pool exploded
   */
  public static void process(int limit, String message) throws InterruptedException {
    final ProcessingSpinner processingSpinner = new ProcessingSpinner();
    processingSpinner.runProcess(limit, message);
  }
}
