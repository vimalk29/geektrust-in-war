package geektrust.in.war.repositoryservice;


import java.util.LinkedHashMap;
import java.util.Map;

import geektrust.in.war.dto.Battalion;
import geektrust.in.war.repository.ArmyData;

/**
 * This is Service class for ArmyData,
 * it helps to calculate unsubstituted army composition with respect to lengaburu's army
 *
 * @author vimalk29
 * @version 1.0
 */
public class ArmyService {
    private final ArmyData armyData;

    /**
     * Initialises armyData object using armyMap
     *
     * @param armyMap is the LinkedHashMap of the opponent's army composition
     */
    public ArmyService(LinkedHashMap<String, Integer> armyMap) {
        armyData = new ArmyData(armyMap);
    }

    /**
     * This function calculates the units of each battalion required
     * It maps Battalion type and it's status for lengaburu's army and returns it
     *
     * @return battalionComposition
     * <p>
     * Note: It doesn't substitute exhausted battalions but only gives an overview of battalion units required
     */
    public LinkedHashMap<String, Battalion> getBattalionComposition() {
        Map<String, Integer> lengaburuArmy = new ArmyData().getArmy();
        Map<String, Integer> falicorniaArmy = armyData.getArmy();

        LinkedHashMap<String, Battalion> battalionComposition = new LinkedHashMap<>();

        for (String battalionType : lengaburuArmy.keySet()) {
            Battalion battalion = new Battalion(lengaburuArmy.get(battalionType), falicorniaArmy.get(battalionType));
            battalionComposition.put(battalionType, battalion);
        }
        return battalionComposition;
    }

}