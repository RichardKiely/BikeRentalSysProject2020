package GuiMenuBar;

//Rental.java
/*An instantiable class definition for an Bike Rental which also demonstrates
the OO concept of aggregation*/

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Rental implements Serializable {

    private Bike bikeRented;
    private int noOfDays=0;
    private double rentalFeeDaily;
    private GregorianCalendar dateRented;


    public Rental(Bike bikeRented,int noOfDays,GregorianCalendar dateRented) {
        setBikeRented(bikeRented);
        setNoOfDays(noOfDays);
        setDateRented(dateRented);


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

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {

        this.noOfDays = noOfDays;
    }


    public double getTotalRentalFee()
    {
        return getNoOfDays() * getRentalFeeDaily(getBikeRented().getType());
    }

    public double getRentalFeeDaily(String type)
    {
        switch (type)
        {
            case "MB":
                rentalFeeDaily = 25;
                break;
            case "EL":
                rentalFeeDaily = 30;
                break;
            case "RC":
                rentalFeeDaily = 20;
                break;
            case "HB":
                rentalFeeDaily = 22;
                break;
            case "Kids":
                rentalFeeDaily = 10;
                break;
        }
        return rentalFeeDaily;
    }


    @Override
    public String toString() {
       String str="";

             str +=   " BikeRented: " + getBikeRented() +
                " DateRented: " ;
             if(dateRented==null) {
                 str += "Date Rented not specified";
             }
            else {
                 str += getDateRented().get(Calendar.YEAR) + "-" + (dateRented.get(Calendar.MONTH)+1) + "-" + dateRented.get(Calendar.DATE) +
                         " Number of Days: " +   getNoOfDays() + " Total Rental Fee " + getTotalRentalFee();

             }


        return str;
    }
}
