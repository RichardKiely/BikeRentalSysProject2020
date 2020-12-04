package GuiMenuBar;


import java.io.Serializable;

/**
 * * An instantiable class which defines a Bike. This one contains exception-handling code in some
 *  * of its mutators to prevent bad input
 *  * @author Richard Kiely.
 */

public class Bike implements Serializable {

    private String type;
    private int gears;
    private String colour;
    private String model;
    private double rates;
    private int bikeId;
    private boolean rented;
    private static int count;

    /**
     * Bike 6-argument constructor. Calls the 6 mutators and the incrementCount() method  to
     * initialise the attributes of a Bike ID object. The Bike ID is
     * set internally using the value of the count attribute, to ensure unique Bike ID values.
     * @param type the type of the bike
     * @param gears the gears of the bike
     * @param colour the color of the bike
     * @param model the model of the bike
     * @param rates the rate of the bike
     * @param rented the boolean rented is true or false depending on the bike being rented or not.
     */


    public Bike(String type, int gears, String colour, String model, double rates, boolean rented) {
        setType(type);
        setGears(gears);
        setColour(colour);
        setModel(model);
        setRates(rates);
        setRented(rented);
        incrementCount();
        setBikeId(count);
    }

    /**
     * Method to increment the static count attribute of the class, this is to ensure that every
     * Bike object will have a unique bikeID value, as it tracks the value of this attribute.
     */

    private void incrementCount(){
        count++;
    }

    /**
     * Method to get the type of a Bike object
     * @return a String value specifying the type of Bike object
     */

    public String getType() {
        return type;
    }

    /**
     * Method to set the type of Bike object
     * @param type the type of Bike
     * @throws IllegalArgumentException In the case of an invalid input
     */

    public void setType(String type) {

        if(type==null || type.equals(""))
            throw new IllegalArgumentException("The bike type must be given some value\n");

        this.type = type;
    }

    /**
     * Method to get the gears a Bike object has.
     * @return an integer value specifying the amount of gears a Bike object has
     */

    public int getGears() {
        return gears;
    }

    /**
     * Method to set the gears of a Bike object
     * @param gears the number gears of the Bike object
     * @throws IllegalArgumentException In the case of an invalid input
     */

    public void setGears(int gears) {
        if(gears<0)
            throw new IllegalArgumentException("The bike must have at least one gear\n");
        this.gears = gears;
    }

    /**
     * Method to get the color of a Bike object
     * @return a String value specifying the color of Bike object
     */

    public String getColour() {
        return colour;
    }
    /**
     * Method to set the colour of a Bike object
     * @param colour the colour of the Bike
     */

    public void setColour(String colour) {
        this.colour = colour;
    }
    /**
     * Method to get the model of a Bike object
     * @return a String value specifying the model of Bike object
     */
    public String getModel() {
        return model;
    }

    /**
     * Method to set the model of a Bike object
     * @param model the model of the Bike object
     * @throws IllegalArgumentException In the case of an invalid input
     */

    public void setModel(String model) {
        if(model==null || model.equals(""))
            throw new IllegalArgumentException("The bike model must be given some input\n");
        this.model = model;
    }
    /**
     * Method to get the rates of a Bike object
     * @return a double value specifying the rates of a Bike object
     */

    public double getRates() {

        return rates;
    }
    /**
     * Method to set the rate of a Bike object
     * @param rates the rate of the Book
     * @throws IllegalArgumentException In the case of an invalid rate
     */

    public void setRates(double rates) {
        if(rates<0)
            throw new IllegalArgumentException("The rental price must be a positive number\n");
        this.rates = rates;
    }

    /**
     * Method to get the ID of a Bike object
     * @return an integer value specifying the ID of a Bike object
     */
    public int getBikeId() {
        return bikeId;
    }
    /**
     * Method to set the bikeID of a Bike object
     * @param bikeId the bikeID number of the Bike
     */
    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }
    /**
     * Method to get the boolean value of a Bike object
     * @return a boolean value true/false if a bike object is rented or not.
     */
    public boolean isRented() {
        return rented;
    }
    /**
     * Method to set rented true/false if a bike object is rented or not.
     * @param rented sets rented true/false
     */

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    /**
     * Method to get the state of a Bike object
     * @return a String value specifying the state of a Bike object
     */
    @Override
    public String toString() {
        return "BikeId: " + getBikeId() +
                ", Type: " + getType() +
                ", Gears: " + getGears()+
                ", Colour: " + getColour() +
                ", Model: " + getModel()+
                ", Rates: " + getRates() + "\n";
    }
}