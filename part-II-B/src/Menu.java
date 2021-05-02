import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Menu {
    private IRedBlackTreeVolume tree = new RedBlackTreeVolume();
    //default constructor
    Menu() {
    }

    //GET DATA FROM FILE AND INSERT THEM TO LIST AND THEN RETURN THE LIST
    private List<Record> readFile() {

        String delimiter = ",";
        String file_path = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\PARTS\\PART_II\\agn.us.txt";
        ArrayList<Record> recordArrayList = new ArrayList<>();

        try {
            //read csv file in order to get the volume and date
            BufferedReader reader = new BufferedReader(new FileReader(file_path));
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

    private void findMinimumVolume() {
        tree.minRecord();
    }

    private void findMaximumVolume() {
        tree.maxRecord();
    }



    public void dialog() {
        //insert data from file
        List<Record> recordList = readFile();
        for (Record record : recordList) tree.insert(record);

        printMenu();

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();

                switch (option) {
                    case 1 -> findMinimumVolume();
                    case 2 -> findMaximumVolume();
                    case 3 -> {
                        System.out.println("Exiting");
                        return;
                    }
                    default -> System.out.println("Wrong input,try again");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong input");
            }
            printMenu();
        }
    }

    private void printMenu() {
        System.out.println("---------------MENU-------------------");
        System.out.println("Type 1 to find date with the minimum value of Volume");
        System.out.println("Type 2 to find date with the maximum value of Volume");
        System.out.println("Type 3 to exit");
        System.out.println("------------END OF MENU----------------");
        System.out.print("Option:");
    }


}

