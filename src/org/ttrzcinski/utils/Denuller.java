package org.ttrzcinski.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Enforces initialization in order to assure, that it is not null.<br/>
 * Created 31.08.2019 12:06 as a part of project DukesHiddenStash
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version %I% from %G%
 * @since 1.12
 */
@UtilityClass
public class Denuller {

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if You need or old value, if it was already initialized
   */
  public static Object fix(final Object given) {

    return given == null ? new Object() : given;
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if You need or old value, if it was already initialized
   */
  public static HashMap<?, ?> fix(final HashMap<?, ?> given) {

    return given == null ? new HashMap<>() : given;
  }

  /**
   * Initializes object, if given object is null.
   *
   * @param given given object
   * @return initialized object, if You need or old value, if it was already initialized
   */
  public static List<?> fix(final List<?> given) {

    return given == null ? new ArrayList<>() : given;
  }
}
