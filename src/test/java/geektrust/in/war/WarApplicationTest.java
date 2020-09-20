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
    void changingStreamForTesting() {
        System.setOut(new PrintStream(temporaryOutputStream));
    }

    @AfterEach
    void restoringStreamAfterTest() {
        System.setOut(new PrintStream(originalOutputStream));
    }

    /*@Test
    void battalionCompositionCheck() throws IOException {
        String inputString =  FileUtility.fileToString(FIXTURES + "/input/input3.txt");
        //Contains Falicornia's Army Coposition
        LinkedHashMap<String,Integer> falicorniaArmy = WarApplication.getFalicorniaArmyAsMap(inputString);
        ArmyService armyService = new ArmyService(falicorniaArmy);
        // Obtains battalion composition of Lengaburu's Army against falicornia without substitution
        LinkedHashMap<String, Battalion> expected = new LinkedHashMap<>();
        //Hard coded expected data for input 3
        expected.put("H", new Battalion(100, falicorniaArmy.get("H") ));
        expected.put("E", new Battalion(50,  falicorniaArmy.get("E") ));
        expected.put("AT", new Battalion(10, falicorniaArmy.get("AT") ));
        expected.put("SG", new Battalion(5,  falicorniaArmy.get("SG") ));

        assertEquals(expected.toString() , armyService.getBattalionComposition().toString() );
    }*/

    @Test
    void shouldGiveWinsInput1() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output1.txt");
        WarApplication.main(new String[] {FIXTURES + "/input/input1.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    @Test
    void shouldGiveWinInput2()throws  IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output2.txt");
        WarApplication.main(new String[] {FIXTURES + "/input/input2.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }

    @Test
    void shouldGiveLosesInput3() throws IOException {
        String expected = FileUtility.fileToString(FIXTURES + "/output/output3.txt");
        WarApplication.main(new String[] {FIXTURES + "/input/input3.txt"});
        assertEquals(expected, temporaryOutputStream.toString());
    }
}
