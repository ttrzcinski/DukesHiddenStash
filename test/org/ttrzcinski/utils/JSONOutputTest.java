package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.JSONOutput;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONOutputTest {

  @Test
  void toJSON_null() {
    // Arrange
    Map<String, String> newMap = null;
    var expected = "{}";

    // Act
    var result = JSONOutput.toJSON(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toJSON_noElements() {
    // Arrange
    Map<String, String> newMap = new HashMap<>();
    var expected = "{}";

    // Act
    var result = JSONOutput.toJSON(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toJSON_oneElement() {
    // Arrange
    Map<String, String> newMap = new HashMap<>();
    newMap.put("the_home", "~");
    var expected = String.format("{\"the_home\":\"%s\"}", "~")
        .replace("\\", "\\\\");

    // Act
    var result = JSONOutput.toJSON(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void toJSON_twoElements() {
    // Arrange
    Map<String, String> newMap = new HashMap<>();
    newMap.put("home_1", "~");
    newMap.put("home_2", "peak");
    var expected = String.format("{\"%s\":\"%s\",\"%s\":\"%s\"}",
        "home_2", "peak", "home_1", "~")
        .replace("\\", "\\\\");

    // Act
    var result = JSONOutput.toJSON(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void convertToHashMapStringString_null() {
    // Arrange
    Map<String,Path> newMap = null;
    Map expected = new HashMap<>();

    // Act
    var result = JSONOutput.convertToHashMapStringString(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void convertToHashMapStringString_empty() {
    // Arrange
    Map<String,Path> newMap = new HashMap<>();
    Map expected = new HashMap<>();

    // Act
    var result = JSONOutput.convertToHashMapStringString(newMap);

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void convertToHashMapStringString_twoElements() {
    // Arrange
    Map<String,Path> newMap = new HashMap<>();
    newMap.put("home_1", Path.of("~"));
    newMap.put("home_2", Path.of("peak"));
    var expected = Arrays.asList("home_2", "peak", "home_1", "~");

    // Act
    var result = JSONOutput.convertToHashMapStringString(newMap);

    // Assert
    assertTrue(expected.stream().allMatch(x -> result.toString().contains(x)));
  }
}
