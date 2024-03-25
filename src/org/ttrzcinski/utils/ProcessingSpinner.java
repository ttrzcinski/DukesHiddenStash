package org.ttrzcinski.utils;

/**
 * Will show processing in CLI, like spinner in browser.
 */
public final class ProcessingSpinner {

  /**
   * Used timeout to mark thread sleep time.
   */
  private static final int WORK_TIMEOUT = 200;

  /**
   * Count of spinning animation phases.
   */
  private static final int PHASES_COUNT = 4;

  /**
   * Kept line of processing.
   */
  private String keptLine = "";

  /**
   * Hidden constructor.
   */
  private ProcessingSpinner() {
  }

  /**
   * Prints to console given line.
   *
   * @param line counted line
   */
  private void print(final String line) {
    // Stop, if finished
    if (this.keptLine.length() > line.length()) {
      final String temp = " ".repeat(this.keptLine.length());
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
  private void spin(final int iterator, final String line) {
    if (iterator % PHASES_COUNT == 0) {
      this.print(String.format("< - > %s", line));
    } else if (iterator % PHASES_COUNT == 1) {
      this.print(String.format("< \\ > %s", line));
    } else if (iterator % PHASES_COUNT == 2) {
      this.print(String.format("< | > %s", line));
    } else if (iterator % PHASES_COUNT == 3) {
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
  public boolean runProcess(
      final int limit, final String message
  ) throws InterruptedException {
    final ProcessingSpinner processingSpinner = new ProcessingSpinner();
    int i = 0;
    if (i > limit) {
      return false;
    }
    do {
      processingSpinner.spin(i,
          String.format("%d/%d - %s", i, limit, message)
      );
      // TODO HERE ADD CALL THROUGH REFLECTION TO ACTUAL PROCESSING
      Thread.sleep(WORK_TIMEOUT);
      i += 1;
    } while (i <= limit);
    return true;
  }

  /**
   * Shows processing with spinner without an object.
   *
   * @param limit limit of things to process
   * @param message message to be presented
   * @throws InterruptedException if thread pool exploded
   */
  public static void process(final int limit, final String message) throws InterruptedException {
    final ProcessingSpinner processingSpinner = new ProcessingSpinner();
    processingSpinner.runProcess(limit, message);
  }
}
