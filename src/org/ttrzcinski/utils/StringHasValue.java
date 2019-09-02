package org.ttrzcinski.utils;


import org.ttrzcinski.interfaces.IStringHasValue;

/**
 * Checks, if string has value.
 *
 * Created 31.08.2019 12:06 as a part of project DukesHiddenStash
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version %I% from %G%
 * @since 1.12
 */
public class StringHasValue {

  /**
   * Checks, if given string has value.
   *
   * @param given given string to check
   * @return true, if has value, false otherwise
   */
  public boolean hasValue(String given) {
    IStringHasValue newTradeChecker =
        (String str) -> str != null && str.trim().length() > 0;
    return newTradeChecker.hasValue(given);
  }
}
