package org.ttrzcinski.utils.string_fix;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.StringFix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ttrzcinski.dictionaries.FakerString.*;

class StringFixCutFirstTest {

    @Test
    void cutFirst_withNull() {
        // Arrange
        int passedLength = -1;

        // Act
        String result = StringFix.cutFirst(NULL, passedLength);

        // Assert
        assertEquals(NULL, result);
    }

    @Test
    void cutFirst_withEmpty_withNegative() {
        // Arrange
        int passedLength = -1;

        // Act
        String result = StringFix.cutFirst(EMPTY, passedLength);

        // Assert
        assertEquals(EMPTY, result);
    }

    @Test
    void cutFirst_withEmpty_withZero() {
        // Arrange
        int passedLength = 0;

        // Act
        String result = StringFix.cutFirst(EMPTY, passedLength);

        // Assert
        assertEquals(EMPTY, result);
    }

    @Test
    void cutFirst_withEmpty_withTooLong() {
        // Arrange
        int passedLength = 1;

        // Act
        String result = StringFix.cutFirst(EMPTY, passedLength);

        // Assert
        assertEquals(EMPTY, result);
    }

    @Test
    void cutFirst_withSome_withNegative() {
        // Arrange
        int passedLength = -1;

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(SOME, result);
    }

    @Test
    void cutFirst_withSome_withZero() {
        // Arrange
        int passedLength = 0;

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(SOME, result);
    }

    @Test
    void cutFirst_withSome_withLengthWithin() {
        // Arrange
        int passedLength = 2;
        String expected = SOME.substring(passedLength);

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void cutFirst_withSome_withWholeLength() {
        // Arrange
        int passedLength = SOME.length();

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(EMPTY, result);
    }

    @Test
    void cutFirst_withSome_withTooLong() {
        // Arrange
        int passedLength = SOME.length() + 2;

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(EMPTY, result);
    }

    @Test
    void cutFirst_withSome_withSubsFromTheEnd() {
        // Arrange
        String expected = "me";
        int passedLength = SOME.length() - 2;

        // Act
        String result = StringFix.cutFirst(SOME, passedLength);

        // Assert
        assertEquals(expected, result);
    }
}
