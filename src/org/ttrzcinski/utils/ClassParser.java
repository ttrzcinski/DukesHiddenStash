package org.ttrzcinski.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
    Object obj = null;
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
    Class<?> classHandle = null;
    try {
      classHandle = Class.forName(String.format("%s.%s",
          packageName, className)
      );
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      classHandle = null;
    }
    return classHandle;
  }

  /**
   * Lists methods of pointed class.
   *
   * It looks like:<br/>
   * public static java.lang.String org.ttrzcinski.utils.StringFix.simple(java.lang.String)
   *
   * @param classFullName given class name
   */
  public void listMethods(String classFullName) {
    try {
      Class thisClass = Class.forName(classFullName);
      Method[] methods = thisClass.getDeclaredMethods();

      for (Method method : methods) {
        System.out.println(method.toString());
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

  /**
   * Lists variables of pointed class.
   *
   * @param classFullName given class name
   */
  public void listVariables(String classFullName) {
    Field[] fields = new Field[1];
    try {
      Class classTemp = Class.forName(classFullName);
      fields = classTemp.getClass().getDeclaredFields();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e);
    }
    //
    if (fields.length > 0) {
      System.out.printf("Fields found in %s:%n", classFullName);
      Arrays.stream(fields).map(Field::toString).forEach(System.out::println);
    } else {
      System.out.printf("No fields found in %s:%n", classFullName);
    }
  }
}
