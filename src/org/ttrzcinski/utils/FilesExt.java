package org.ttrzcinski.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File processing methods wrapped for simpler use of them.
 */
public class FilesExt {

  /**
   * Lists all files within given directory.
   *
   * @param path path to the directory, f.e.: C:\\Projects
   * @return List with all files in
   */
  public final static List<File> allFilesOf(String path) {
    // Check entered param
    if (!ParamCheck.isPath(path)) {
      return new ArrayList<>();
    }
    // Prepare result list
    File directory = new File(path);
    List<File> resultList = new ArrayList<File>();
    // get all the files from a directory
    File[] fList = directory.listFiles();
    resultList.addAll(Arrays.asList(fList));
    for (File file : fList) {
      if (file.isFile()) {
        System.out.println(file.getAbsolutePath());
      } else if (file.isDirectory()) {
        resultList.addAll(allFilesOf(file.getAbsolutePath()));
      }
    }
    return resultList;
  }

  /**
   * Converts file names to files array.
   *
   * @param names file names
   * @return file array
   */
  public final static File[] asFileArray(String[] names) {
    return Arrays.stream(names)
        .map(s -> new File(s))
        .toArray(size -> new File[names.length]);
  }

  /**
   * Converts files array to absolute file paths array.
   *
   * @param files files array
   * @return array of absolute file paths
   */
  public final static String[] asFileAbsolutePaths(File[] files) {
    return Arrays.stream(files)
        .map(f -> f.getAbsolutePath())
        .toArray(size -> new String[files.length]);
  }

  /**
   * Converts files array to file paths array.
   *
   * @param files files array
   * @return array of file paths
   */
  public final static String[] asFilesPaths(File[] files) {
    return Arrays.stream(files)
        .map(f -> f.getPath())
        .toArray(size -> new String[files.length]);
  }

  /**
   * Lists all file names within given directory.
   *
   * @param path path to the directory, f.e.: C:\\Projects
   * @return List with all file names in
   */
  public final static List<String> allFileNamesOf(String path) {
    // Check entered path
    if (!ParamCheck.isPath(path)) {
      return new ArrayList<>();
    }
    // Read all files with subdirectories from pointed location
    List<File> files = allFilesOf(path);
    // Read absolute file paths from actual list of files
    String[] fileNames = asFileAbsolutePaths(files.toArray(File[]::new));
    // Convert to list
    return Arrays.asList(fileNames);
  }
}
