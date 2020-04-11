package org.ttrzcinski.interfaces;

/**
 * While extending the class, eases the System.out.print("[INTO]" down to out(.
 */
public abstract class AbstractConsoleOutput {

  /**
   * Kept pattern to concatenate two strings.
   */
  private static final String CONCAT_PATTERN_2 = "%s%s";

  /**
   * Calls standard output.
   *
   * @param given given phrase
   */
  public final void out(final String given) {
    System.out.printf("[INFO] %s%n", given);
  }

  /**
   * Calls standard error.
   *
   * @param given given phrase
   */
  public final void err(final String given) {
    System.err.printf("[ERR] %s%n", given);
  }

  /**
   * Calls standard output with warning.
   *
   * @param given given phrase
   */
  public final void warn(final String given) {
    System.out.printf("[WARN]%s%n", given);
  }

  /**
   * Passes output to standard output stream, if condition was fulfilled.
   *
   * @param showOutput condition to fulfill
   * @param phrase phrase to use as beginning of message
   * @param param passed parameter
   */
  public final void conditionalOutput(
      final boolean showOutput, final String phrase, final String param
  ) {
    if (showOutput) {
      this.out(String.format(CONCAT_PATTERN_2, phrase, param));
    }
  }

  /**
   * Passes error to standard error stream, if condition was fulfilled.
   *
   * @param showError condition to fulfill
   * @param phrase phrase to use as beginning of message
   * @param param passed parameter
   */
  public final void conditionalError(
      final boolean showError, final String phrase, final String param
  ) {
    if (showError) {
      this.err(String.format(CONCAT_PATTERN_2, phrase, param));
    }
  }
}
