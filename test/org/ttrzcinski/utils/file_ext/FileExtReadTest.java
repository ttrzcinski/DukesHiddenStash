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

class FileExtReadTest {

    private static String path;

    @BeforeAll
    static void beforeAll() {
        FileExtReadTest.path = format("test_file_%s.txt", new SimpleDateFormat("HHmmss").format(new Date()));
    }

    @AfterAll
    static void afterAll() {
        // Remove the file
        if (Files.exists(Paths.get(FileExtReadTest.path)))
            try {
                Files.delete(Paths.get(FileExtReadTest.path));
            } catch (IOException e) {
                fail(() -> format("Was unable to remove temp file: %s", FileExtReadTest.path));
            }
        FileExtReadTest.path = null;
    }
    
    @Test
    void read_withPath_withMissingFile() {
        // Arrange
        String testedFullPath = String.format("missing_%s", FileExtReadTest.path);
        Path testPath = Paths.get(testedFullPath);

        // Act
        File result = FileExt.read(testPath);

        // Assert
        assertNull(result);
    }

    @Test
    void read_withPath_withSomeFile() {
        // Arrange
        String testedFullPath = FileExtReadTest.path;
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
        File result = FileExt.read(testPath);

        // Assert
        assertNotNull(result);
    }
}
