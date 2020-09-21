
package geektrust.in.war.dto;


/**
 * This is a DTO class,
 * It's used to contain the data of each battalion for our army which needs to fight the falicornian's army
 * It helps in figuring us out whether the battalion is exhausted given the current troops deployed and that
 * helps us later to substitute the battalions respectively
 *
 * @author vimalk29
 * @version 1.0
 */
public class Battalion {
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

    /**
     * Calculates and sets how many units needs to be deployed against opponent irrespective of max limits
     */
    private void calculateUnits() {
        Integer unitsDeployed = ((int) Math.ceil((double) falicorniaUnits / 2));
        setUnitsDeployed(unitsDeployed);
    }

    /**
     * Calculates the units which can actually be deployed
     */
    public void setUnitsDeployedByCapacity() {
        setUnitsDeployed(this.unitsDeployed > this.maxUnits ? this.maxUnits : this.unitsDeployed);
    }

    /**
     * @param addUnits is the number of units which is added to the Units deployed
     */
    public void addToUnitsDeployed(Integer addUnits) {
        this.unitsDeployed += addUnits;
        setExhausted();
    }

    /**
     * Decrements the units deployed
     *
     * @param decrement is the number of units to be decremented
     */
    public void decrementUnitsDeployed(Integer decrement) {
        this.unitsDeployed -= decrement;
        setExhausted();
    }

    /**
     * sets unitDeployed to an exact value
     *
     * @param unitsDeployed is the number of units, unit Deployed has to be set to
     */
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
        return canBeAdded > needsToBeAdded ? needsToBeAdded : canBeAdded;
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

    /**
     * Calculates and sets exhausted when units deployed is more than max Units
     */
    private void setExhausted() {
        this.exhausted = this.unitsDeployed > this.maxUnits;
    }

    public String toString() {
        return "{MaxUnits: " + maxUnits + " falicorniaUnits: " +
                falicorniaUnits + " unitsDeployed: " + unitsDeployed + " exhausted: " + exhausted + "}";
    }
}
