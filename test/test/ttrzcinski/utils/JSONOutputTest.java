package test.ttrzcinski.utils;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.JSONOutput;

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
}