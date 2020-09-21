package geektrust.in.war;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import geektrust.in.war.util.FileUtility;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * WarApplicationTest class helps us to test outcome of the program in case of 3 input
 * files which are added in the resources directory
 *
 * @author vimalk29
 * @version 1.0
 */
class WarApplicationTest {
    private final String FIXTURES = "src/test/resources";
    private final ByteArrayOutputStream temporaryOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    /**
     * Changes Output Stream to ByteArrayOutputStream
     * helps us to test for console outputs without problems
     */
    @BeforeEach
    void changingStreamForTesting() {
        System.setOut(new PrintStream(temporaryOutputStream));
    }

    /**
     * Changes Output Stream back to System.out
     */
    @AfterEach
    void restoringStreamAfterTest() {
        System.setOut(new PrintStream(originalOutputStream));
    }


    /**
     * Should give a WINS with input file 1
     *
     * @throws IOException if Input file not found
     */
    @Test
    void shouldGiveWinsInputOne() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output1.txt");
        WarApplication.main(new String[]{FIXTURES + "/input/input1.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    /**
     * Should give a WINS with input file 1
     *
     * @throws IOException if Input file not found
     */
    @Test
    void shouldGiveWinInputTwo() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output2.txt");
        WarApplication.main(new String[]{FIXTURES + "/input/input2.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    /**
     * Should give a LOSES with input file 3
     *
     * @throws IOException if Input file not found
     */
    @Test
    void shouldGiveLosesInputThree() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output3.txt");
        WarApplication.main(new String[]{FIXTURES + "/input/input3.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    /**
     * Should throw ArrayIndexOutOfBoundsException if no directory is given
     *
     * @throws ArrayIndexOutOfBoundsException as no File directory given
     */
    @Test
    void shouldThrowArrayOutOfIndexExceptionIfNoFilesGivenAsArguments() throws ArrayIndexOutOfBoundsException {
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->WarApplication.main(new String[]{}));
    }


    /**
     * Should throw ArrayIndexOutOfBoundsException if no directory is given
     *
     * @throws ArrayIndexOutOfBoundsException as no File directory given
     */
    @Test
    void shouldNotThrowArrayOutOfIndexExceptionIfFilesGivenAsArguments() throws ArrayIndexOutOfBoundsException {
        assertDoesNotThrow(()->WarApplication.main(new String[]{FIXTURES + "/input/input3.txt"}));
    }


}
