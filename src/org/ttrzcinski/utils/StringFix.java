package org.ttrzcinski.utils;


import org.jetbrains.annotations.NotNull;

/**
 * Keeps methods to fix String to common standard.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public final class StringFix {

  /**
   * Hidden constructor - there is no point to initialize an instance.
   */
  private StringFix() {
  }

  /**
   * Fixed string to not-null initialized empty string, if is null.
   *
   * @param given given string to fix
   * @return fixed string
   */
  public static String toNotNull(final String given) {
    if (given == null || given.trim().length() == 0) {
      return "";
    } else {
      return given.trim();
    }
  }

  /**
   * Simplifies string to trimmed and lowercase form.
   *
   * @param given given string to fix
   * @return fixed string
   */
  public static String simple(final String given) {
    return toNotNull(given).toLowerCase();
  }

  /**
   * Adds empty spaces from left side to given string.
   *
   * @param given given string
   * @param wantedLength wanted length
   * @return extended string
   */
  public static String padRight(@NotNull final String given,
      final int wantedLength) {
    int wantedCount = wantedLength - given.length();
    return wantedLength < 0 || wantedCount < 0
        ? given
        : String.format("%s%s",
            given,
            " ".repeat(wantedCount)
        );
  }

  /**
   * Adds empty spaces from left side to given string.
   *
   * @param given given string
   * @param wantedLength wanted length
   * @return extended string
   */
  public static String padLeft(@NotNull final String given,
      final int wantedLength) {
    int wantedCount = wantedLength - given.length();
    return wantedLength < 0 || wantedCount < 0
        ? given
        : String.format("%s%s",
            " ".repeat(wantedCount),
            given
        );
  }

  /**
   * Cuts wanted number of chars from the end of string.
   *
   * @param given given string
   * @param count number of characters to remove
   * @return cut-off string
   */
  public static String cutLast(@NotNull final String given, int count) {
    int finalCount = given.length() - count;
    return finalCount >= 0
        ? given.substring(0, finalCount)
        : "";
  }
}
