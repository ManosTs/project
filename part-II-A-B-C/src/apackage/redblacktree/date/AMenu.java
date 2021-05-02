package apackage.redblacktree.date;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AMenu {

    private IRedBlackTreeDate tree = new RedBlackTreeDate();
    private String filePath;

    //default constructor
    public AMenu(String filePath) {
        this.filePath = filePath;
        initializeDataFromFile();
    }

    private void initializeDataFromFile() {
        //insert data from file
        List<Record> recordList = readFile();
        for (Record record : recordList) tree.insert(record);
    }

    //create bst tree
    private List<Record> readFile() {
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


    private void printRBTree() {
        tree.printTree();
    }

    private void searchVolumeByDate() throws ParseException {

        System.out.print("Give date to search volume:");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(key);
        tree.search(date);
    }

    private void volumeModifier() throws ParseException {

        System.out.print("Give date to modify volume:");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(key);

        tree.modifyVolume(date);
    }

    private void deleteRecord() throws ParseException {

        System.out.print("Give date to delete record:");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(key);

        tree.deletion(date);
    }

    public void ADialog() {

        printMenu();

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();
                switch (option) {
                    case 1 -> printRBTree();

                    case 2 -> searchVolumeByDate();

                    case 3 -> volumeModifier();

                    case 4 -> deleteRecord();

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

            } catch (InputMismatchException | ParseException e) {
                System.out.println("Wrong input");
            }
            printMenu();
        }

    }

    private void printMenu() {
        System.out.println("---------------A-MENU-------------------");
        System.out.println("Type 1 to print the Red Black Tree");
        System.out.println("Type 2 to search volume by date");
        System.out.println("Type 3 to modify volume by date");
        System.out.println("Type 4 to delete record by date");
        System.out.println("Type 5 to go to previous menu");
        System.out.println("Type 6 to exit");
        System.out.println("------------END OF MENU----------------");
        System.out.print("Option:");
    }


}

