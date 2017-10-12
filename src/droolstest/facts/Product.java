package droolstest.facts;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Encja, w terminologii droolsa - fakt
 * @author aaugustyniak
 */
public class Product {

    protected int amount;
    protected float price;
    protected String name;
    protected float onePrice;
    protected Timestamp usefulness;

    public Product() {
        name = "";
        amount = 0;
        onePrice = 0;
        Calendar calendar = Calendar.getInstance();
        usefulness = new Timestamp(calendar.getTimeInMillis());
    }

    public Product(String aName, int aAmount, float aOnePrice) {
        amount = aAmount;
        onePrice = aOnePrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(float onePrice) {
        this.onePrice = onePrice;
    }

    public Timestamp getUsefulness() {
        return usefulness;
    }

    public void setUsefulness(Timestamp usefulness) {
        this.usefulness = usefulness;
    }

    public float getAllPrice() {
        return amount * onePrice;
    }

    public String getDescription() {
        return "name: " + name + "Amount: " + amount + " one price: " + onePrice + " price: " + price + " all price: " + getAllPrice();
    }

    @Override
    public String toString() {
        return this.getDescription();
        
    }

}
