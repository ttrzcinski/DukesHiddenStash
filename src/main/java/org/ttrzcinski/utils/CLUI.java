package org.ttrzcinski.utils;

import org.ttrzcinski.utils.MultiOut.Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command Line User Interface.
 */
public class CLUI {

  /**
   * List of known arguments usable in command line.
   */
  private List<EnvArgument> known;

  /**
   * List of passed arguments for further processing or history check.
   */
  private List<String> passed;

  /**
   * Kept set of outputs.
   */
  private MultiOut multiOut;

  /**
   * Initializes new instance of C.L.U.I. with no passed arguments.
   */
  public CLUI() {
    // Prepare known arguments
    this.initKnownArguments();
    // Prepare used outputs
    this.initOutputs();
    // Prepare empty passed parameters
    this.passed = new ArrayList<>();
  }

  /**
   * Initializes new instance of C.L.U.I. with passed arguments.
   *
   * @param args passed arguments
   */
  public CLUI(String[] args) {
    this();
    // Use passed arguments
    if (this.passed == null || this.passed.isEmpty()) {
      this.passed = Arrays.asList(args);
    }
  }

  /**
   * Initializes known arguments list, if was not initialized.
   */
  private void initKnownArguments() {
    // Prepare known arguments to compare with
    if (this.known == null) {
      this.known = prepareArguments();
    }
  }

  /**
   * Initializes used set of outputs.
   */
  private void initOutputs() {
    // Prepare multi output
    if (this.multiOut == null) {
      this.multiOut = MultiOut.getInstance();
      multiOut.put(Output.STD_OUTPUT);
    }
  }

  /**
   * Passes given phrase to console and other outputs.
   *
   * @param given given phrase
   */
  public void pass(String given) {
    this.multiOut.pass(given);
  }

  /**
   * Checks, if in passed arguments was argument from passed group.
   *
   * @param group given parameter group
   * @return true means it contains, false otherwise
   */
  public boolean checkInPassed(String group) {
    String fixedGroup = group != null ?
            group.trim().toLowerCase() :
            "";
    // Checks, if given arguments is passed

    return switch (fixedGroup) {
      case "help" -> passed.contains("-h") && passed.contains("--h")
              && passed.contains("-help") && passed.contains("--help");
      case "creators" -> passed.contains("-author") && passed.contains("--author")
              && passed.contains("-authors") && passed.contains("--authors");
      default -> passed.contains(String.format("-%s", fixedGroup))
              && passed.contains(String.format("--%s", fixedGroup));
    };
  }

  /**
   * Prepares arguments for further comparison.
   *
   * @return list of those arguments
   */
  private List<EnvArgument> prepareArguments() {
    return new ArrayList<>(Arrays.asList(
        new EnvArgument().withAcronym("h").withName("help")
            .withDescription("Present help with possible arguments to use."),
        new EnvArgument().withAcronym("a").withName("author")
            .withDescription("List of authors, who created the application."),
        new EnvArgument().withAcronym("src").withName("source")
            .withDescription("Path to src catalog within the project."),
        new EnvArgument().withAcronym("t").withName("test")
            .withDescription("Path to test catalog within the project.")));
  }

  /**
   * Shows on console arguments as help page.
   */
  public void showAsHelpLegend() {
    this.multiOut.pass("Possible arguments:");
    this.getKnown().stream()
        .map(EnvArgument::asHelp)
        .forEach(this::pass);
  }

  /**
   * Shows on console list of authors.
   */
  public void showAuthors() {
    this.pass("Authors:");
    this.pass("Initial project by <Tomasz Trzciński> at trzcinski.tomasz.1988@gmail.com");
  }

  /**
   * Returns (cloned) list of known arguments.
   *
   * @return known arguments as list
   */
  public List<EnvArgument> getKnown() {
    this.initKnownArguments();
    return new ArrayList<>(this.known);
  }

  /**
   * Shows whole set of passed arguments - for test purposes.
   *
   * @param args given argument's array
   */
  public void showWholeArgumentsArray(final String[] args) {
    final List<String> orderedArgs = new ArrayList<>();
    final List<String> unknownArgs = new ArrayList<>();
    for (String arg : args) {
      if (ParamCheck.isArgument(arg)) {
        orderedArgs.add(arg);
      } else {
        unknownArgs.add(arg);
      }
    }

    this.pass("Those were right:");
    orderedArgs.forEach(this::pass);

    this.pass("\nThose were not:");
    unknownArgs.forEach(this::pass);
  }

}
