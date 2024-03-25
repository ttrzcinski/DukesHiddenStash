package org.ttrzcinski.utils.file_ext;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.FileExt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class FileExtExistsTest {

    private static String path;

    @BeforeAll
    static void beforeAll() {
        FileExtExistsTest.path = "test_file_%s.txt".formatted(new SimpleDateFormat("HHmmss").format(new Date()));
    }

    @AfterAll
    static void afterAll() {
        // Remove the file
        if (Files.exists(Paths.get(FileExtExistsTest.path)))
            try {
                Files.delete(Paths.get(FileExtExistsTest.path));
            } catch (IOException e) {
                fail(() -> "Was unable to remove temp file: %s".formatted(FileExtExistsTest.path));
            }
        FileExtExistsTest.path = null;
    }

    @Test
    void exists_withPath_withMissingFile() {
        // Arrange
        String testedFullPath = "missing_%s".formatted(FileExtExistsTest.path);
        Path testPath = Paths.get(testedFullPath);

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withPath_withSomeFile() {
        // Arrange
        String testedFullPath = FileExtExistsTest.path;
        Path testPath = Paths.get(testedFullPath);
        try {
            Files.createFile(testPath);
            assertTrue(Files.exists(testPath));
            List<String> lines = Arrays.asList("something_1", "something_2", "something_3");
            Files.write(testPath, lines);
        } catch (IOException e) {
            fail(() -> "Was unable to create temp file: %s".formatted(testedFullPath));
        }

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertTrue(result);
    }

    @Test
    void exists_withString_withMissingFile() {
        // Arrange
        String testPath = "missing_%s".formatted(FileExtExistsTest.path);
        File testFile = new File(testPath);

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withString_withSomeFile() {
        // Arrange
        String testPath = FileExtExistsTest.path;
        File testFile = null;
        try {
            testFile = new File(testPath);
            testFile.createNewFile();
            assertTrue(testFile.exists());
        } catch (IOException e) {
            fail(() -> "Was unable to create temp file: %s".formatted(testPath));
        }

        // Act
        boolean result = FileExt.exists(testFile);

        // Assert
        assertTrue(result);
    }

    @Test
    void exists_withFile_withMissingFile() {
        // Arrange
        String testPath = String.format("missing_%s", FileExtExistsTest.path);
        File testFile = new File(testPath);

        // Act
        boolean result = FileExt.exists(testFile);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withFile_withSomeFile() {
        // Arrange
        String testPath = FileExtExistsTest.path;
        File testFile = null;
        try {
            testFile = new File(testPath);
            testFile.createNewFile();
            assertTrue(testFile.exists());
        } catch (IOException e) {
            fail(() -> format("Was unable to create temp file: %s", testPath));
        }

        // Act
        boolean result = FileExt.exists(testFile);

        // Assert
        assertTrue(result);
    }
}
