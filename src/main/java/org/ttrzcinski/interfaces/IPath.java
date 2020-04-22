package org.ttrzcinski.interfaces;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Forces usage of common set of directory and files processing methods.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public interface IPath {

  /**
   * Returns path to mouth's set directory.
   *
   * @return path instance
   */
  Path getPath();

  /**
   * Returns list of files and subdirectories from mount's directory.
   *
   * @return list of files and subdirectories
   */
  List<File> getFiles();

  /**
   * Returns filtered list of files and subdirectories from mount's directory
   * filtered by given filter.
   *
   * @param filter given filter criteria
   * @return list of files and subdirectories
   */
  List<File> getFiles(String filter);

  /**
   * Returns list of subdirectories from mount's directory.
   *
   * @return list of subdirectories
   */
  List<File> getSubdirectories();

  /**
   * Returns filtered list of subdirectories from mount's directory
   * filtered by given filter.
   *
   * @param filter given filter criteria
   * @return list of subdirectories
   */
  List<File> getSubdirectories(String filter);

  /**
   * Creates new file in pointed location, if it doesn't exist.
   *
   * @return handle to file, if created, null otherwise
   */
  File create();

  /**
   * Removes a file.
   *
   * @return file, if removed, null otherwise
   */
  File remove();

  /**
   * Checks, if file exists in pointed location.
   *
   * @return true means it exists, false otherwise
   */
  boolean exists();
}
