package geektrust.in.war.util;

import java.util.LinkedHashMap;

public class StringUtil {

    /**
     * @param content is taken as parameter which is then transformed into a LinkedHashMap
     * @return army
     */
    public static LinkedHashMap<String, Integer> getArmyAsMapFromString(String content) {
        LinkedHashMap<String, Integer> army = new LinkedHashMap<>();
        final String[] input = content.trim().split("\\s+");
        // Initializing loop from index 1 to
        // Ignore the first word and traverse all the army composition
        for (int i = 1; i < input.length; ++i) {
            // Get Characters
            final String battalionType = input[i].replaceAll("[^A-Za-z]", "");
            // Get Number
            final String units = input[i].replaceAll("[^0-9]", "");
            army.put(battalionType, Integer.parseInt(units));
        }
        return army;
    }
}
