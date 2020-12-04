package GuiMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends JFrame implements ActionListener {

   private static ArrayList<Bike> allBikesList;
   private static ArrayList<Rental> allRentals;


    JMenuBar menuBar;
    JMenu bike;
    JMenu rental;
    JMenu admin;

    JMenuItem AmendBike;
    JMenuItem AddBike;
    JMenuItem RemoveBike;
    JMenuItem ViewBike;
    JMenuItem rentBikeBtn;
    JMenuItem returnRental;
    JMenuItem cancelRental;
    JMenuItem adminYearlyRev;
    JMenuItem adminAnalyse;

    JPanel panel1 = new JPanel();
    JLabel label1 = new JLabel();

    private static File file;

    public Main() {

        super(" Tralee Bike Rentals ");

        //Icon Image top left of gui
        setIconImage(new ImageIcon("./src/bikeImage.png").getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null); //puts the gui to the center of the screen.
        this.setLayout(new GridBagLayout()); //centers the image.

        // main bike image
        label1.setIcon(new ImageIcon("./src/bikeImage.png"));
        panel1.add(label1);
        add(panel1);


        menuBar = new JMenuBar();

        bike = new JMenu("Bikes");
        bike.setMnemonic(KeyEvent.VK_B); // ALT B shortcut for bikes

        rental = new JMenu("Rentals");
        rental.setMnemonic(KeyEvent.VK_R);// ALT R shortcut for rentals

        admin = new JMenu("Admin");
        admin.setMnemonic(KeyEvent.VK_A); // ALT A shortcut for admin


        AmendBike = new JMenuItem("Amend");
        AmendBike.setMnemonic(KeyEvent.VK_A); //A shortcut for Amend a bike
        AddBike = new JMenuItem("Add");
        AddBike.setMnemonic(KeyEvent.VK_D);// B shortcut for Add bike
        RemoveBike = new JMenuItem("Remove");
        RemoveBike.setMnemonic(KeyEvent.VK_R);// R shortcut for Remove
        ViewBike = new JMenuItem("View");
        ViewBike.setMnemonic(KeyEvent.VK_V);// R shortcut for View

        AmendBike.addActionListener(this);
        AddBike.addActionListener(this);
        RemoveBike.addActionListener(this);
        ViewBike.addActionListener(this);

        bike.add(AmendBike);
        bike.add(AddBike);
        bike.add(RemoveBike);
        bike.add(ViewBike);

        rentBikeBtn = new JMenuItem("Rent Bike");
        rentBikeBtn.setMnemonic(KeyEvent.VK_R); //R shortcut for Rent bike
        returnRental = new JMenuItem("Return Bike");
        returnRental.setMnemonic(KeyEvent.VK_T); //T shortcut for return bike
        cancelRental = new JMenuItem("Cancel Rental");
        cancelRental.setMnemonic(KeyEvent.VK_C); //C shortcut for cancel rental

        rentBikeBtn.addActionListener(this);
        returnRental.addActionListener(this);
        cancelRental.addActionListener(this);

        rental.add(rentBikeBtn);
        rental.add(returnRental);
        rental.add(cancelRental);

        adminYearlyRev = new JMenuItem("List Yearly Revenue");
        adminYearlyRev.setMnemonic(KeyEvent.VK_L); //L shortcut for List yearly Rev
        adminAnalyse = new JMenuItem("Analyse Revenue");
        adminAnalyse.setMnemonic(KeyEvent.VK_A); //A shortcut for Analyse

        admin.addActionListener(this);
        admin.addActionListener(this);

        admin.add(adminYearlyRev);
        admin.add(adminAnalyse);

        menuBar.add(bike);
        menuBar.add(rental);
        menuBar.add(admin);
        menuBar.setBackground(new Color(0, 100, 200));

        this.setJMenuBar(menuBar);
        this.setVisible(true);

        createFileMenu();

        readRentalsFromFile();

    }

        public static void main(String[] args) throws Exception {

            new Main();


            Bike b1 = new Bike("MB", 21, "Blue", "Trek", 25, true);
            Bike b2 = new Bike("RC", 18, "White", "Giant", 20, true);
            Bike b3 = new Bike("El", 18, "White", "Cube", 30, true);
            Bike b4 = new Bike("Kids", 10, "Red", "Rad power", 10, true);
            Bike b5 = new Bike("HB", 21, "Blue", "Raleigh Activator", 22, true);

            allBikesList = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5));

            Rental r1 = new Rental(b1,3,new GregorianCalendar(2020,12,03));
            Rental r2 = new Rental(b2,3,new GregorianCalendar(2020,12,03));
            Rental r3 = new Rental(b3,3,new GregorianCalendar(2020,12,03));



            if(!file.exists())
                allRentals = new ArrayList<>(Arrays.asList(r1,r2,r3));

        }
    public static void saveRentalsToFile() throws IOException {


        File outFile = new File("rentals.data");
        FileOutputStream outStream = new FileOutputStream(outFile);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


        for(Rental r: allRentals){
            System.out.println(r);
        }

        objectOutStream.writeObject(allRentals);
        objectOutStream.close();
        outStream.close();

    }
    public void createFileMenu() {


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        saveRentalsToFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Data saved successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);

                    System.exit(0);
                }
            }
        });
    }

    public  void readRentalsFromFile() {

        try {

             file = new File("rentals.data");

            if(file.exists()) {

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allRentals = (ArrayList<Rental>) is.readObject();
                is.close();

                for(Rental r: allRentals){
                    System.out.println(r);
                }

                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName()+ " file", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null,"Class of object deserialized not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cnfe.printStackTrace();
        }
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public void saveBikesToFile() throws IOException {


        File outFile = new File("bikes.data");
        FileOutputStream outStream = new FileOutputStream(outFile);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
        objectOutStream.writeObject(allBikesList);
        outStream.close();

    }


    public static void readBikesFromFile() throws IOException, ClassNotFoundException {


        File inFile = new File("bikes.data");
        FileInputStream inStream = new FileInputStream(inFile);
        ObjectInputStream objectInStream = new ObjectInputStream(inStream);
        allBikesList = (ArrayList<Bike>) objectInStream.readObject();
        inStream.close();
    }


    public static void amendProduct(ArrayList<Bike> allBikesList){

        ArrayList<Bike> foundBike = new ArrayList<>();
        String searchKey = JOptionPane.showInputDialog("Please enter the name of the bike type you wish to amend");

        for(Bike bk: allBikesList)
            if(bk.getType().toLowerCase().contains(searchKey.toLowerCase()))
                foundBike.add(bk);

        String text="";

        for (Bike bk : foundBike)
            if (bk != null) {
                text += bk + "\n";
            }

        int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following Bike Types matched your search phrase\n\n" + text +
                "\n\nEnter the id of the one do you want to amend"));

        Bike BikeToAmend=null;

        for (Bike bk : foundBike)
            if (bk != null && bk.getBikeId()==searchID)
                BikeToAmend = bk;

        String amendChoice = JOptionPane.showInputDialog("The details of the Bike you wish to amend are:\n\n" +
                BikeToAmend + "\n\n1. Amend Type\n2. Amend Gears" +
                "\n3. Amend Color \n\n.4 Amend Model " +
                "\n\n5. Amend Rates \n\nPlease enter your choice");

        int amendChoiceAsInt = Integer.parseInt(amendChoice);

        while(amendChoiceAsInt<1 || amendChoiceAsInt>5){
            amendChoice = JOptionPane.showInputDialog("The details of the Bike you wish to amend are:\n\n" +
                    BikeToAmend + "\n\n1. Amend Type\n2. Amend Gears" +
                    "\n3. Amend Color \n\n.4 Amend Model " +
                    "\n\n5. Amend Rates\n\nInvalid choice entered!! Must be a value between 1 and 5 inclusive");

            amendChoiceAsInt = Integer.parseInt(amendChoice);
        }

        switch(amendChoice){
            case "1":
                String newType = JOptionPane.showInputDialog("Please enter the new name for the Bike Type");

                BikeToAmend.setType(newType);

                break;

            case "2":
               int  newGears = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new amount of gears the bike has"));
                BikeToAmend.setGears(newGears);

                break;
            case "3":
                String newColor = JOptionPane.showInputDialog("Please enter the new name for the Bike Type");

                BikeToAmend.setColour(newColor);

                break;

            case "4":
                String newModel = JOptionPane.showInputDialog("Please enter the new model for the Bike Type");

                BikeToAmend.setModel(newModel);

                break;

            case "5":
                int  newRate = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new amount of rate the bike costs"));
                BikeToAmend.setRates(newRate);

                break;


        }
        JOptionPane.showMessageDialog(null,"Bike details now amended!",
                "Bike Amended",JOptionPane.INFORMATION_MESSAGE);
        foundBike.clear();
    }
    public static void addBike(ArrayList<Bike> allBikesList) {


        String addType = JOptionPane.showInputDialog("Please enter the new name for the Bike Type");

        int  addGears = Integer.parseInt(JOptionPane.showInputDialog("\nPlease enter the amount of gears the bike has"));

        String addColor = JOptionPane.showInputDialog("\nPlease enter the new name for the Bike Type");

        String addModel = JOptionPane.showInputDialog("\nPlease enter the new model for the Bike Type");

        double  addRate = Double.parseDouble(JOptionPane.showInputDialog("\nPlease enter the new amount of rate the bike costs"));


       Bike b6 = new Bike(addType,addGears,addColor,addModel,addRate,true);

       allBikesList.add(b6);
      JOptionPane.showMessageDialog(null,"Bike now created and added to array list!",
             "Bike Added",JOptionPane.INFORMATION_MESSAGE);

    }

    public static void removeBike(ArrayList<Bike> allBikesList) {

        ArrayList<Bike> foundBike = new ArrayList<>();
        String searchKey = JOptionPane.showInputDialog("Please enter the type of bike you wish to remove example...(MB,RC,El,Kids,HB)");

        for(Bike bk: allBikesList)
            if(bk.getType().toLowerCase().contains(searchKey.toLowerCase()))
                foundBike.add(bk);

        String text="";

        for (Bike bk : foundBike)
            if (bk != null) {
                text += bk + "\n";
            }

        int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following bikes matched your search \n\n" + text +
                "\n\nPlease enter the id of the one you want to remove"));

        Bike bikeToRemove=null;

        for (Bike bk : foundBike)
            if (bk != null && bk.getBikeId() ==searchID)
                bikeToRemove = bk;

        int removeChoice = JOptionPane.showConfirmDialog(null,"The details of the bike you wish to remove are:\n\n" +
                bikeToRemove + "\n\nAre you sure you wish to remove this bike?","Bike Removal Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);

        if(removeChoice==JOptionPane.YES_OPTION) {
            allBikesList.remove(bikeToRemove);
            JOptionPane.showMessageDialog(null, "Bike now removed from array list!",
                    "Bike Removed", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Bike removal cancelled",
                    "Bike Not Removed", JOptionPane.INFORMATION_MESSAGE);

        foundBike.clear();
    }


   public static void viewProducts(ArrayList<Bike> allBikesList) {
        String allBikesData = "";
        Bike bk;

        Iterator<Bike> iterator = allBikesList.iterator();

        while (iterator.hasNext()) {
            bk = iterator.next();
            if (bk != null)
                allBikesData += bk + "\n";
        }

        JOptionPane.showMessageDialog(null, allBikesData,
                "List of all Bikes", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void rentBike(ArrayList<Bike> allBikesList){

        int i = 0;
        ArrayList<Bike> rentBike = new ArrayList<>();



        String searchKey = JOptionPane.showInputDialog("Please enter the type of bike you wish to rent example...(MB,RC,El,Kids,HB)");

        int noOfDays =Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of days you wish to rent the bike for? "));

        String dateRented = JOptionPane.showInputDialog("Please enter the date you wish to rent the bike: dd-MM-yyyy format");
        int day = Integer.parseInt(dateRented.substring(0, 2));
        int month = Integer.parseInt(dateRented.substring(3, 5));
        int year = Integer.parseInt(dateRented.substring(6, 10));

        for(Bike bk: allBikesList)
            if(bk.getType().toLowerCase().contains(searchKey.toLowerCase()))
                rentBike.add(bk);

        String str="";

        for (Bike bk : rentBike)
            if (bk != null) {
                str += bk + "\n";
            }

        int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following bikes matched your search \n\n" + str +
                "\n\nPlease enter the id of the one you want to rent "));

        Bike bikeToRent=null;

        for (Bike bk : rentBike)
            if (bk != null && bk.getBikeId() ==searchID)
                bikeToRent = bk;


        int rentalChoice = JOptionPane.showConfirmDialog(null,"The details of the bike you wish to rent are:\n\n" +
                bikeToRent + "\n\nAre you sure you wish to rent this bike?","Bike Rental Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);

        Rental r2 = new Rental(bikeToRent,noOfDays,new GregorianCalendar(year,month,day));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        allRentals.add(r2);
        dateFormatter.format(allRentals.get(i).getDateRented().getTime());

        JOptionPane.showMessageDialog(null, "The following Booking details has been added to the system:" +
                "\n" + r2);

    }
    public void cancelRental(ArrayList<Rental> allRentals){
        String allRentalData = "";
        Rental rt;

        Iterator<Rental> iterator = allRentals.iterator();

        while (iterator.hasNext()) {
            rt = iterator.next();
            if (rt != null)
                allRentalData += rt + "\n";
        }


        int searchID = Integer.parseInt(JOptionPane.showInputDialog("Here are the rentals \n\n" + allRentalData +
                "\n\nPlease enter the id of the one you want to cancel "));

        Rental cancelRental=null;

        for (Rental c : allRentals)
            if (c.getBikeRented().getBikeId() == searchID){
                cancelRental = c;
            break;
        }
        int removeChoice = JOptionPane.showConfirmDialog(null,"The details of the bike you wish to remove are:\n\n" +
                cancelRental + "\n\nAre you sure you wish to remove this bike?","Bike Removal Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);

        if(removeChoice==JOptionPane.YES_OPTION) {
            allRentals.remove(cancelRental);
            JOptionPane.showMessageDialog(null, "Rental has now been cancelled",
                    "Rental Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Rental not cancelled",
                    "Rental not cancelled ", JOptionPane.INFORMATION_MESSAGE);


    }

    @Override
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == AmendBike) {
               amendProduct(allBikesList);
            }
            if (e.getSource() == AddBike) {
                    addBike(allBikesList);
            }
            if (e.getSource() == RemoveBike) {
                removeBike(allBikesList);
            }
            if (e.getSource() == ViewBike) {
               viewProducts(allBikesList);
            }

            if (e.getSource() == rentBikeBtn) {
                rentBike(allBikesList);
            }
            if (e.getSource() == returnRental) {
                System.out.println("Bike returned");
            }
            if (e.getSource() == cancelRental) {
                cancelRental(allRentals);
                System.out.println("Rental Cancelled");
            }
            if (e.getSource() == adminYearlyRev) {
                System.out.println("You did well this year");
            }

            if (e.getSource() == adminAnalyse) {
                System.out.println("Well done you made a million");
            }

        }
    }
