package geektrust.in.war.war;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import geektrust.in.war.dto.Battalion;

/**
 * This is War class,
 * It consists of two functionality
 * 1. Substitutes the battalions in order to fit into the max limit consitions
 * 2. Decide the battle outcome of the battalions Lengaburu is going to deploy
 *
 * @author vimalk29
 * @version 1.0
 */
public class War {
    private Set<String> armyComposition;
    private LinkedHashMap<String, Battalion> battalions;

    /**
     * armyComposition is initialised with LinkedHashSet to keep order and to contain army composition.
     * battalions is initialised with LinkedHashMap to keep the order intact and contain
     * the resultant battalion composition
     */
    public War() {
        this.armyComposition = new LinkedHashSet<>();
        this.battalions = new LinkedHashMap<>();
    }

    /**
     * Contains the logic behind how to substitute the battalion in case of exhaustion for each battalion
     * looks at 1 index smaller and 1 index greater than the current index and consumes the battalions
     * it should take based on the problem statement
     *
     * @param battalionComposition is the unsubstituted battalion composition
     *                             ( which is returned by ArmyService class method )
     *                             It's the base of our substitution algorithm
     */
    public void substituteExhaustedBattalions(LinkedHashMap<String, Battalion> battalionComposition) {
        ArrayList<String> battalionTypeList = new ArrayList<>(battalionComposition.keySet());
        int battalionTypeLength = battalionTypeList.size();
        //Creating a index loop as we would need to check
        //previous and next indices of the battalion for substitution
        for (int index = 0; index < battalionTypeLength; ++index) {
            //Get the battalion
            Battalion currentBattalion = battalionComposition.get(battalionTypeList.get(index));
            Battalion adjacentBattalion;

//            System.out.println("currentBattalion= " + currentBattalion + " ");

            if (index > 0 && currentBattalion.getExhausted()) {
                //Battalions weaker than current at index - 1
                String adjacentBattalionType = battalionTypeList.get(index - 1);
                adjacentBattalion = battalionComposition.get(adjacentBattalionType);

//                System.out.println("adjacentBattalionMin before = " + adjacentBattalion + " ");
                if (!adjacentBattalion.getExhausted()) {
                    //units required by current Battalion
                    int unitsRequired = currentBattalion.getUnitsDeployed() - currentBattalion.getMaxUnits();
                    // unitNeededFromAdjacent holds the value of units required from the current battalion
                    int unitNeededFromAdjacent = (unitsRequired) * 2;

                    // canBeAdded holds the value of units which can be substituted without exhausting adjacent
                    Integer canBeAdded = adjacentBattalion.canBeAddedFrom(unitNeededFromAdjacent);
                    adjacentBattalion.addToUnitsDeployed(canBeAdded);

                    currentBattalion.decrementUnitsDeployed(canBeAdded / 2);

                    //Update battalion Composition
                    battalionComposition.put(adjacentBattalionType, adjacentBattalion);
                    battalionComposition.put(battalionTypeList.get(index), currentBattalion);
                }

//                System.out.println("adjacentBattalionMin after = " + adjacentBattalion + " ");
            }
            if (index < battalionTypeLength - 1 && currentBattalion.getExhausted()) {
                //Battalions stronger than current at index + 1
                String adjacentBattalionType = battalionTypeList.get(index + 1);
                adjacentBattalion = battalionComposition.get(adjacentBattalionType);

//                System.out.println("adjacentBattalionMax before = " + adjacentBattalion + " ");
                if (!adjacentBattalion.getExhausted()) {
                    //units required by current Battalion
                    int unitsRequired = currentBattalion.getUnitsDeployed() - currentBattalion.getMaxUnits();
                    // unitNeededFromAdjacent holds the value of units required from the current battalion
                    double exactUnitNeededFromAdjacent = (float) unitsRequired / 2;
                    int unitNeededFromAdjacent = (int) Math.ceil(exactUnitNeededFromAdjacent);

                    // canBeAdded holds the value of units which can be substituted without exhausting adjacent
                    Integer canBeAdded = adjacentBattalion.canBeAddedFrom(unitNeededFromAdjacent);
                    adjacentBattalion.addToUnitsDeployed(canBeAdded);

                    currentBattalion.decrementUnitsDeployed(canBeAdded * 2 > unitsRequired ? unitsRequired : canBeAdded * 2);
//                    currentBattalion.decrementUnitsDeployed(canBeAdded * 2);

                    //Update battalion Composition
                    battalionComposition.put(adjacentBattalionType, adjacentBattalion);
                    battalionComposition.put(battalionTypeList.get(index), currentBattalion);
                }
//                System.out.println("adjacentBattalionMax after = " + adjacentBattalion + " ");
            }

        }

        this.battalions = battalionComposition;
    }


    /**
     * Loops through the battalions and see if there's any battalion who is exhausted after
     * substitution and hence calculate the outcome of the battle
     *
     * @return an string in desired output for the battalions we lengaburu can send
     */
    public String decideBattleOutcome() {
        Boolean lost = false;
        for (String battalionType : battalions.keySet()) {
            Battalion currentBattalion = battalions.get(battalionType);
            if (!lost) {
                lost = currentBattalion.getExhausted();
            }
            currentBattalion.setUnitsDeployedByCapacity();
            addBattalionComposition(battalionType, currentBattalion.getUnitsDeployed());
        }
        return getArmyComposition(!lost);
    }

    /**
     * Adds battalion composition to armyComposition Set while calculating the substituted values
     * of each battalion of the army
     *
     * @param battalionType the type of battalion to be added to string
     * @param units         the number of units of that battalion
     */
    private void addBattalionComposition(String battalionType, Integer units) {
        this.armyComposition.add(units.toString() + battalionType);
    }

    /**
     * @param win denotes if Lengaburu's army won or lost the attack
     * @return a string in the desired format containing wins or loses as well as the required army composition
     */
    private String getArmyComposition(Boolean win) {
        String army = win ? "WINS " : "LOSES ";
        return army.concat(String.join(" ", this.armyComposition));
    }
}