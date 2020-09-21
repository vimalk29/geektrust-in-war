package geektrust.in.war;

import java.io.IOException;
import java.util.LinkedHashMap;

import geektrust.in.war.dto.Battalion;
import geektrust.in.war.repositoryservice.ArmyService;
import geektrust.in.war.util.FileUtility;
import geektrust.in.war.util.StringUtil;
import geektrust.in.war.war.War;

/**
 * Contains the main method which then handles all the logic and steps to solve the given problem
 *
 * @author vimalk29
 * @version 1.0
 */
public class WarApplication {
    /**
     * @param args contains arguments when the app runs
     * @throws IOException Exceptions if any while opening a file
     */
    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {

        String inputFilePath = args[0]; //Read file Input Location

        // Get our max army composition
        LinkedHashMap<String, Integer> maximumUnitsAvailable =
                StringUtil.getArmyAsMapFromString("ARMY 100H 50E 10AT 5SG");

        //Set army service with respect to our army limit
        ArmyService armyService = new ArmyService(maximumUnitsAvailable);

        String inputString = FileUtility.fileToString(inputFilePath); //Read file as a String
        //Contains Opponent's Army Composition
        LinkedHashMap<String, Integer> opponentArmy = StringUtil.getArmyAsMapFromString(inputString);

        // Obtains battalion composition of our Army against opponent without substitution
        LinkedHashMap<String, Battalion> requiredBattalionComposition =
                armyService.getBattalionComposition(opponentArmy);

        //Create a War class object
        War war = new War();

        //Substitute exhausted battalions
        war.substituteExhaustedBattalions(requiredBattalionComposition);

        //Print the outcome of the battle to the console
        System.out.print(war.decideBattleOutcome());
    }

}
