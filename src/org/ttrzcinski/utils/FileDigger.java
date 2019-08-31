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
      File file = new File(path);
      if (file.exists()) {
        File[] files = null;
        if (file.isFile()) {
          files = new File[]{new File(file.getName())};
        } else {
          out("There is a directory: " + file.getName() + "..");
          //TODO LIMIT TO ONE TYPE ONLY
          File listOfFiles[] = file.listFiles();
          List<File> results = new ArrayList<File>();
          for (File processedfile : listOfFiles) {
            out("with: " + processedfile.getName());
            File[] foundone = this.digFile(processedfile.getName());
            if (foundone != null && foundone.length > 0) {
              results.addAll(Arrays.asList(foundone));
            }
          }
          files = results.toArray(new File[results.size()]);
        }
        return files;
      }
    }
    //If nothing was found yet, it means, that there is nothing to return
    return null;
  }
}
