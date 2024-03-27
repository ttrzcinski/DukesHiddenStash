package org.ttrzcinski.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Parses other classes.
 */
public class ClassParser {

  /**
   * Creates new instance of pointed class.
   *
   * @param classFullName pointed class
   * @return instance of that class
   */
  public Object createInstanceOfClass(String classFullName) {
    Object obj;
    try {
      obj = Class.forName(classFullName)
          .getDeclaredConstructor()
          .newInstance();
    } catch (Exception e) {
      e.printStackTrace();
      obj = null;
    }
    return obj;
  }

  /**
   * Loads class from wanted package with given class name.
   *
   * @param packageName wanted package
   * @param className given class name
   * @return class handle, if found, null otherwise
   */
  public Class<?> returnClassFile(String packageName, String className) {
    Class<?> classHandle;
    try {
      classHandle = Class.forName(
          String.format("%s.%s", packageName, className)
      );
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      classHandle = null;
    }
    return classHandle;
  }

  /**
   * Lists methods of pointed class.<br/>
   * It looks like:<br/> public static java.lang.String org.ttrzcinski.utils.StringFix.simple(java.lang.String)
   *
   * @param classFullName given class name
   */
  public void listMethods(String classFullName) {
    try {
      Class<?> thisClass = Class.forName(classFullName);
      Method[] methods = thisClass.getDeclaredMethods();

      Arrays.stream(methods)
          .map(Method::toString)
          .forEach(System.out::println);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Lists variables of the class.
   *
   * @param classFullName class full name
   */
  public void listVariables(String classFullName) {
    Field[] fields = new Field[1];
    try {
      Class<?> classTemp = Class.forName(classFullName);
      fields = classTemp.getDeclaredFields();
      fields = Class
          .forName(classFullName)
          .getFields();
    } catch (Exception e) {
      System.err.println("Couldn't list variables.");
      e.printStackTrace();
    }
    // Only, if there are some fields
    if (fields.length <= 0) {
      System.out.printf("No fields found in %s:%n", classFullName);
      return;
    }
    // Show found fields.
    System.out.printf("Fields found in %s:%n", classFullName);
    Arrays.stream(fields)
        .filter(Objects::nonNull)
        .map(Field::toString)
        .forEach(System.out::println);
  }
}
