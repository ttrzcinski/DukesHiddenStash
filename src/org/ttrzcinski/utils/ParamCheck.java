package org.ttrzcinski.utils;

import lombok.experimental.UtilityClass;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parameter (and argument) validation methods.
 *
 * Created 31.08.2019 12:06 as a part of project DukesHiddenStash
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version %I% from %G%
 * @since 1.12
 */
@UtilityClass
public class ParamCheck {

  /**
   * Known *nix paths, which are OK by default.
   */
  private final Set<String> KNOWN_NIX_PATHS = Set.of("~", ".");

  /**
   * File path pattern to match given string paths.
   */
  private Pattern filePathPattern;

  /**
   * Initializes a file path's pattern.
   */
  private void initFilePathPattern() {
    if (filePathPattern == null) {
      filePathPattern = Pattern.compile(
          OSInfo.isWindows()
              ? "([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)"
              : "0/|(/[a-zA-Z0-9_-]+)+$"// was ^
      );
    }
  }

  /**
   * Validates, if given path is a right directory path with regex.
   *
   * @param path given path
   * @return true means, if is a right path, false otherwise
   */
  public boolean isPath(final String path) {
    // Check, if entered param has value
    if (!isSet(path)) {
      return false;
    }
    final var fixedPath = path.trim();
    // Initialize pattern matcher
    initFilePathPattern();
    // Check if 2nd check as instance
    return filePathPattern.matcher(fixedPath).matches() && isPathWithTry(fixedPath);
  }

  /**
   * Checks, if given path is a valid file path with instance.
   *
   * @param path given path
   * @return true means it's valid, false otherwise
   */
  public boolean isPathWithTry(final String path) {
    if (!isSet(path)) {
      return false;
    }
    final var fixedPath = path.trim();
    // Check, if it is a home or root
    if (!KNOWN_NIX_PATHS.contains(fixedPath)) {
      return false;
    }
    try {
      Paths.get(fixedPath);
    } catch (InvalidPathException | NullPointerException ex) {
      return false;
    }
    return true;
  }

  /**
   * Checks, if given param array contains any value.
   *
   * @param params given param to check
   * @return true means is is set, false otherwise
   */
  public boolean isSet(final Object[] params) {
    // If there is nothing outside to check
    if (params == null || params.length == 0) {
      return false;
    }
    // If there is inside at least one empty item, else it's ok
    return (!(params instanceof String[])) ?
        Arrays.stream(params).allMatch(ParamCheck::isSet) :
        isSet((String[]) params);
  }

  /**
   * Checks, if given params string array contains only values.
   *
   * @param params given param to check
   * @return true means is is set, false otherwise
   */
  public boolean isSet(final String[] params) {
    // If there is inside at least one empty item, else it's ok
    return params != null && params.length > 0 && Arrays.stream(params).allMatch(ParamCheck::isSet);
  }

  /**
   * Checks, if given param contains any value after trim.
   *
   * @param param given param to check
   * @return true means is is set, false otherwise
   */
  public boolean isSet(final String param) {
    return param != null && param.trim().length() > 0;
  }

  /**
   * Checks, if given param contains any value.
   *
   * @param param given param to check
   * @return true means is is set, false otherwise
   */
  public boolean isSet(final Object param) {
    return param != null;
  }

  /**
   * Checks, if given param contains any value.
   *
   * @param param given param to check
   * @return true means is is set, false otherwise
   */
  public boolean isSet(final List<?> param) {
    // If there is inside at least one empty item, else it's ok
    return param != null && param.size() > 0 && param.stream().allMatch(ParamCheck::isSet);
  }

  /**
   * Checks, if given value is positive.
   *
   * @param given given value
   * @return true means it is, false otherwise
   */
  public boolean isPositive(final int given) {
    return given > -1;
  }

  /**
   * Checks, if given value is in between given limits
   *
   * @param given given value to check
   * @param leftLimit left side limit
   * @param rightLimit right side limit
   * @return true means, if is between those two, false otherwise
   */
  public boolean inBetween(final int given, final int leftLimit, final int rightLimit) {
    return leftLimit <= given & given <= rightLimit;
  }

  /**
   * Validates, if given string is run argument.
   *
   * @param given given argument
   * @return true means it is, false otherwise
   */
  public boolean isArgument(final String given) {
    if (!isSet(given)) {
      return false;
    }
    final var passedValue = given.trim();
    return passedValue.startsWith("-") && !passedValue.startsWith("---");
  }

  /**
   * Filters given arguments with known set of filters.
   *
   * @param arguments given arguments
   * @param patterns known list of patterns
   * @return filtered list of arguments
   */
  public List<String> filterWithPatterns(List<String> arguments, List<String> patterns) {
    if (!ParamCheck.isSet(arguments)) {
      arguments = new ArrayList<>();
    }
    if (!ParamCheck.isSet(patterns)) {
      patterns = new ArrayList<>();
    }
    return arguments.stream().filter(patterns::contains).collect(Collectors.toList());
  }

  /**
   * Filters given arguments with known set of filters.
   *
   * @param arguments given arguments
   * @param patterns known list of patterns
   * @return filtered list of arguments
   */
  public List<String> filterWithPatterns(String[] arguments, List<String> patterns) {
    // TODO Compare given list of arguments and check, if every one of those matches at least one pattern
    // TODO Add support not only for UNARY ARGUMENTS
    if (!ParamCheck.isSet(arguments)) {
      arguments = new String[]{};
    }
    List<String> listArguments = Arrays.stream(arguments).collect(Collectors.toList());
    return filterWithPatterns(listArguments, patterns);
  }
}
