package org.ttrzcinski.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ttrzcinski.interfaces.ConsoleOutput;

public class FileDigger extends ConsoleOutput {

  /**
   * Extracts all the files and catalogs from given path, if path is right.
   *
   * @param path given path
   * @return files array with zero, one or more files
   */
  public File[] digFile(String path) {
    //Check the entry path
    if (new StringHasValue().hasValue(path)) {
      //if (path != null && path.trim().length() > 0) {
      //Check existence of given path
      final File file = new File(path);
      if (file.exists()) {
        File[] files = null;
        if (file.isFile()) {
          files = new File[]{new File(file.getName())};
        } else {
          out(String.format("There is a directory: %s..", file.getName()));
          //TODO LIMIT TO ONE TYPE ONLY
          final File[] listOfFiles = file.listFiles();
          final var results = new ArrayList<File>();
          Arrays.stream(listOfFiles).forEach(processed -> {
            out("with: " + processed.getName());
            final File[] foundOnes = this.digFile(processed.getName());
            if (foundOnes != null && foundOnes.length > 0) {
              results.addAll(Arrays.asList(foundOnes));
            }
          });
          files = results.toArray(new File[results.size()]);
        }
        return files;
      }
    }
    //If nothing was found yet, it means, that there is nothing to return
    return null;
  }
}
