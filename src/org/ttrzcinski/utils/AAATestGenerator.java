package org.ttrzcinski.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AAATestGenerator {

  /**
   * Kept path to sources.
   */
  private static String sourcesPath;

  /**
   * Returns handle to file, if given path points to source file.
   *
   * @param filePath pointed file path
   * @return handle to file, if found, null otherwise
   */
  private static File prepareSourceFile(String filePath) {
    if (!FileExt.exists(filePath)) {
      System.err.printf("Missing file %s.%n", filePath);
      return null;
    }
    // Check name
    if (!filePath.endsWith(".java")) {
      System.err.printf("Name of %s doesn't end with java.%n", filePath);
      return null;
    }
    // Process content of the file
    File sourceFile = new File(filePath);
    if (sourceFile.isDirectory()) {
      System.err.printf("Pointed %s is a directory.%n", filePath);
      return null;
    }
    return sourceFile;
  }

  /**
   * Prepares package line for unit test file.
   *
   * @param sourceFile given file
   * @return package line
   */
  private static String preparePackageLine(final @NotNull File sourceFile) {
    return String.format("package %s;", sourceFile.getParent()
        .replace(sourcesPath, "")
        .replace("/", ".")
        .replace("\\", ".")
        .substring(1));
  }

  /**
   * Prepares line with class opening.
   *
   * @param sourceFile processed source file
   * @return class line
   */
  private static String prepareClassLine(final @NotNull File sourceFile) {
    return String.format("class %s_AAATest {",
        StringFix.cutLast(sourceFile.getName(),
            5)
    );
  }

  /**
   * Should prepare list of methods from pointed class.
   *
   * @param className pointed class name
   * @return list of methods
   */
  private static List<String> prepareListOfMethods(String className) {
    List<String> methodsList = new ArrayList<>();
    try {
      // TODO Take the right one to process
      Class theClass = AAATestGenerator.class;
      methodsList = Arrays
          .stream(theClass.getDeclaredMethods())
          .map(Method::toString)
          .collect(Collectors.toList());
    } catch (Throwable e) {
      System.err.println(e);
    }
    return methodsList;
  }

  /**
   * Parses pointed source file and prepares template for unit test file.
   *
   * @param sourceFilePath path to source file
   * @return content of unit test file
   */
  public static List<String> generate(String sourceFilePath) {
    // Check entered param
    File sourceFile = prepareSourceFile(sourceFilePath);
    if (sourceFile == null) {
      return null;
    }
    // Start preparing the content
    List<String> content = new ArrayList<>();
    content.add(preparePackageLine(sourceFile));
    content.add("\n\n");
    content.add("import static org.junit.jupiter.api.Assertions.*;\n");
    content.add("import org.junit.jupiter.api.Test;\n");
    content.add("\n");
    content.add(prepareClassLine(sourceFile));
    content.add("\n");

    // TODO Prepare list of methods

    // TODO Prepare list of coverage unit tests

    content.add("}");
    return content;
  }

  /**
   * Sets sources directory to given.
   */
  public static void setSources(String sources) {
    if (ParamCheck.isPath(sources)) {
      sourcesPath = sources;
    }
  }
}
