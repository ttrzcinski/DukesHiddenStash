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
    return given == null || given.trim().length() == 0 ? "" : given.trim();
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
  public static String padRight(final String given, final int wantedLength) {
    String givenFix;
    int wantedCount = 0;
    if (!ParamCheck.isSet(given)) {
      givenFix = "";
    } else {
      givenFix = given;
      wantedCount = wantedLength - givenFix.length();
    }
    return wantedLength < 0 || wantedCount < 0
        ? givenFix
        : String.format("%s%s",
            givenFix,
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
  public static String padLeft(@NotNull final String given, final int wantedLength) {
    String givenFix;
    int wantedCount = 0;
    if (!ParamCheck.isSet(given)) {
      givenFix = "";
    } else {
      givenFix = given;
      wantedCount = wantedLength - givenFix.length();
    }
    return wantedLength < 0 || wantedCount < 0
        ? givenFix
        : String.format("%s%s",
            " ".repeat(wantedCount),
            givenFix
        );
  }

  /**
   * Cuts wanted number of characters from the end of string.
   *
   * @param given given string
   * @param endingLength number of characters to remove
   * @return cut-off string
   */
  public static String cutLast(final String given, int endingLength) {
    if (!ParamCheck.isSet(given)) {
      return given;
    }
    if (endingLength <= 0) {
      return given;
    } else if (endingLength >= given.length()) {
      return "";
    }
    return given.substring(0, given.length() - endingLength);
  }

  /**
   * Cuts wanted number of characters from beginning of string.
   *
   * @param given given string
   * @param startingLength number of characters to remove
   * @return cut-off string
   */
  public static String cutFirst(final String given, int startingLength) {
    if (!ParamCheck.isSet(given)) {
      return given;
    }
    if (startingLength <= 0) {
      return given;
    } else if (startingLength >= given.length()) {
      return "";
    }
    return given.substring(startingLength);
  }
}
