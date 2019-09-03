package org.ttrzcinski.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AAATestGenerator {

  /**
   * Kept path to sources.
   */
  private static String sourcesPath;

  /**
   * Kept class name without extension.
   */
  private static String className;

  /**
   * Dictionary of variants based on type.
   */
  private static HashMap<String, List<String>> variants = new HashMap<>();
  {
    variants.put("Object", Arrays.asList(new String[]{"null", "Object"}));
    variants.put("bool", Arrays.asList(new String[]{"true", "false"}));
    variants.put("int", Arrays.asList(new String[]{"zero", "one", "negative", "maxInt", "minInt"}));
    variants.put("String",
        Arrays.asList(new String[]{"null",
            "empty", "emptyToTrim",
            "some", "someToTrim",
            "justDigits", "justLetters", "justSpecialChars"
        })
    );

  }

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
    var sourceFile = new File(filePath);
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
    className = StringFix.cutLast(sourceFile.getName(),5 );
    return String.format("class %s_AAATest {", className);
  }
  
  /**
   * Should parse classes out of java files.
   *
   * @param directoryPath directory of classes
   * @param parentPackage parent's package
   */
  private static void readClassesFromDirectory(String directoryPath,
      String parentPackage) {
    // Create a File object on the root of the directory containing the class file
    File directory = new File(directoryPath);

    try {
      URL url = directory.toURI().toURL();  // file:/c:/Projects/out/
      URL[] urls = new URL[]{url};
      // Loads classes from the directory
      ClassLoader classLoader = new URLClassLoader(urls);
      Class theClass = classLoader.loadClass(parentPackage);
    } catch (MalformedURLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
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
   * Prepares a single unit test variant of wanted method.
   *
   * @param methodName method's name
   * @param variant unit test's variant
   * @return full body of unit test method
   */
  private static String prepareMethod(String methodName, String variant) {
    var result = new ArrayList<String>();

    result.add("  @Test\n");
    result.add(String.format("  void %s_%s() {\n", methodName, variant));

    result.add("    // Arrange\n");
    result.add("    Object testObject = new Object();\n");
    result.add("    var expected = \"\";\n");
    result.add("\n");

    result.add("    // Act\n");
    result.add(String.format("    var result = %s.%s(newMap);\n",
        className, methodName)
    );
    result.add("\n");

    result.add("    // Assert\n");
    result.add("    assertEquals(expected, result);\n");
    result.add("  }");

    return result.stream().collect(Collectors.joining());
  }

  /**
   * Parses pointed source file and prepares template for unit test file.
   *
   * @param sourceFilePath path to source file
   * @return content of unit test file
   */
  public static List<String> generate(String sourceFilePath) {
    // Check entered param
    var sourceFile = prepareSourceFile(sourceFilePath);
    if (sourceFile == null) {
      return null;
    }
    // Start preparing the content
    var content = new ArrayList<String>();
    content.add(preparePackageLine(sourceFile));
    content.add("\n\n");
    content.add("import static org.junit.jupiter.api.Assertions.*;\n");
    content.add("import org.junit.jupiter.api.Test;\n");
    content.add("\n");
    content.add(prepareClassLine(sourceFile));
    content.add("\n");

    // TODO Prepare list of methods

    // TODO Prepare list of coverage unit tests

    // Prepare full body of this method and variant
    var methodBody = prepareMethod("method1", "withNull");

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
