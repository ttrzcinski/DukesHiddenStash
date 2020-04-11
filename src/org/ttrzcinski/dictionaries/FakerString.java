package org.ttrzcinski.dictionaries;

/**
 * Set of default values to use in unit tests with string parameters.
 */
public final class FakerString {

  public static final String NULL = null;

  public static final String EMPTY = "";

  public static final String EMPTY_TO_TRIM = "   ";

  public static final String SOME = "some";

  public static final String SOME_TO_RIGHT_TRIM = "some  ";

  public static final String SOME_TO_LEFT_TRIM = "  some";

  public static final String SOME_TO_TRIM = "   some  ";

  public static final String OTHER = "other";

  public static final String OTHER_TO_TRIM = "   other  ";

  /**
   * Hidden constructor.
   */
  private FakerString() {
  }
}
