package org.ttrzcinski.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


/**
 * File (Util Methods) Extension.
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
    return Arrays.asList(path.toFile().listFiles(file -> file.exists()));
  }

  /**
   * Lists subdirectories from path.
   *
   * @param path given path
   * @return list of subdirectories
   */
  public static List<File> listSubdirectoriesOf(Path path) {
    return Arrays.asList(path.toFile().listFiles(File::isDirectory));
  }

  /**
   * Lists of only files from path.
   *
   * @param path given path
   * @return list of only files
   */
  public static List<File> listOnlyFilesOf(Path path) {
    return Arrays.asList(path.toFile().listFiles(File::isFile));
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
      files = path.toFile().listFiles(x -> x.exists());
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
    return Arrays.stream(files).collect(Collectors.toList());
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
        result = false;
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
    return new File(path.toString()).exists();
  }

  /**
   * Checks, if file exists under given path.
   *
   * @param file given path as file
   * @return true means it exists, false otherwise
   */
  public static boolean exists(File file) {
    return file.exists();
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
    return file.exists() && file.delete() ? file : null;
  }
}
