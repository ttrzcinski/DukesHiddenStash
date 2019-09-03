package org.ttrzcinski.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AAATestGenerator {

  /**
   * Kept path to sources.
   */
  private String sourcesPath;

  /**
   * Kept package name.
   */
  private String packagePath;

  /**
   * Kept class name without extension.
   */
  private String className;

  /**
   * Utils to process classes.
   */
  private ClassParser classParser;

  /**
   * Initializes class parser, if it was not initialized.
   */
  private void initClassParser() {
    if (classParser == null) {
      classParser = new ClassParser();
    }
  }

  /**
   * Dictionary of variants based on type.
   */
  private HashMap<String, List<String>> variants = new HashMap<>();
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
  private File prepareSourceFile(String filePath) {
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
  private String preparePackageLine(final @NotNull File sourceFile) {
    className = StringFix.cutLast(sourceFile.getName(), 5);
    packagePath = sourceFile.getParent()
        .replace(sourcesPath, "")
        .replace("/", ".")
        .replace("\\", ".")
        .substring(1);
    return String.format("package %s;", packagePath);
  }

  /**
   * Should prepare list of methods from pointed class.
   *
   * @param className pointed class name
   * @return list of methods
   */
  private List<String> prepareListOfMethods(String packageName,
      String className) {
    Class<?> theClass = classParser.returnClassFile(packageName, className);
    // Check, if class exists
    List<String> methodsList = new ArrayList<>();
    if (theClass == null) {
      return methodsList;
    }
    // Process methods of the class
    String fullPackageAndClass = String.format("%s.%s.",
        packageName, className);
    try {
      List<String> list = Arrays.stream(theClass.getDeclaredMethods())
          .map(
              method -> method.toString()
                  .replace(fullPackageAndClass, "")
                  .replace("java.lang.", "")
                  .replace("java.util.", "")
          )
          .collect(Collectors.toList());
      methodsList = list;
    } catch (Throwable e) {
      System.err.println(e);
    }
    return methodsList;
  }

  /**
   * Should parse classes out of java files.
   *
   * @param directoryPath directory of classes
   * @param parentPackage parent's package
   */
  private void readClassesFrom(String directoryPath, String parentPackage) {
    // Create a File of the root directory containing the class file
    var directory = new File(directoryPath);

    try {
      URL url = directory.toURI().toURL();  // file:/c:/Projects/out/
      URL[] urls = new URL[]{url};
      // Loads classes from the directory
      ClassLoader classLoader = new URLClassLoader(urls);
      Class theClass = classLoader.loadClass(parentPackage);
      System.out.println(theClass.getDeclaredMethods().toString());
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
   * @param className class name
   * @param methodName method's name
   * @param variant unit test's variant
   * @param voidType marks, if method is void or returns a value
   * @return full body of unit test method
   */
  private String prepareMethod(String className, String methodName,
      String variant, boolean voidType) {
    // TODO CHANGE VOIDTYPE TO RETURNTYPE as ENUM OF TYPES
    // Fix passed parameters
    int firstBracket = methodName.indexOf("(");
    String justMethodName;
    String parameters;
    if (firstBracket > -1) {
      justMethodName = methodName.substring(0, firstBracket);
      parameters = methodName.substring(firstBracket + 1, methodName.indexOf(")"));
    } else {
      justMethodName = methodName;
      parameters = "";
    }
    // Prepare test method header
    var result = new ArrayList<String>();
    result.add("\n\n  @Test\n");
    result.add(
        String.format("  void %s_%s(%s) {\n", justMethodName, variant,
            parameters)
    );
    // Prepare arrange section
    result.add("    // Arrange\n");
    result.add("    ClassName testInstance = new ClassName();\n"
        .replace("ClassName", className)
    );
    result.add("    Object testObject = new Object();\n");
    if (!voidType) {
      result.add("    var expected = \"\";\n");
    }
    result.add("\n");
    // Prepare act section
    result.add("    // Act\n");
    result.add(
        String.format("    %stestInstance.%s(testObject);\n",
            (voidType ? "" : "var result = "),
            justMethodName
        )
    );
    result.add("\n");
    // Prepare assert section
    result.add("    // Assert\n");
    if (voidType) {
      result.add("    assertEquals(true, false);\n");
    } else {
      result.add("    assertEquals(expected, result);\n");
    }
    result.add("  }");
    // Convert list to one String var
    return result.stream().collect(Collectors.joining());
  }

  /**
   * Parses pointed source file and prepares template for unit test file.
   *
   * @param sourceFilePath path to source file
   * @return content of unit test file
   */
  public List<String> generate(String sourceFilePath) {
    // Initialize the class parser
    initClassParser();
    // Check entered param
    var sourceFile = prepareSourceFile(sourceFilePath);
    if (sourceFile == null) {
      return null;
    }
    // Start preparing the content
    var content = new ArrayList<String>();
    content.add(preparePackageLine(sourceFile));
    content.add("\n\nimport static org.junit.jupiter.api.Assertions.*;\n");
    content.add("import org.junit.jupiter.api.Test;\n\n");
    content.add(String.format("class %s_AAATest {\n", className));

    // TODO Prepare list of methods
    List<String> classMethods = prepareListOfMethods(packagePath, className);
    for (var classMethod : classMethods) {
      // Prepare full body of this method and variant
      var justClassMethod = classMethod.substring(classMethod.lastIndexOf(" ") + 1);
      var voidType = classMethod.contains("void");
      var methodBody = prepareMethod(className, justClassMethod, "withNull", voidType);
      content.add(methodBody);
    }

    content.add("\n}\n");
    return content;
  }

  /**
   * Sets sources directory to given.
   */
  public void setSources(String sources) {
    if (ParamCheck.isPath(sources)) {
      sourcesPath = sources;
    }
  }
}
