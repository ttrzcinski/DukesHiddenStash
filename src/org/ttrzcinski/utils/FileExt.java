package org.ttrzcinski.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


/**
 * File (Util Methods) Extension and processing methods wrapped for simpler use of them.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public class FileExt {

  /**
   * Lists file from path.
   *
   * @param path given path
   * @return list of files
   */
  public static List<File> listFilesOf(Path path) {
    // Find iml file in current project directory
    return Arrays.asList(
        Objects.requireNonNull(path.toFile().listFiles(File::exists))
    );
  }

  /**
   * Lists subdirectories from path.
   *
   * @param path given path
   * @return list of subdirectories
   */
  public static List<File> listSubdirectoriesOf(Path path) {
    return Arrays.asList(
        Objects.requireNonNull(path.toFile().listFiles(File::isDirectory))
    );
  }

  /**
   * Lists of only files from path.
   *
   * @param path given path
   * @return list of only files
   */
  public static List<File> listOnlyFilesOf(Path path) {
    return Arrays.asList(
        Objects.requireNonNull(path.toFile().listFiles(File::isFile))
    );
  }

  /**
   * Lists of files from path filtered by given filter.
   *
   * @param path given path
   * @param filter given filter
   * @return filtered list of files
   */
  public static List<File> listFilesOf(@NotNull final Path path,
      @NotNull final String filter) {
    String phrase = StringFix.simple(filter);
    File[] files;
    if (phrase.length() == 0 || phrase.equals("*.*")) {
      files = path.toFile().listFiles(File::exists);
    } else if (phrase.startsWith("*.") && phrase.length() > 2) {
      // filter by extension
      String end = phrase.substring(2);
      files = path.toFile().listFiles(x -> x.getName().endsWith(end));
    } else if (phrase.endsWith(".*") && phrase.length() > 2) {
      // filter by name starting
      String start = phrase.substring(0, phrase.length() - 2);
      files = path.toFile().listFiles(x -> x.getName().startsWith(start));
    } else {
      // contains
      files = path.toFile().listFiles(x -> x.getName().contains(phrase));
    }
    return Arrays
        .stream(Objects.requireNonNull(files))
        .collect(Collectors.toList());
  }

  /**
   * Lists of subdirectories from path filtered by given filter.
   *
   * @param path given path
   * @param filter given filter
   * @return filtered list of subdirectories
   */
  public static List<File> listSubdirectoriesOf(@NotNull final Path path,
      @NotNull final String filter) {
    return listFilesOf(path, filter).stream().filter(File::isDirectory)
        .collect(Collectors.toList());
  }

  /**
   * Lists all files within given directory.
   *
   * @param path path to the directory, f.e.: C:\\Projects
   * @return List with all files in
   */
  public static List<File> allFilesOf(String path) {
    // Check entered param
    if (!ParamCheck.isPath(path)) {
      return new ArrayList<>();
    }
    // Prepare result list
    File directory = new File(path);
    // get all the files from a directory
    File[] fList = directory.listFiles();
    List<File> resultList = new ArrayList<>(
        Arrays.asList(Objects.requireNonNull(fList))
    );
    for (File file : fList) {
      if (file.isFile()) {
        resultList.add(file);
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
  public static File[] asFileArray(String[] names) {
    return Arrays.stream(names)
        .map(File::new)
        .toArray(size -> new File[names.length]);
  }

  /**
   * Converts files array to absolute file paths array.
   *
   * @param files files array
   * @return array of absolute file paths
   */
  public static String[] asFileAbsolutePaths(File[] files) {
    return Arrays.stream(files)
        .map(File::getAbsolutePath)
        .toArray(size -> new String[files.length]);
  }

  /**
   * Converts files array to file paths array.
   *
   * @param files files array
   * @return array of file paths
   */
  public static String[] asFilesPaths(File[] files) {
    return Arrays.stream(files)
        .map(File::getPath)
        .toArray(size -> new String[files.length]);
  }

  /**
   * Lists all file names within given directory.
   *
   * @param path path to the directory, f.e.: C:\\Projects
   * @return List with all file names in
   */
  public static List<String> allFileNamesOf(String path) {
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

  /**
   * Creates file on pointed path.
   *
   * @param path pointed path
   * @return handle to the file
   */
  public static File create(Path path) {
    // Checked entered parameters
    if (!ParamCheck.isSet(path)) {
      System.err.println("Failed as creating file: null name provided.");
      return null;
    }
    // Create that file
    var file = new File(path.toString());
    var result = false;
    if (!file.exists()) {
      try {
        result = file.createNewFile();
      } catch (IOException ioe) {
        file = null;
      }
    }
    return result ? file : null;
  }

  /**
   * Creates file on pointed path with given content.
   *
   * @param path pointed path
   * @param content given content
   * @return handle to the file
   */
  public static File create(Path path, String content) {
    // Check entered parameter
    if (!ParamCheck.isSet(content)) {
      return create(path);
    }
    // Process file further
    var file = new File(path.toString());
    var result = false;
    if (!file.exists()) {
      try {
        result = file.createNewFile();
        Files.writeString(path, content, StandardOpenOption.APPEND);
      } catch (IOException ioe) {
        result = false;
        file = null;
      }
    }
    return result ? file : null;
  }

  /**
   * Creates directory on pointed path.
   *
   * @param path given path
   * @return handle to the directory
   */
  public static File makeDirectory(Path path) {
    var file = new File(path.toString());
    var result = false;
    if (!file.exists()) {
      result = file.mkdir();
    }
    return result ? file : null;
  }

  /**
   * Creates all directories from given list.
   *
   * @param directories given list of directories
   * @return handle to created directories
   */
  public static File[] makeDirectories(List<String> directories) {

    File[] result = new File[directories.size()];
    // If this is a directory
    int bound = directories.size();
    for (int i = 0; i < bound; i++) {
      Path directoryPath = Path.of(directories.get(i));
      // Check, if directory exists
      result[i] = !Files.exists(directoryPath) ?
          makeDirectory(directoryPath) :
          directoryPath.toFile();
    }
    return result;
  }

  /**
   * Passed handle to the file.
   *
   * @param keptPath given path
   * @return file, if path is right, null otherwise
   */
  public static File read(@NotNull final Path keptPath) {

    return new File(keptPath.toAbsolutePath().toString());
  }

  /**
   * Checks, if file exists under given path.
   *
   * @param path given path
   * @return true means it exists, false otherwise
   */
  public static boolean exists(Path path) {

    return path != null && exists(path.toFile());
  }

  /**
   * Checks, if file exists under given path.
   *
   * @param path given path
   * @return true means it exists, false otherwise
   */
  public static boolean exists(String path) {

    return ParamCheck.isSet(path) && exists(Path.of(path));
  }

  /**
   * Checks, if file exists under given path.
   *
   * @param file given path as file
   * @return true means it exists, false otherwise
   */
  public static boolean exists(File file) {

    return file != null && file.exists();
  }

  /**
   * Removes file with the path.
   *
   * @param path given path
   * @return removed file
   */
  public static File remove(Path path) {
    return remove(new File(path.toString()));
  }

  /**
   * Removes file with the path.
   *
   * @param file given path as file
   * @return removed file
   */
  public static File remove(File file) {
    return exists(file) && file.delete() ? file : null;
  }


}
