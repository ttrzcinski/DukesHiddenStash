package org.ttrzcinski.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.ttrzcinski.interfaces.AbstractConsoleOutput;

public class FileDigger extends AbstractConsoleOutput {

  /**
   * Extracts all the files and catalogs from given path, if path is right.
   *
   * @param path given path
   * @return files array with zero, one or more files
   */
  public File[] digFile(String path) {
    //Check the entry path
    if (!ParamCheck.isSet(path)) {
      return null;
    }
    // Check existence of given path
    final File file = new File(path);
    if (!file.exists()) {
      // Stop, if file doesn't exist
      return null;
    }
    // If it is a file
    if (file.isFile()) {
      return new File[]{new File(file.getName())};
    }

    out(String.format("There is a directory: %s..", file.getName()));
    //TODO LIMIT TO ONE TYPE ONLY
    final File[] listOfFiles = file.listFiles();
    final var results = new ArrayList<File>();
    Arrays.stream(Objects.requireNonNull(listOfFiles))
        .forEach(processed -> {
          out("with: " + processed.getName());
          final File[] foundOnes = this.digFile(processed.getName());
          if (foundOnes != null && foundOnes.length > 0) {
            results.addAll(Arrays.asList(foundOnes));
          }
        });
    return results.toArray(new File[0]);
  }
}
