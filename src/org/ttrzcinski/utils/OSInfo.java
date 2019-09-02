package org.ttrzcinski.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Paths;

/**
 * Passed information about OS.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 */
public final class OSInfo {

  /**
   * Hidden constructor - there is not point to initialize an instance.
   */
  private OSInfo() {
  }

  /**
   * Checks directory by checking current relative path. "\" - Windows-based "/" - *nux based
   *
   * @return "win', if windows, "nix" otherwise
   */
  public static String checkDirectoryBySystem() {
    if (Paths.get("").toAbsolutePath().toString()
        .contains("\\")) {
      return "win";
    } else {
      return "nix";
    }
  }

  /**
   * Checks, if current OS is Windows-based.
   *
   * @return true means it's Windows
   */
  public static boolean isWindows() {
    return System.getProperty("os.name").contains("Windows");
  }

  /**
   * Checks, if current OS is *nix-based.
   *
   * @return true means
   */
  public static boolean isNix() {
    return !isWindows();
  }

  /**
   * Reads current screen resolution.
   *
   * @return screen size as dimension
   */
  public static Dimension readResolution() {
    return Toolkit.getDefaultToolkit().getScreenSize();
  }

  /**
   * Fixed direction of slashes in given path to current OS.
   *
   * @param path given path
   * @return fixed path
   */
  public static String fixPath(String path) {
    // Check given parameter
    if (!ParamCheck.isSet(path)) {
      return path;
    }
    // Process given parameter
    return isWindows()
        ? path.replaceAll("/", "\\\\")
        : path.replaceAll("\\\\", "/");
  }
}
