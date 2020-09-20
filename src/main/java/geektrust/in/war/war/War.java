package geektrust.in.war.war;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import geektrust.in.war.dto.Battalion;

public class War {
    // Using LinkedHashSet to keep order and to contain army composition.
    private Set<String> armyComposition;
    private LinkedHashMap<String, Battalion> battalions;

    public War() {
        this.armyComposition = new LinkedHashSet<>();
        this.battalions = new LinkedHashMap<>();
    }

    public void substituteExhaustedBattalions(LinkedHashMap<String, Battalion> battalionComposition) {
        ArrayList<String> battalionTypeList = new ArrayList<>(battalionComposition.keySet());
        int battalionTypeLength = battalionTypeList.size();
        //Creating a index loop as we would need to check
        //previous and next indices of the battalion for substitution
        for (int index = 0; index < battalionTypeLength; ++index) {
            //Get the battalion
            Battalion currentBattalion = battalionComposition.get(battalionTypeList.get(index));

            Battalion adjacentBattalion;

            if (index > 0 && currentBattalion.getExhausted()) {
                //Battalions weaker than current at index - 1
                String adjacentBattalionType = battalionTypeList.get(index - 1);
                adjacentBattalion = battalionComposition.get(adjacentBattalionType);

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
            }
            if (index < battalionTypeLength - 1 && currentBattalion.getExhausted()) {
                //Battalions stronger than current at index + 1
                String adjacentBattalionType = battalionTypeList.get(index + 1);
                adjacentBattalion = battalionComposition.get(adjacentBattalionType);

                if (!adjacentBattalion.getExhausted()) {
                    //units required by current Battalion
                    int unitsRequired = currentBattalion.getUnitsDeployed() - currentBattalion.getMaxUnits();
                    // unitNeededFromAdjacent holds the value of units required from the current battalion
                    double exactUnitNeededFromAdjacent = (float) unitsRequired / 2;
                    int unitNeededFromAdjacent = (int) Math.ceil(exactUnitNeededFromAdjacent);

                    // canBeAdded holds the value of units which can be substituted without exhausting adjacent
                    Integer canBeAdded = adjacentBattalion.canBeAddedFrom(unitNeededFromAdjacent);
                    adjacentBattalion.addToUnitsDeployed(canBeAdded);

                    currentBattalion.decrementUnitsDeployed(canBeAdded * 2>unitsRequired?unitsRequired:canBeAdded*2);

                    //Update battalion Composition
                    battalionComposition.put(adjacentBattalionType, adjacentBattalion);
                    battalionComposition.put(battalionTypeList.get(index), currentBattalion);
                }
            }

            currentBattalion.setUnitsDeployedByCapacity();
            battalionComposition.put(battalionTypeList.get(index), currentBattalion);
        }
        this.battalions = battalionComposition;
    }

    public String decideOutcome() {
        Boolean lost = false;
        for (String battalionType : battalions.keySet()) {
            Battalion currentBattalion = battalions.get(battalionType);
            addBattalionComposition(battalionType, currentBattalion.getUnitsDeployed());
            if (!lost) {
                lost = currentBattalion.getExhausted();
            }
        }
        return getArmyComposition(!lost);
    }

    private void addBattalionComposition(String battalionType, Integer units) {
        this.armyComposition.add(units.toString() + battalionType);
    }

    private String getArmyComposition(Boolean win) {
        String army = win ? "WINS " : "LOSES ";
        return army.concat(String.join(" ", this.armyComposition));
    }
}