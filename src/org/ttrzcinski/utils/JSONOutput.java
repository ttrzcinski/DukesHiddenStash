package org.ttrzcinski.utils;

import org.json.JSONObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Converts given instance to JSOn string.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public class JSONOutput {

  /**
   * Converts HashMap with String and PAth to HashMap with String and String.
   *
   * @param map given HashMap with string and Path
   * @return HashMap equivalent with String and String
   */
  private Map<String, String> convertToHashMapStringString(
      final HashMap<String, Path> map
  ) {
    return (map != null && !map.isEmpty()) ? map.entrySet().stream().collect(
        Collectors.toMap(
            Map.Entry::getKey,
            e -> e.getValue().toAbsolutePath().toString()
        )) : new HashMap<>();
  }

  /**
   * Serialize given HashMap to JSON string.
   *
   * @param map given HashMap
   * @return JSON string equivalent
   */
  public static String toJSON(final Map<?, ?> map) {
    return new JSONObject(map).toString();
  }
}
