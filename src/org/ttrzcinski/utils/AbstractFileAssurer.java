package org.ttrzcinski.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.ttrzcinski.interfaces.AbstractConsoleOutput;

/**
 * Assures presence of file (or directory).
 */
public abstract class AbstractFileAssurer extends AbstractConsoleOutput {

  /**
   * Creates new file or directory following the path, if path deosn't exist.
   *
   * @param path given path
   */
  public final void assurePresenceOfRepo(
      final String path, final boolean showOutput
  ) {
    // Check, if path has some content to process
    if (!ParamCheck.isSet(path)) {
      conditionalError(showOutput, "Given path has no content.", "");
      return;
    }
    final File file = new File(path);
    // Check, if file exists
    if (file.exists()) {
      conditionalOutput(showOutput, "There is already: ", path);
      return;
    }
    // Check, what type of resource is that
    if (!path.contains(".")) {
      // Path is to catalog
      if (file.mkdir()) {
        conditionalOutput(showOutput, "Created directory: ", path);
      } else {
        conditionalError(showOutput, "Couldn't create directory: ", path);
      }
    } else {
      // Path is to file
      FileOutputStream fileOutputStream = null;
      try {
        fileOutputStream = new FileOutputStream(path);
        conditionalOutput(showOutput, "Created a file: ", path);
      } catch (FileNotFoundException fnfe) {
        conditionalError(showOutput, "Couldn't create a file: ", fnfe.getMessage());
        fnfe.printStackTrace();
      } finally {
        SafeClose.close(fileOutputStream);
      }
    }
  }

  /**
   * Creates new file or directory following the path, if path doesn't exist.
   *
   * @param path given path
   */
  public final void assurePresenceOfRepo(final String path) {

    this.assurePresenceOfRepo(path, true);
  }
}
