/**
 * Coin machine project
 * @author riley kim
 * @version 03/30/23
 */
public abstract class Coin {
    public abstract double getValue(); // returns .01, .05, ..., .5, 1.0 based on implementing class object's value
    public abstract String getName(); // returns "penny", "nickel", ...,
    // "half dollar", "dollar"
    public String getPluralName() {// define in the class: pennies, dimes...
        // penny = pennies
        // nickels = nickels
        // dime = dimes
        // quarter = quarters
        return (getName().equals("penny") ? "pennies" : getName() + "s");
    }

    public boolean equals(Object other){
        if(other instanceof Coin){
            return this.getValue()== ((Coin)other).getValue();

        }
        return false;
    }
}
