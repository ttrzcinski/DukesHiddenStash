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

class FileExt_removeTest {

    private static String path;

    @BeforeAll
    static void beforeAll() {
        FileExt_removeTest.path = format("test_file_%s.txt", new SimpleDateFormat("HHmmss").format(new Date()));
    }

    @AfterAll
    static void afterAll() {
        // Remove the file
        if (Files.exists(Paths.get(FileExt_removeTest.path)))
            try {
                Files.delete(Paths.get(FileExt_removeTest.path));
            } catch (IOException e) {
                fail(() -> "Was unable to remove temp file: %s".formatted(FileExt_removeTest.path));
            }
        FileExt_removeTest.path = null;
    }

    @Test
    void remove_withPath_withMissingFile() {
        // Arrange
        String testedFullPath = "missing_%s".formatted(FileExt_removeTest.path);
        Path testPath = Paths.get(testedFullPath);

        // Act
        Object result = FileExt.remove(testPath);

        // Assert
        assertNull(result);
    }

    @Test
    void remove_withPath_withSomeFile() {
        // Arrange
        Path testPath = Paths.get(FileExt_removeTest.path);
        try {
            Files.createFile(testPath);
            assertTrue(Files.exists(testPath));
            List<String> lines = Arrays.asList("something_1", "something_2", "something_3");
            Files.write(testPath, lines);
        } catch (IOException e) {
            fail(() -> "Was unable to create temp file: %s".formatted(FileExt_removeTest.path));
        }

        // Act
        Object result = FileExt.remove(testPath);

        // Assert
        assertNotNull(result);
    }

    @Test
    void remove_withFile_withMissingFile() {
        // Arrange
        String testPath = "missing_%s".formatted(FileExt_removeTest.path);
        File testFile = new File(testPath);

        // Act
        Object result = FileExt.remove(testFile);

        // Assert
        assertNull(result);
    }

    @Test
    void remove_withFile_withSomeFile() {
        // Arrange
        String testPath = FileExt_removeTest.path;
        File testFile = null;
        try {
            testFile = new File(testPath);
            testFile.createNewFile();
            assertTrue(testFile.exists());
        } catch (IOException e) {
            fail(() -> "Was unable to create temp file: %s".formatted(testPath));
        }

        // Act
        Object result = FileExt.remove(testFile);

        // Assert
        assertNotNull(result);
    }
}
