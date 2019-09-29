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
   * Kept utils to generate AAA Test classes.
   */
  private AAATestGenerator atg;

  /**
   * Creates new instance of unit test generator.
   */
  public UnitTestGenerator() {
    this.atg = new AAATestGenerator();
    this.srcPath = new ArrayList<>();
  }

  /**
   * Reads from pointed IML file marked directories.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator fromIML(String imlPath) {
    var imlParser = new IMLParser().of(imlPath);
    if (imlParser.isReady()) {
      this.srcPath.add(imlParser.getSourcePath());
      this.testPath = imlParser.getTestPath();
      // Pass location to AAA test generator
      this.atg.setSources(imlParser.getSourcePath());
    }
    return this;
  }

  /**
   * Reads from current project's IML file marked directories.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator fromCurrentProject() {
    var projectPath = System.getProperty("user.dir");
    var imls = FileExt.
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
  private HashMap<String, String> prepareSourcesList() {
    var sourcePaths = new HashMap<String, String>();
    this.srcPath.stream()
        .map(FilesExt::allFileNamesOf)
        .flatMap(Collection::stream).forEach(innerFile -> {
      var preparedTestPath = this.srcPath.stream()
          .filter(innerFile::contains)
          .findFirst().map(src -> innerFile.replace(src, testPath))
          .orElse(innerFile);
      sourcePaths.put(innerFile, preparedTestPath);
    });
    return sourcePaths;
  }

  /**
   * Prepares processed files list, where key is 'from' and value is 'to'.
   *
   * @param omitInfo marks, if package-info should be skipped
   * @return list of files without package-info
   */
  private HashMap<String, String> prepareSourcesList(boolean omitInfo) {
    var sourcePaths = this.prepareSourcesList();
    if (!omitInfo) {
      return sourcePaths;
    }
    // Remove package-info files
    var keys = new ArrayList<>(sourcePaths.keySet());
    for (var key : keys) {
      if (key.endsWith("package-info.java")) {
        sourcePaths.remove(key);
      }
    }
    return sourcePaths;
  }

  /**
   * Returns only directories from given HashMap of files.
   *
   * @param given given map with sources and test paths
   * @return list with only directories
   */
  private List<String> onlyDirectories(HashMap<String, String> given) {
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
  private List<String> onlyFiles(HashMap<String, String> given) {
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
  private HashMap<String, String> onlySourceFiles(final HashMap<String, String> given) {
    // Check entered map
    if (!ParamCheck.isSet(given)) {
      return new HashMap<>();
    }
    // Process given map
    return given.entrySet().stream()
        .filter(file -> file.getKey().endsWith(".java"))
        .collect(
            Collectors.toMap(
                Entry::getKey, Entry::getValue, (a, b) -> b, HashMap::new
            )
        );
  }

  /**
   * Returns only files from given HashMap with pointed extension.
   *
   * @param given given map with sources and test paths
   * @param extension pointed file's extension to filter files
   * @return list with only files
   */
  private List<String> onlyFilesWithExtension(HashMap<String, String> given,
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
    var fixedExtension = String.format(".%s", extension);
    return given.values().stream()
        .filter(value -> !value.endsWith(fixedExtension))
        .collect(Collectors.toList());
  }

  /**
   * Generates unit test cases after prior setting params.
   */
  public final void generate() {
    System.out.println("Starting to generate unit tests..");
    var sourcePaths = this.prepareSourcesList(true);
    // Generate unit test directories
    var results = FileExt.makeDirectories(onlyDirectories(sourcePaths));
    // Check, if all directories were created
    var failedDirectories = (int) Arrays.stream(results)
        .filter(Objects::isNull).count();
    if (failedDirectories > 0) {
      System.err.printf("Failed at creating directories: %d%n",
          failedDirectories
      );
      return;
    }
    // Start Sources unit test files
    var testFiles = onlySourceFiles(sourcePaths);
    // Generate unit test classes one by one
    for (var change : testFiles.entrySet()) {
      var fixedPath = change.getValue()
          .replaceFirst(".java", "_AAATest.java");
      var file = Path.of(fixedPath);
      // Remove, if unit test exists
      if (file.toFile().exists()) {
        FileExt.remove(file);
      }
      // Prepare content for unit test file
      // Create unit test file
      var fullContent = String.join("", this.atg.
          generate(change.getKey()));
      var result = FileExt.create(file.toAbsolutePath(), fullContent);
      if (result == null) {
        System.out.printf("Failed at creating file %s%n",
            file.toFile().getName()
        );
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
