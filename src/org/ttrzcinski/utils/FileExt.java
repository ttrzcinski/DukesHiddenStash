package org.ttrzcinski.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    var files = new ArrayList<File>();
    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
      for (Path dirPath : directoryStream) {
        files.add(new File(dirPath.toString()));
      }
    } catch (IOException ex) {
      files = null;
    }
    return files;
  }

  /**
   * Lists subdirectories from path.
   *
   * @param path given path
   * @return list of subdirectories
   */
  public static List<File> listSubdirectoriesOf(Path path) {
    List<File> subdirectories = new ArrayList<>();
    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
      for (Path dirPath : directoryStream) {
        var file = new File(dirPath.toString());
          if (file.isDirectory()) {
              subdirectories.add(file);
          }
      }
    } catch (IOException ex) {
      subdirectories = null;
    }
    return subdirectories;
  }

  /**
   * Lists of only files from path.
   *
   * @param path given path
   * @return list of only files
   */
  public static List<File> listOnlyFilesOf(Path path) {
    List<File> onlyFiles = new ArrayList<>();
    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
      for (Path dirPath : directoryStream) {
        var file = new File(dirPath.toString());
          if (file.isFile()) {
              onlyFiles.add(file);
          }
      }
    } catch (IOException ex) {
      onlyFiles = null;
    }
    return onlyFiles;
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
    String simpleFilter = StringFix.simple(filter);
    List<File> results;
    if (simpleFilter.length() == 0 || simpleFilter.equals("*.*")) {
      // no filter given or given for all
      results = listFilesOf(path);
    } else if (simpleFilter.startsWith("*.")
        && simpleFilter.length() > 2) {
      // filter by extension
      String ending = simpleFilter.substring(2);
      results = listFilesOf(path).stream()
          .filter(file -> file.getName().endsWith(ending))
          .collect(Collectors.toList());
    } else if (simpleFilter.endsWith(".*")
        && simpleFilter.length() > 2) {
      // filter by name starting
      String beginning = simpleFilter.
          substring(0, simpleFilter.length() - 2);
      results = listFilesOf(path).stream()
          .filter(file -> file.getName().startsWith(beginning))
          .collect(Collectors.toList());
    } else {
      // contains
      results = listFilesOf(path).stream()
          .filter(file -> file.getName().contains(simpleFilter))
          .collect(Collectors.toList());
    }
    return results;
  }

  /**
   * Lists of subdirectories from path filtered by given filter.
   *
   * @param path given path
   * @param filter given filter
   * @return filtered list of subdirectories
   */
  public static List<File> listSubdirectoriesOf(final Path path,
      final String filter) {
    if (!ParamCheck.isSet(filter)) {
      return listSubdirectoriesOf(path);
    }
    String simpleFilter = StringFix.simple(filter);

    final List<File> results;
    if (simpleFilter.equals("*.*")) {
      // no filter at all
      results = listSubdirectoriesOf(path);
    } else if (simpleFilter.startsWith("*.")
        && simpleFilter.length() > 2) {
      // filter by extension
      String ending = simpleFilter.substring(2);
      results = listSubdirectoriesOf(path).stream()
          .filter(file -> file.getName().endsWith(ending))
          .collect(Collectors.toList());
    } else if (simpleFilter.endsWith(".*") && simpleFilter.length() > 2) {
      // filter by name starting
      String beginning = simpleFilter
          .substring(0, simpleFilter.length() - 2);
      results = listSubdirectoriesOf(path).stream()
          .filter(file -> file.getName().startsWith(beginning))
          .collect(Collectors.toList());
    } else {
      // contains
      results = listSubdirectoriesOf(path).stream()
          .filter(file -> file.getName().contains(simpleFilter))
          .collect(Collectors.toList());
    }
    return results;
  }

  /**
   * Creates file on pointed path.
   *
   * @param path given path
   * @return handle to the file
   */
  public static File create(Path path) {
    var file = new File(path.toString());
    var result = false;
    if (!file.exists()) {
      try {
        result = file.createNewFile();
      } catch (IOException e) {
        result = false;
        file = null;
      }
    }
    return result ? file : null;
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
