package droolstest.facts;

/**
 *
 * @author aaugustyniak
 */
public class Milk extends Product {

    private final float liters;
    private final int percentages;

    public Milk(String aName, int aAmount, float aOnePrice, float aLiters, int aPercentages) {
        amount = aAmount;
        onePrice = aOnePrice;
        liters = aLiters;
        percentages = aPercentages;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " liters: " + liters + " percentages: " + percentages;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
