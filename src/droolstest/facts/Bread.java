package droolstest.facts;

/**
 *
 * @author aaugustyniak
 */
public class Bread extends Product {

    private final float weight;

    public static enum Kinds {

        PSZENNY, ZYTNI, ZIARNISTY
    };
    Kinds kind;

    public Bread(String aName, int aAmount, float aOnePrice, float aWeight, Bread.Kinds aKind) {
        amount = aAmount;
        onePrice = aOnePrice;
        weight = aWeight;
        kind = aKind;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " waight: " + weight + " kind: " + kind;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
