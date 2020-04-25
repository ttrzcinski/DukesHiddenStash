package org.ttrzcinski.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnvArgumentTest {

    @Test
    void hasField_halfOfWidth() {
        // Arrange
        var testObject = new EnvArgument();
        var fields = testObject.getClass().getDeclaredFields();
        var expectedName = "HALF_OF_WIDTH";

        // Act
        boolean result = Arrays.stream(fields)
                .peek(x -> x.setAccessible(true))
                .anyMatch(x -> x.getName().contains(expectedName));

        // Assert
        assertTrue(result);
    }

    @Test
    void hasAnConstructor() {
        // Arrange
        var testObject = new EnvArgument();
        var constructors = testObject.getClass().getConstructors();

        // Act
        boolean result = constructors != null && constructors.length > 0;

        // Assert
        assertTrue(result);
    }

    @Test
    void hasMethod_withAcronym() {
        // Arrange
        var methods = new EnvArgument().getClass().getDeclaredMethods();
        var expected = "withAcronym";

        // Act
        boolean result = Arrays.stream(methods)
                .peek(x -> x.setAccessible(true))
                .anyMatch(x -> x.getName().contains(expected));

        // Assert
        assertTrue(result);
    }

    @Test
    void hasMethod_withName() {
        // Arrange
        var methods = new EnvArgument().getClass().getDeclaredMethods();
        var expected = "withName";

        // Act
        boolean result = Arrays.stream(methods)
                .peek(x -> x.setAccessible(true))
                .anyMatch(x -> x.getName().contains(expected));

        // Assert
        assertTrue(result);
    }

    @Test
    void hasMethod_withDescription() {
        // Arrange
        var methods = new EnvArgument().getClass().getDeclaredMethods();
        var expected = "withDescription";

        // Act
        boolean result = Arrays.stream(methods)
                .peek(x -> x.setAccessible(true))
                .anyMatch(x -> x.getName().contains(expected));

        // Assert
        assertTrue(result);
    }

    @Test
    void withDescription_isSomething() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertNotNull(result);
    }

    @Test
    void withDescription_isExact() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertTrue(result.contains(testDescription));
    }

    @Test
    void asHelp_isSomething() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertNotNull(result);
    }

    @Test
    void asHelp_isExact() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertTrue(result.contains(testDescription));
    }

    @Test
    void getLetter_isSomething() {
        // Arrange
        var testObject = new EnvArgument();
        var testName = "that test 1";

        // Act
        var result = testObject.withAcronym(testName).asHelp();

        // Assert
        assertNotNull(result);
    }

    @Test
    void getLetter_isExact() {
        // Arrange
        var testObject = new EnvArgument();
        var testName = "that test 1";

        // Act
        var result = testObject.withAcronym(testName).asHelp();

        // Assert
        assertTrue(result.contains(testName));
    }

    @Test
    void getNick_isSomething() {
        // Arrange
        var testObject = new EnvArgument();
        var testName = "that test 1";

        // Act
        var result = testObject.withName(testName).asHelp();

        // Assert
        assertNotNull(result);
    }

    @Test
    void getNick_isExact() {
        // Arrange
        var testObject = new EnvArgument();
        var testName = "that test 1";

        // Act
        var result = testObject.withName(testName).asHelp();

        // Assert
        assertTrue(result.contains(testName));
    }

    @Test
    void getHelp_isSomething() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertNotNull(result);
    }

    @Test
    void getHelp_isExact() {
        // Arrange
        var testObject = new EnvArgument();
        var testDescription = "its something";

        // Act
        var result = testObject.withDescription(testDescription).asHelp();

        // Assert
        assertTrue(result.contains(testDescription));
    }
}
