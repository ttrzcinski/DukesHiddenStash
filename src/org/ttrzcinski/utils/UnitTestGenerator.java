package org.ttrzcinski.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
   * Reads given IML project file and reads from the definition marked directories.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator fromIML(String imlPath) {
    IMLParser imlParser = new IMLParser().of(imlPath);
    if (imlParser.isReady()) {
      System.out.println("IML parser is ready.");
    }
    return this;
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
    Set<String> sourcePaths = new HashSet<>();
    this.srcPath.stream()
        .map(FilesExt::allFileNamesOf)
        .forEach(sourcePaths::addAll);
    // Generate unit test classes one by one
    for (String fullFilePath : sourcePaths) {
      // Prepare unit test file path
      fullFilePath = fullFilePath.replaceFirst("src", "test");
      // If unit test file already exists, omit it
      final File tempFile = new File(fullFilePath);
      // Prepare path
      final Path path = Path.of(fullFilePath);
      try {
        // Remove file, if overriding
        if (this.overrideFlag) {
          System.out.printf("Deleting file %s%n", path);
          if (!debug) {
            Files.deleteIfExists(path);
          }
        }
        // Create field
        if (this.overrideFlag || !tempFile.exists()) {
          System.out.printf("Creating file %s%n", path);
          if (!debug) {
            Files.createFile(path);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
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
  }

}
