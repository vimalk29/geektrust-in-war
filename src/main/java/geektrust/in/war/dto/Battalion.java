
package geektrust.in.war.dto;

public class Battalion {
    /**
     * This class is being used to calculate and hold the data of battalions
     *
     * maxUnits is the number of units lengaburu battalion has
     * falicorniaUnits is the no. of units of this battalion falicornia has deployed
     * unitsDeployed is the minimum no. of units lengaburu should deploy to win
     * exhausted denotes if the battalion units lengaburu is less than required to defeat falicornian army
     *
     * Note: It only holds data of one single battalion and doesn't process battalion substitution
     */
    private final Integer maxUnits;
    private final Integer falicorniaUnits;
    private Integer unitsDeployed;
    private Boolean exhausted;

    /**
     * @param maxUnits        maxUnits of the battalion lengaburu's army has
     * @param falicorniaUnits falicornia battalion units Deployed
     */
    public Battalion(Integer maxUnits, Integer falicorniaUnits) {
        this.maxUnits = maxUnits;
        this.falicorniaUnits = falicorniaUnits;
        calculateUnits();
    }

    private void calculateUnits() {
        Integer unitsDeployed = ((int) Math.ceil((double) falicorniaUnits / 2));
        setUnitsDeployed(unitsDeployed);
    }

    public void setUnitsDeployedByCapacity() {
        setUnitsDeployed(this.unitsDeployed > this.maxUnits ? this.maxUnits : this.unitsDeployed);
    }

    public void addToUnitsDeployed(Integer addUnits) {
        this.unitsDeployed += addUnits;
        setExhausted();
    }

    public void decrementUnitsDeployed(Integer decrement) {
        this.unitsDeployed -= decrement;
        setExhausted();
    }

    private void setUnitsDeployed(Integer unitsDeployed) {
        this.unitsDeployed = unitsDeployed;
        setExhausted();
    }

    /**
     * Calculates and returns number of unit which can be added to this battalion
     *
     * @param needsToBeAdded holds the number of units required
     * @return the number of units which can be added
     */
    public Integer canBeAddedFrom(Integer needsToBeAdded) {
        Integer canBeAdded = this.maxUnits - this.unitsDeployed;
        return canBeAdded > needsToBeAdded ? canBeAdded : needsToBeAdded;
    }


    public Integer getMaxUnits() {
        return maxUnits;
    }

    public Integer getFalicorniaUnits() {
        return falicorniaUnits;
    }

    public Integer getUnitsDeployed() {
        return unitsDeployed;
    }


    public Boolean getExhausted() {
        return exhausted;
    }

    private void setExhausted() {
        this.exhausted = this.unitsDeployed > this.maxUnits;
    }

    public String toString() {
        return "{MaxUnits: " + maxUnits + " falicorniaUnits: " +
                falicorniaUnits + " unitsDeployed: " + unitsDeployed + " exhausted: " + exhausted + "}";
    }
}
