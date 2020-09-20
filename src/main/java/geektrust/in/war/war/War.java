package geektrust.in.war.war;

import java.io.Console;
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

                    currentBattalion.decrementUnitsDeployed(canBeAdded * 2> unitsRequired ? unitsRequired : canBeAdded * 2);
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

    public String decideOutcome() {
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

    private void addBattalionComposition(String battalionType, Integer units) {
        this.armyComposition.add(units.toString() + battalionType);
    }

    private String getArmyComposition(Boolean win) {
        String army = win ? "WINS " : "LOSES ";
        return army.concat(String.join(" ", this.armyComposition));
    }
}

/*





Lengaburu {
    "H": 100,
    "E": 50,
    "AT": 10,
    "SG": 5,
}


\
 \
 falicornia {
    "H": 150,
    "E": 96,
    "AT": 26,
    "SG": 8,
}
    |
    |
    |

armyComposition{
    "H": Battalions{
            maxUnits= 100;
            falicorniaUnits = 150;
            unitsDeployed = 75;
            exhausted= false;
         },
    "E": Battalions{
            maxUnits=50;
            falicorniaUnits= 96;
            unitsDeployed = 50;
            exhausted= false;
         },
    "AT": Battalions{
            maxUnits=10;
            falicorniaUnits = 26;
            unitsDeployed = 10;
            exhausted= false;
         },
    "SG": Battalions{
            maxUnits=5;
            falicorniaUnits = 8;
            unitsDeployed= 5;
            exhausted= false;
         },
    }

    length = 4
    [ "H", "E", "AT", "SG" ]

    unitRequired = unitDeployed - maxUnits                                  (3) needed for current B
    unitFromAdj = unitRequired*2                                            (6) chahiye from adjacent

    canBeAdded = (50-48)>unitFromAdj?unitFromAdj: (50-48)                   (2) milegi adjacent




    unitRequired = unitDeployed - maxUnits                                  (2) needed for current B
    unitFromAdj = unitRequired/2                                            (1) chahiye from adjacent

    canBeAdded = (5-4)>unitFromAdj?unitFromAdj: (5-4)                       (1) milegi adjacent



    unitRequired = unitDeployed - maxUnits                                  (21) needed for current B
    unitFromAdj = unitRequired/2                                            (11) chahiye from adjacent

    canBeAdded = (5-4)>unitFromAdj?unitFromAdj: (5-4)                       (1) milegi adjacent














    if{
    1 2 3
    }
    if{
    0 1 2
    }






        H<E<AT<SG
        8=4=2=1

if AT=2
E=4
SG=1


*/