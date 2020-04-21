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

class FileExt_existsTest {

    private static String path;

    @BeforeAll
    static void beforeAll() {
        FileExt_existsTest.path = format("test_file_%s.txt", new SimpleDateFormat("HHmmss").format(new Date()));
    }

    @AfterAll
    static void afterAll() {
        // Remove the file
        if (Files.exists(Paths.get(FileExt_existsTest.path)))
            try {
                Files.delete(Paths.get(FileExt_existsTest.path));
            } catch (IOException e) {
                fail(() -> format("Was unable to remove temp file: %s", FileExt_existsTest.path));
            }
        FileExt_existsTest.path = null;
    }

    @Test
    void exists_withPath_withMissingFile() {
        // Arrange
        String testedFullPath = String.format("missing_%s", FileExt_existsTest.path);
        Path testPath = Paths.get(testedFullPath);

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withPath_withSomeFile() {
        // Arrange
        String testedFullPath = FileExt_existsTest.path;
        Path testPath = Paths.get(testedFullPath);
        try {
            Files.createFile(testPath);
            assertTrue(Files.exists(testPath));
            List<String> lines = Arrays.asList("something_1", "something_2", "something_3");
            Files.write(testPath, lines);
        } catch (IOException e) {
            fail(() -> format("Was unable to create temp file: %s", testedFullPath));
        }

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertTrue(result);
    }

    @Test
    void exists_withString_withMissingFile() {
        // Arrange
        String testPath = String.format("missing_%s", FileExt_existsTest.path);
        File testFile = new File(testPath);

        // Act
        boolean result = FileExt.exists(testPath);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withString_withSomeFile() {
        // Arrange
        String testPath = FileExt_existsTest.path;
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

    @Test
    void exists_withFile_withMissingFile() {
        // Arrange
        String testPath = String.format("missing_%s", FileExt_existsTest.path);
        File testFile = new File(testPath);

        // Act
        boolean result = FileExt.exists(testFile);

        // Assert
        assertFalse(result);
    }

    @Test
    void exists_withFile_withSomeFile() {
        // Arrange
        String testPath = FileExt_existsTest.path;
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