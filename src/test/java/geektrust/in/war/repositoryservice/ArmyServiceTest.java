package geektrust.in.war.repositoryservice;

import org.junit.jupiter.api.Test;

import java.util.Map;

import geektrust.in.war.dto.Battalion;
import geektrust.in.war.util.StringUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmyServiceTest {

    @Test
    void shouldSaveArmyCorrectly() {
        String expected = "{H=100, E=50, AT=10, SG=5}";
        ArmyService armyService = new ArmyService(StringUtil.getArmyAsMapFromString("ARMY 100H 50E 10AT 5SG"));
        assertEquals(expected, armyService.getArmyData().getArmy().toString());
    }


    @Test
    void shouldGiveCorrectBattalionComposition() {
        String expected = "{H={MaxUnits: 100 opponentUnits: 300 unitsDeployed: 150 exhausted: true}, E={MaxUnits: 50 opponentUnits: 100 unitsDeployed: 50 exhausted: false}}";
        ArmyService armyService = new ArmyService(StringUtil.getArmyAsMapFromString("ARMY 100H 50E"));
        Map<String, Battalion> battalionMap = armyService
                .getBattalionComposition(StringUtil
                        .getArmyAsMapFromString("FALICORNIA_ATTACK 300H 100E"));

        assertEquals(expected, battalionMap.toString());
    }

}
