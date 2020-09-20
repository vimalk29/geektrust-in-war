package geektrust.in.war.repository;

import java.util.LinkedHashMap;

/**
 * This class provides army's composition of the attack
 *
 * @author vimalk29
 * @version 1.0
 */
public class ArmyData {
    private final LinkedHashMap<String, Integer> army;

    /**
     * Constructor to initialise army with Lengaburu's army capacity
     */
    public ArmyData() {
        army = new LinkedHashMap<>();
        initializeLengaburuArmy();
    }

    /**
     * Constructor to initialise army using
     * @param army
     */
    public ArmyData(LinkedHashMap<String, Integer> army) {
        this.army = army;
    }

    /**
     * This function will add Lengaburu's initial army units in the map.
     * It is hard-coded as some units are known before hand.
     */
    private void initializeLengaburuArmy(){
        addArmyUnit("H", 100);
        addArmyUnit("E",50);
        addArmyUnit("AT", 10);
        addArmyUnit("SG",5);
    }


    /**
     * This function will return the map containing max army composition.
     */
    public LinkedHashMap<String, Integer> getArmy() {
        return this.army;
    }
    
    /**
     * This function will add army units in the map.
     * @param battalionType  battalion type to be added
     * @param maxQuantity  quantity of this unit.
     */
    public void addArmyUnit(String battalionType, Integer maxQuantity){
        getArmy().put(battalionType,maxQuantity);
    }
    
    /**
     * This function will remove an army unit in the map.
     * @param battalionType will be added in the map
     */
    public  void removeArmyUnit(String battalionType){
        getArmy().remove(battalionType);
    }
}