package cpackage.hashchain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CMenu {
    //object to the interface that points to the HASH CHAINING CLASS
    private IHashChaining hash;
    private String filePath;

    public CMenu(String filePath) {
        this.filePath = filePath;

        initializeDataFromFile();
    }

    private void initializeDataFromFile() {
        //create the hash Table
        List<Record> list = createHashTable();
        hash = new HashChaining(list.size());
        for (Record record : list) hash.put(record.date, record.Volume);
    }

    private List<Record> createHashTable() {
        String delimiter = ",";

        ArrayList<Record> recordArrayList = new ArrayList<>();

        try {
            //read csv file in order to get the volume and date
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            reader.readLine(); // this will read the first line
            String line1 = null; //skip first line of file which has the headers

            // Reading tokens one by one and hold em in a String type, array;
            while ((line1 = reader.readLine()) != null) {
                String[] tokens = line1.split(delimiter); //store the occurs of delimiter
                Record record = new Record();

                //converting string dates to type Date
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dates = formatter.parse(tokens[0]);

                //set the node which will have the below records(Date, Volume)
                record.setDate(dates);
                record.setVolume(Integer.parseInt(tokens[5]));

                //add the above records into List
                recordArrayList.add(record);

            }


        } catch (IOException | ParseException io) {
            System.out.println(io.getMessage());
        }

        return recordArrayList;
    }


    //method to print the table
    private void Printer() {
        hash.printTable();
    }

    //find the given Date and its volume
    private void Finder() {
        try {
            System.out.print("Give date to find volume:");
            Scanner sc = new Scanner(System.in);
            String date = sc.nextLine();

            hash.search(date);
        } catch (ParseException io) {
            System.out.println(io.getMessage());
        }

    }

    //change the volume of given Date
    private void VolumeUpdater() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Give date to change its Volume:");
            String date = sc.nextLine();
            System.out.print("Give new Volume:");
            int newValue = sc.nextInt();

            hash.updateVolume(date, newValue);

            //show the change
            System.out.println("The Volume of " + date + " has changed to " + newValue);
        } catch (ParseException io) {
            System.out.println(io.getMessage());
        }

    }

    //delete the record of given Date
    private void Deleter() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Give date to delete its record:");
            String date = sc.nextLine();

            hash.delete(date);
        } catch (ParseException io) {
            System.out.println(io.getMessage());
        }

    }


    //runs in MAIN
    public void CDialog() {

        printMenu();

        //the options

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();

                switch (option) {
                    case 1 -> Printer();
                    case 2 -> Finder();
                    case 3 -> VolumeUpdater();
                    case 4 -> Deleter();
                    case 5 -> {
                        System.out.println("Back to menu...");
                        return;
                    }
                    case 6 -> {
                        System.out.println("Exiting");
                        System.exit(1);
                    }
                    default -> System.out.println("Wrong input,try again");
                }

            } catch (InputMismatchException i) {
                System.out.println("Wrong input");
                CDialog();
            }
            printMenu();
        }
    }

    //get the MENU
    private void printMenu() {
        System.out.println("\n---------------C-MENU-------------------");
        System.out.println("Type 1 to print the Table of Records");
        System.out.println("Type 2 to find date");
        System.out.println("Type 3 to modify Volume");
        System.out.println("Type 4 to delete any record");
        System.out.println("Type 5 to go to previous menu");
        System.out.println("Type 6 to exit");
        System.out.println("------------END OF MENU----------------");
        System.out.print("Option:");
    }
}

