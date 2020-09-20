
package geektrust.in.war.dto;

public class Battalion {
    /**
     * This class is being used to calculate and hold the data of
     * @param maxUnits lengaburu battalion can have and the
     * @param falicorniaUnits they need to fight with depending on which it calculates
     * @param unitsDeployed which has minimum no. of units lengaburu can deploy
     * @param exhausted denotes if the battalion units lengaburu has can suffice to defeat falicornian army
     *
     *
     * Note: It only holds data of one single battalion and doesn't do battalion substitution
     */
    private final Integer maxUnits;
    private final Integer falicorniaUnits;
    private Integer unitsDeployed;
    private Boolean exhausted;

    /**
     * @param maxUnits  maxUnits of the battalion lengaburu's army has
     * @param falicorniaUnits falicornia battalion units Deployed
     */
    public Battalion(Integer maxUnits, Integer falicorniaUnits) {
        this.maxUnits = maxUnits;
        this.falicorniaUnits = falicorniaUnits;
        calculateQuantity();
    }

    private void calculateQuantity(){
        Integer unitsDeployed = (int) Math.ceil(falicorniaUnits/2);
        setExhausted(unitsDeployed>maxUnits);
        setUnitsDeployed(unitsDeployed);
    }

    public void addToUnitsDeployed(Integer addUnits){
        this.unitsDeployed+=addUnits;
        setExhausted(unitsDeployed>maxUnits);
    }
    public void decrementUnitsDeployed(Integer decrement){
        this.unitsDeployed-=decrement;
        setExhausted(unitsDeployed>maxUnits);
    }

    private void setUnitsDeployed(Integer unitsDeployed) {
        this.unitsDeployed = unitsDeployed;
        setExhausted(unitsDeployed>maxUnits);
    }

    public Integer canBeAddedFrom(Integer needsToBeAdded){
        Integer canBeAdded =  this.maxUnits - this.unitsDeployed;
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

    public void setExhausted(Boolean exhausted) {
        this.exhausted = exhausted;
    }
}
