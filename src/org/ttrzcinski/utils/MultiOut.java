package org.ttrzcinski.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Multi Output - outputs string data to many different outs based on set flags.
 */
public final class MultiOut {

  /**
   * Known output types.
   */
  public enum Output {
    /**
     * Standard console output.
     */
    STD_OUTPUT,
    /**
     * Standard console error output.
     */
    STD_ERROR,
    /**
     * Standard log file to write to.
     */
    LOG_FILE,
    /**
     * Set remote log file to send to.
     */
    LOG_REMOTE,
    /**
     * Output to JSON files.
     */
    JSON_FILE
  }

  /**
   * Lock flag for lazy initialization of Multi Output instance.
   */
  private static boolean lock;

  /**
   * Kept instance of Multi Output - there's at most one.
   */
  private static MultiOut instance;

  /**
   * Kept used outputs.
   */
  private Map<Output, String> outputs;

  /**
   * Lines to pass to outputs.
   */
  private List<String> lines;

  // Constructors

  /**
   * Creates new instance of Multi Output.
   */
  private MultiOut() {
    // Assure outputs
    this.outputs = new HashMap<>();
    // Assure lines to pass
    this.lines = new ArrayList<>();
  }

  /**
   * Obtains the only instance of Multi Output.
   *
   * @return Multi Output instance
   */
  public static MultiOut getInstance() {
    if (instance == null && !lock) {
      lock = true;
      instance = new MultiOut();
      lock = false;
    }
    return instance;
  }

  /**
   * Processes given lines one by one to known outputs.
   */
  public void pass() {
    this.pass(null);
  }

  /**
   * Processes given lines one by one to known outputs.
   *
   * @param given given line to output
   */
  public void pass(final String given) {
    // Add given line, if it contains value
    if (ParamCheck.isSet(given)) {
      this.lines.add(given);
    }
    // TODO Consider: use virtual thread pool with passing handle to other threads.
      if (!this.lines.isEmpty()) {
      // Standard output
      this.lines.stream()
              .filter(_ -> this.outputs.containsKey(Output.STD_OUTPUT))
          .forEach(System.out::println);
      // Remove those lines after passing them to outputs
      this.lines.clear();
    }
  }

  /**
   * Adds (or updates) given output with additional parameters.
   *
   * @param output type of output
   */
  public void put(final Output output) {
    this.put(output, null);
  }

  /**
   * Adds (or updates) given output with additional parameters.
   *
   * @param output type of output
   * @param path marked path for output
   */
  public void put(final Output output, final String path) {
    if (output != null) {
      this.outputs.put(output, path);
    }
  }

  /**
   * Turn off usage of output, if is set.
   *
   * @param output pointed output
   */
  public void remove(final Output output) {
    this.outputs.remove(output);
  }
}
