package org.ttrzcinski.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.ttrzcinski.interfaces.ConsoleOutput;

/**
 * Assures presence of file (or directory).
 */
public class FileAssurer extends ConsoleOutput {

  /**
   * Creates new file or directory following the path, if path deosn't exist.
   *
   * @param path given path
   */
  public void assurePresenceOfRepo(String path, boolean showOutput) {
    //Check, if path has some content to process
    if (path != null && path.trim().length() > 0) {
      final File file = new File(path);
      //Check, if file exists
      if (!file.exists()) {
        if (path.contains(".")) {
          FileOutputStream fileOutputStream = null;
          try {
            fileOutputStream = new FileOutputStream(path);
            if (showOutput) {
              out("Created a file: " + path);
            }
          } catch (FileNotFoundException fnfe) {
            if (showOutput) {
              err("Couldn't create a file: " + fnfe.getMessage());
            }
            fnfe.printStackTrace();
          } finally {
            SafeClose.close(fileOutputStream);
          }
        } else {
          if (file.mkdir()) {
            if (showOutput) {
              out("Created directory: " + path);
            }
          } else {
            if (showOutput) {
              err("Couldn't create directory: " + path);
            }
          }
        }
      } else {
        if (showOutput) {
          out("There is already: " + path);
        }
      }
    } else {
      if (showOutput) {
        err("Given path has no content.");
      }
    }
  }

  /**
   * Creates new file or directory following the path, if path deosn't exist
   *
   * @param path given path
   */
  public void assurePresenceOfRepo(String path) {
    this.assurePresenceOfRepo(path, true);
  }
}
