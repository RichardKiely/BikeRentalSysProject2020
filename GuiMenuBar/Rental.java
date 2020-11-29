package GuiMenuBar;

//Rental.java
/*An instantiable class definition for an Bike Rental which also demonstrates
the OO concept of aggregation*/

import java.util.GregorianCalendar;

public class Rental {

    private Bike bikeRented;
    private GregorianCalendar dateRented;
    private GregorianCalendar dateOfReturn;

    public Rental(Bike bikeRented, GregorianCalendar dateRented, GregorianCalendar dateOfReturn) {
        setBikeRented(bikeRented);
       setDateRented(dateRented);
       setDateOfReturn(dateOfReturn);
    }

    public Bike getBikeRented() {
        return bikeRented;
    }

    public void setBikeRented(Bike bikeRented) {
        this.bikeRented = bikeRented;
    }

    public GregorianCalendar getDateRented() {
        return dateRented;
    }

    public void setDateRented(GregorianCalendar dateRented) {
        this.dateRented = dateRented;
    }

    public GregorianCalendar getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(GregorianCalendar dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public String toString() {
        return
                " BikeRented: " + getBikeRented() +
                " DateRented: " + getDateRented() +
                " Date Of Return: " + getDateOfReturn() + "\n" ;
    }
}
