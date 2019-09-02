package org.ttrzcinski.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generated unit test in pointed project.
 */
public class UnitTestGenerator {

  /**
   * Path to code sources.
   */
  private List<String> srcPath;

  /**
   * Path to test catalog.
   */
  private String testPath;
  /**
   * Flag marking overriding existing files.
   */
  private boolean overrideFlag;

  /**
   * Creates new instance of unit test generator.
   */
  public UnitTestGenerator() {
    this.srcPath = new ArrayList<>();
  }

  /**
   * Reads from pointed IML file marked directories.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator fromIML(String imlPath) {
    IMLParser imlParser = new IMLParser().of(imlPath);
    if (imlParser.isReady()) {
      this.srcPath.add(imlParser.getSourcePath());
      this.testPath = imlParser.getTestPath();
    }
    return this;
  }

  /**
   * Reads from current project's IML file marked directories.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator fromCurrentProject() {
    String projectPath = System.getProperty("user.dir");
    List<String> imls = FileExt.
        listFilesOf(Path.of(projectPath), "*.iml")
        .stream()
        .map(File::getAbsolutePath)
        .collect(Collectors.toList());
    // If found, use iml file
    return imls.size() > 0 ? this.fromIML(imls.get(0)) : this;
  }

  /**
   * Adds tests directory path with given directory.
   *
   * @param directory given directory
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withSource(String directory) {
    if (ParamCheck.isPath(directory)) {
      this.srcPath.add(directory);
    }
    return this;
  }

  /**
   * Adds test path with given directory.
   *
   * @param directory given directory
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withTests(String directory) {
    if (ParamCheck.isPath(directory)) {
      this.testPath = directory;
    }
    return this;
  }

  /**
   * Marks, that old unit test case classes will be overridden.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withOverride() {
    this.overrideFlag = true;
    return this;
  }

  /**
   * Marks, that old unit test case classes will be stay untouched.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withMissing() {
    this.overrideFlag = false;
    return this;
  }

  /**
   * Checks, if mandatory parameters are set.
   *
   * @return true means all needed parameters are set, false otherwise
   */
  public final boolean validateParameters() {
    // Check, if source directory is set and test directory is set
    return this.srcPath != null && this.testPath != null;
  }

  /**
   * Generates unit test cases after prior setting params.
   */
  public final void generate() {
    boolean debug = true;
    System.out.println("Starting to generate unit tests..");
    var sourcePaths = new HashMap<String, String>();
    List<String> innerFiles = FilesExt.allFileNamesOf(this.srcPath.get(0));
    for (String innerFile : innerFiles) {
      String preparedTestPath = innerFile;
      for (String src : this.srcPath) {
        if (innerFile.contains(src)) {
          preparedTestPath = innerFile.replace(src, testPath);
          break;
        }
      }
      sourcePaths.put(innerFile, preparedTestPath);
    }
    // Generate unit test classes one by one
    for (Entry<String, String> change : sourcePaths.entrySet()) {
      // If this is a directory
      if (!change.getValue().endsWith(".java")) {
        // Check, if directory exists
        Path directory = Path.of(change.getValue());
        if (!directory.toFile().exists()) {
          FileExt.makeDirectory(directory);
          System.out.printf("Directory %s existence: %s%n", directory.toFile().getPath(), directory.toFile().exists());
        }
      }
      // Prepare unit test file path
      //System.out.println(change.getValue());
      /*fullFilePath = fullFilePath.replaceFirst("src", "test");
      // If unit test file already exists, omit it
      final File tempFile = new File(fullFilePath);
      // Prepare path
      final Path path = Path.of(fullFilePath);
      //try {
        // Remove file, if overriding
        if (this.overrideFlag) {
          System.out.printf("Deleting file %s%n", path);
          if (!debug) {
            ;//Files.deleteIfExists(path);
          }
        }
        // Create field
        if (this.overrideFlag || !tempFile.exists()) {
          System.out.printf("Creating file %s%n", path);
          if (!debug) {
            ;//Files.createFile(path);
          }
        }*/
      //} catch (IOException e) {
      //  e.printStackTrace();
      //}
    }
    System.out.println("..finished making the unit test files.");
  }

  /**
   * Shows on console all set inside variables.
   */
  public void console() {
    System.out.println("Source directories:");
    for (String directory : this.srcPath) {
      System.out.println(directory);
    }
    System.out.println(this.testPath);
  }

}
