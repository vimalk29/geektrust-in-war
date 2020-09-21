package geektrust.in.war.utility;

import org.junit.jupiter.api.Test;

import geektrust.in.war.util.StringUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @Test
    void shouldGiveCorrectOutput(){
        String expected = "{H=100, E=50, AT=10, SG=5}";
        assertEquals(expected,  StringUtil.getArmyAsMapFromString("ARMY 100H 50E 10AT 5SG").toString());
    }
}
