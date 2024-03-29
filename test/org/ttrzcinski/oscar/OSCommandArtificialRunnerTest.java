package org.ttrzcinski.oscar;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.RandomExt;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class OSCommandArtificialRunnerTest {

    @Test
    void testGetUsedCommands() {
        // Arrange
        long randomId = new Random().nextLong();
        String tempRandomCommand = String.format("test-that-command-with-number-%s", randomId);
        OSCommandArtificialRunner.run(tempRandomCommand);

        // Act
        List<String> actual = OSCommandArtificialRunner.getUsedCommands();

        // Assert
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(actual.contains(tempRandomCommand));
    }

    @Test
    void testGetLast() {
        // Arrange
        long randomId = new Random().nextLong();
        String tempRandomCommand = RandomExt.randomName();
        OSCommandArtificialRunner.run(tempRandomCommand);

        // Act
        String actual = OSCommandArtificialRunner.getLast();

        // Assert
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(actual.contains(tempRandomCommand));
    }

    @Test
    void testRun() {
        // Arrange
        String testCommand = "pwd";

        // Act
        var actual = OSCommandArtificialRunner.run(testCommand);

        // Assert
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(actual.contains("test"));
    }

    // TODO ADD CONDITIONS TO RUN ONLY ON LINUX
    @Test
    void testIsNix() {
        // Arrange
        // Act
        var actual = OSCommandArtificialRunner.isNix();

        // Assert
        assertTrue(actual);
    }
}