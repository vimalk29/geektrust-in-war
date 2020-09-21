package geektrust.in.war.utility;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import geektrust.in.war.util.FileUtility;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Helps to test FileUtility class functions
 * <p>
 * 1. Tests if the IOException is implemented correctly
 * 2. Tests that the FileUtility function is able to decode the content correctly
 *
 * @author vimalk29
 * @version 1.0
 */
class FileUtilityTest {
    private final String FIXTURES = "src/test/resources";

    @Test
    void shouldConvertFileToStringCorrectly() throws IOException {
        String expected = "FALICORNIA_ATTACK 100H 101E 20AT 5SG";
        assertEquals(expected, FileUtility.fileToString(FIXTURES + "/input/input1.txt"));
    }

    /**
     * If file location is wrong, should throw IOException
     */

    @Test
    void whenExceptionThrownThenExpectationSatisfied() {
        assertThrows(IOException.class, () -> FileUtility.fileToString(FIXTURES + "/input/inputNotExists.txt"));
    }

    /**
     * If file location is correct, should not throw IOException
     */
    @Test
    void whenExceptionThrownThenExpectationFails() {
        assertDoesNotThrow(() -> FileUtility.fileToString(FIXTURES + "/input/input1.txt"));
    }
}
