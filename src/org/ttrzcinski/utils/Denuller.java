package org.ttrzcinski.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Enforces initialization in order to assure, that it is not null.
 */
public class Denuller {

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static Object fix(Object given) {
    return Objects.requireNonNullElseGet(given, Object::new);
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static HashMap<?, ?> fix(HashMap<?, ?> given) {
    return Objects.requireNonNullElseGet(given, HashMap::new);
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static List<?> fix(List<?> given) {
    return Objects.requireNonNullElseGet(given, ArrayList::new);
  }
}
