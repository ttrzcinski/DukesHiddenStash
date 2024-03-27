package org.ttrzcinski.utils.param_check;

import org.junit.jupiter.api.Test;
import org.ttrzcinski.utils.ParamCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamCheckIsPathWithTryTest {

    @Test
    void isPathWithCheck_null() {
        // Arrange
        String testObject = "";

        // Act
        boolean result = ParamCheck.isPathWithTry(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPathWithCheck_empty() {
        // Arrange
        String testObject = "";

        // Act
        boolean result = ParamCheck.isPathWithTry(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPathWithCheck_nixHomeCatalog() {
        // Arrange
        String testObject = "~/something";

        // Act
        boolean result = ParamCheck.isPathWithTry(testObject);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPathWithCheck_nixRootCatalog() {
        // Arrange
        String testObject = "./something";

        // Act
        boolean result = ParamCheck.isPathWithTry(testObject);

        // Assert
        assertTrue(result);
    }
}
