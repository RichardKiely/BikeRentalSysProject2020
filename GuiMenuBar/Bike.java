package GuiMenuBar;

public class Bike {

    private String type;
    private int gears;
    private String colour;
    private String model;
    private double rates;
    private int bikeId;
    private boolean rented;
    private static int count;



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

    private void incrementCount(){
        count++;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        if(gears>0)
        this.gears = gears;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        if(rates>0)
        this.rates = rates;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return "BikeId: " + getBikeId() +
                "\n Type: " + getType() +
                "\n Gears: " + getGears()+
                "\n Colour: " + getColour() +
                "\n Model: " + getModel()+
                "\n Rates: " + getRates();
    }
}
