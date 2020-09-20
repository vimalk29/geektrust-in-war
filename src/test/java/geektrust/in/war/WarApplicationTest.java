package geektrust.in.war;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import geektrust.in.war.util.FileUtility;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WarApplicationTest {
    private final String FIXTURES = "src/test/resources";
    private final ByteArrayOutputStream temporaryOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    @BeforeEach
    public void changingStreamForTesting() {
        System.setOut(new PrintStream(temporaryOutputStream));
    }

    @AfterEach
    public void restoringStreamAfterTest() {
        System.setOut(new PrintStream(originalOutputStream));
    }

    @Test
    public void shouldGiveWins() throws IOException {
        //Input 1
        String expected = FileUtility.fileToString(FIXTURES + "/output/output1.txt");
        WarApplication.main(new String[] {FIXTURES + "/input/input1.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
        //Input 2
        expected = FileUtility.fileToString(FIXTURES + "/output/output2.txt");
        WarApplication.main(new String[] {FIXTURES + "/input/input2.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    @Test
    public void shouldGiveLoses() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output3.txt");

        WarApplication.main(new String[] {FIXTURES + "/input/input3.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }
}
