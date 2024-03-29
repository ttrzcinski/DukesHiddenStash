package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class OSInfoTest {

    @Test
    void testCheckDirectoryBySystem() {
        // Arrange
        String expected = "nix";
        // Act
        String actual = OSInfo.checkDirectoryBySystem();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testIsWindows() {
        // Arrange-Act
        var actual = OSInfo.isWindows();

        // Assert
        assertFalse(actual);
    }

    @Test
    void testIsNix() {
        // Arrange-Act
        var actual = OSInfo.isNix();

        // Assert
        assertTrue(actual);
    }

    @Test
    void testReadResolution() {
        // Arrange-Act
        Dimension actual = OSInfo.readResolution();

        // Assert
        assertNotNull(actual);
        assertTrue(actual.getWidth() > 0);
        assertTrue(actual.getHeight() > 0);
    }

    @Test
    void testFixPath() {
        // Arrange
        String tempPath = "|usr|home|weird-tester|JavaProejcts";
        String expected = "//usr/home/weird-tester/JavaProjects";
        // Act
        String actual = OSInfo.fixPath(tempPath);

        // Assert
        assertEquals(expected, actual);
    }
}