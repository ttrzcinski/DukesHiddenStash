package org.ttrzcinski.dictionaries;

/**
 * Set of default values to use in unit tests with long parameters.
 */
public final class FakerLong {

  private static final long MIN_NEGATIVE = Long.MIN_VALUE;

  private static final long SOME_NEGATIVE = -2L;

  private static final long FIRST_NEGATIVE = -1L;

  private static final long ZERO = 0L;

  private static final long FIRST_POSITIVE = 1L;

  private static final long SOME_POSITIVE = 2L;

  private static final long MAX_NEGATIVE = Long.MAX_VALUE;

  /**
   * Hidden constructor.
   */
  private FakerLong() {
  }
}
