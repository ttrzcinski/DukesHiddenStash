package org.ttrzcinski.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Enforces initialization in order to assure, that it is not null.
 *
 * Created 31.08.2019 12:06 as a part of project DukesHiddenStash
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version %I% from %G%
 * @since 1.12
 */
public class Denuller {

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static Object fix(Object given) {

    return given == null ? new Object() : given;
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static HashMap<?, ?> fix(HashMap<?, ?> given) {

    return given == null ? new HashMap<>() : given;
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if need or old value, if it was already initialized
   */
  public static List<?> fix(List<?> given) {

    return given == null ? new ArrayList<>() : given;
  }
}
