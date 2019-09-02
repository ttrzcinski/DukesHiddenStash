package org.ttrzcinski.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

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
   * Checks, if mandatory parameters are set.
   *
   * @return true means all needed parameters are set, false otherwise
   */
  public final boolean validateParameters() {
    return this.srcPath != null && this.testPath != null;
  }

  /**
   * Prepares processed files list, where key is 'from' and value is 'to'.
   *
   * @return key-value map with source files and unit test files
   */
  private final HashMap<String, String> prepareProcessingList() {
    var sourcePaths = new HashMap<String, String>();
    this.srcPath.stream()
        .map(FilesExt::allFileNamesOf)
        .flatMap(Collection::stream).forEach(innerFile -> {
      String preparedTestPath = this.srcPath.stream()
          .filter(innerFile::contains)
          .findFirst().map(src -> innerFile.replace(src, testPath))
          .orElse(innerFile);
      sourcePaths.put(innerFile, preparedTestPath);
    });
    return sourcePaths;
  }

  /**
   * Returns only directories from given HashMap of files.
   *
   * @param given given map with sources and test paths
   * @return list with only directories
   */
  private final List<String> onlyDirectories(HashMap<String, String> given) {
    return given.values().stream()
        .filter(value -> !value.endsWith(".java"))
        .collect(Collectors.toList());
  }

  /**
   * Returns only files from given HashMap.
   *
   * @param given given map with sources and test paths
   * @return list with only files
   */
  private final List<String> onlyFiles(HashMap<String, String> given) {
    // Check entered map
    if (!ParamCheck.isSet(given)) {
      return new ArrayList<>();
    }
    // Process given map
    return given.values().stream()
        .filter(
            value -> Path.of(value).getFileName().toString().contains(".")
        )
        .collect(Collectors.toList());
  }

  /**
   * Returns only files from given HashMap.
   *
   * @param given given map with sources and test paths
   * @return map with only directories
   */
  private final HashMap<String, String> onlySourceFiles(final HashMap<String, String> given) {
    // Check entered map
    if (!ParamCheck.isSet(given)) {
      return new HashMap<>();
    }
    // Process given map
    HashMap<String, String> files = given.entrySet().stream()
        .filter(file -> file.getKey().endsWith(".java"))
        .collect(
            Collectors.toMap(
                Entry::getKey, Entry::getValue, (a, b) -> b, HashMap::new
            )
        );
    return files;
  }

  /**
   * Returns only files from given HashMap with pointed extension.
   *
   * @param given given map with sources and test paths
   * @param extension pointed file's extension to filter files
   * @return list with only files
   */
  private final List<String> onlyFilesWithExtension(HashMap<String, String> given,
      String extension) {
    // Check entered params
    if (!ParamCheck.isSet(extension)) {
      return onlyFiles(given);
    }
    // Check entered map
    if (!ParamCheck.isSet(given)) {
      return new ArrayList<>();
    }
    // Process given map
    String fixedExtension = String.format(".%s", extension);
    return given.values().stream()
        .filter(value -> !value.endsWith(fixedExtension))
        .collect(Collectors.toList());
  }

  /**
   * Generates unit test cases after prior setting params.
   */
  public final void generate() {
    System.out.println("Starting to generate unit tests..");
    var sourcePaths = this.prepareProcessingList();
    // Generate unit test directories
    File[] results = FileExt.makeDirectories(onlyDirectories(sourcePaths));
    // Check, if all directories were created
    int failedDirectories = (int) Arrays.stream(results)
        .filter(Objects::isNull).count();
    if (failedDirectories > 0) {
      System.err.printf("Failed at creating directories: %d%n",
          failedDirectories
      );
      return;
    }
    // Start processing unit test files
    HashMap<String, String> testFiles = onlySourceFiles(sourcePaths);
    // Generate unit test classes one by one
    for (Entry<String, String> change : testFiles.entrySet()) {
      String val = change.getValue();
      String fixedPath = val.replaceFirst(".java", "_AAATest.java");
      Path file = Path.of(fixedPath);
      // Remove, if unit test exists
      if (file.toFile().exists()) {
        FileExt.remove(file);
      }
      // Create unit test file
      File result = FileExt.create(file.toAbsolutePath());
      if (result == null) {
        System.out.println("Failed at creating file " + file.toFile().getName());
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
    System.out.println(this.testPath);
  }

}
