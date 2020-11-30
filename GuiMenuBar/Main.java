package GuiMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Main extends JFrame implements ActionListener {

   private static ArrayList<Bike> allBikesList;
   private static ArrayList<Rental> allRentals;
    ArrayList<Rental> rentBike;

    JMenuBar menuBar;
    JMenu bike;
    JMenu rental;
    JMenu admin;

    JMenuItem bikeItem1;
    JMenuItem bikeItem2;
    JMenuItem bikeItem3;
    JMenuItem bikeItem4;
    JMenuItem rentalItem1;
    JMenuItem rentalItem2;
    JMenuItem rentalItem3;
    JMenuItem adminItem1;
    JMenuItem adminItem2;

    JPanel panel1 = new JPanel();
    JLabel label1 = new JLabel();

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


        bikeItem1 = new JMenuItem("Amend");
        bikeItem1.setMnemonic(KeyEvent.VK_A); //A shortcut for Amend a bike
        bikeItem2 = new JMenuItem("Add");
        bikeItem2.setMnemonic(KeyEvent.VK_D);// B shortcut for Add bike
        bikeItem3 = new JMenuItem("Remove");
        bikeItem3.setMnemonic(KeyEvent.VK_R);// R shortcut for Remove
        bikeItem4 = new JMenuItem("View");
        bikeItem4.setMnemonic(KeyEvent.VK_V);// R shortcut for View

        bikeItem1.addActionListener(this);
        bikeItem2.addActionListener(this);
        bikeItem3.addActionListener(this);
        bikeItem4.addActionListener(this);

        bike.add(bikeItem1);
        bike.add(bikeItem2);
        bike.add(bikeItem3);
        bike.add(bikeItem4);

        rentalItem1 = new JMenuItem("Rent Bike");
        rentalItem1.setMnemonic(KeyEvent.VK_R); //R shortcut for Rent bike
        rentalItem2 = new JMenuItem("Return Bike");
        rentalItem2.setMnemonic(KeyEvent.VK_T); //T shortcut for return bike
        rentalItem3 = new JMenuItem("Cancel Rental");
        rentalItem3.setMnemonic(KeyEvent.VK_C); //C shortcut for cancel rental

        rentalItem1.addActionListener(this);
        rentalItem2.addActionListener(this);
        rentalItem3.addActionListener(this);

        rental.add(rentalItem1);
        rental.add(rentalItem2);
        rental.add(rentalItem3);

        adminItem1 = new JMenuItem("List Yearly Revenue");
        adminItem1.setMnemonic(KeyEvent.VK_L); //L shortcut for List yearly Rev
        adminItem2 = new JMenuItem("Analyse Revenue");
        adminItem2.setMnemonic(KeyEvent.VK_A); //A shortcut for Analyse

        admin.addActionListener(this);
        admin.addActionListener(this);

        admin.add(adminItem1);
        admin.add(adminItem2);

        menuBar.add(bike);
        menuBar.add(rental);
        menuBar.add(admin);
        menuBar.setBackground(new Color(0, 100, 200));

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }

        public static void main (String[]args) throws Exception {

            new Main();

            File outFile = new File("bikes.data");
            FileOutputStream outStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(allBikesList);
            outStream.close();
            File inFile = new File("bikes.data");
            FileInputStream inStream = new FileInputStream(inFile);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            allBikesList = (ArrayList<Bike>) objectInStream.readObject();
            inStream.close();

            Bike b1 = new Bike("MB", 21, "Blue", "Trek", 20, true);
            Bike b2 = new Bike("RC", 18, "White", "Giant", 30, true);
            Bike b3 = new Bike("El", 18, "White", "Cube", 50, true);
            Bike b4 = new Bike("Kids", 10, "Red", "Rad power", 20, true);
            Bike b5 = new Bike("HB", 21, "Blue", "Raleigh Activator", 20, true);

            allBikesList = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5));

             allRentals = new ArrayList<>();

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
                BikeToAmend.setGears(newRate);

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

        ArrayList<Bike> foundBike = new ArrayList<Bike>();
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

        ArrayList<Bike> rentBike = new ArrayList<>();


        String searchKey = JOptionPane.showInputDialog("Please enter the type of bike you wish to rent example...(MB,RC,El,Kids,HB)");

        String noOfDays = JOptionPane.showInputDialog("Please enter the date you wish to return the bike: YEAR-MM-DATE format");

        String dateRented = JOptionPane.showInputDialog("Please enter the date you wish to rent the bike: YEAR-MM-DATE format");

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



        JOptionPane.showMessageDialog(null,"Thanks for using the service I hope you enjoy it.");


    }


    @Override
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == bikeItem1) {
               amendProduct(allBikesList);
            }
            if (e.getSource() == bikeItem2) {

                    addBike(allBikesList);
            }
            if (e.getSource() == bikeItem3) {
                removeBike(allBikesList);
            }
            if (e.getSource() == bikeItem4) {

               viewProducts(allBikesList);
            }

            if (e.getSource() == rentalItem1) {
                rentBike(allBikesList);
            }
            if (e.getSource() == rentalItem2) {
                System.out.println("Bike returned");
            }
            if (e.getSource() == rentalItem3) {
                System.out.println("Rental Cancelled");
            }
            if (e.getSource() == adminItem1) {
                System.out.println("You did well this year");
            }

            if (e.getSource() == adminItem2) {
                System.out.println("Well done you made a million");
            }

        }
    }
