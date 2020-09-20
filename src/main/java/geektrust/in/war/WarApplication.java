
package geektrust.in.war;

import java.io.IOException;
import java.util.LinkedHashMap;

import geektrust.in.war.dto.Battalion;
import geektrust.in.war.repositoryservice.ArmyService;
import geektrust.in.war.util.FileUtility;
import geektrust.in.war.war.War;

public class WarApplication {

    /**
     * Assumes that the args will have file location in it's 0th Index
     * @param args contains arguments when the app runs
     * @throws IOException Exceptions if any while opening a file
     */
    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0]; //Read file Input Location

        String inputString =  FileUtility.fileToString(inputFilePath); //Read file as a String

        //Contains Falicornia's Army Coposition
        LinkedHashMap<String,Integer> falicorniaArmy = getFalicorniaArmyAsMap(inputString);

        ArmyService armyService = new ArmyService(falicorniaArmy);

        // Obtains battalion composition of Lengaburu's Army against falicornia without substitution
        LinkedHashMap<String, Battalion> battalionComposition= armyService.getBattalionComposition();
        War war = new War();
        war.substituteExhaustedBattalions(battalionComposition);

        System.out.println(war.decideOutcome());
    }

    /**
     * @param content is taken as parameter which is then transformed into a LinkedHashMap
     * @return army
     */
    private static LinkedHashMap<String, Integer> getFalicorniaArmyAsMap(String content){
        LinkedHashMap<String, Integer> army = new LinkedHashMap<>();
        final String[] input= content.trim().split("\\s+");
        // Initializing loop from index 1 to
        // Ignore the first word and traverse all the army composition
        for(int i=1;i<input.length;++i){
            // Get Characters
            final String battalionType= input[i].replaceAll("[^A-Za-z]", "");
            // Get Number
            final String units= input[i].replaceAll("[^0-9]", "");
            army.put(battalionType, Integer.parseInt(units));
        }
        return army;
    }
}
