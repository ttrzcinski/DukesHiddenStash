package org.ttrzcinski.utils;


import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * Keeps methods to fix String to common standard.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
@UtilityClass
public class StringFix {

  private static final String CONCAT_PATTERN = "%s%s";

  private static final String EMPTY_SPACE = " ";

  private static final String EMPTY = "";

  /**
   * Fixed string to not-null initialized empty string, if is null.
   *
   * @param given given string to fix
   * @return fixed string
   */
  public static String toNotNull(final String given) {
    return given != null && given.trim().length() != 0 ? given.trim() : EMPTY;
  }

  /**
   * Simplifies string to trimmed and lowercase form.
   *
   * @param given given string to fix
   * @return fixed string
   */
  public static String simple(final String given) {
    return StringFix.toNotNull(given).toLowerCase();
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
      givenFix = EMPTY;
    } else {
      givenFix = given;
      wantedCount = wantedLength - givenFix.length();
    }
    return wantedLength < 0 || wantedCount < 0
        ? givenFix
        : String.format(CONCAT_PATTERN,
            givenFix,
            EMPTY_SPACE.repeat(wantedCount)
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
      givenFix = EMPTY;
    } else {
      givenFix = given;
      wantedCount = wantedLength - givenFix.length();
    }
    return wantedLength < 0 || wantedCount < 0
        ? givenFix
        : String.format(CONCAT_PATTERN,
            EMPTY_SPACE.repeat(wantedCount),
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
  public static String cutLast(final String given, final int endingLength) {
    // Check entered parameters
    if (!ParamCheck.isSet(given) || endingLength <= 0) return given;
    // Cut and return wanted part
    return (endingLength < given.length()) ? given.substring(0, given.length() - endingLength) : EMPTY;
  }

  /**
   * Cuts wanted number of characters from beginning of string.
   *
   * @param given given string
   * @param startingLength number of characters to remove
   * @return cut-off string
   */
  public static String cutFirst(final String given, final int startingLength) {
    // Check entered parameters
    if (!ParamCheck.isSet(given) | startingLength <= 0) return given;
    // Cut and return wanted part
    return (startingLength < given.length()) ? given.substring(startingLength) : EMPTY;
  }
}
