import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    //path of file
    String agnFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\agn.us.txt";

    public void dialog() {
        CSVProcessor csvFile = new CSVProcessor();

        ArrayList<CSVFile> list = csvFile.CsvReader(agnFilePath);

        CSVFile[] array = list.toArray(new CSVFile[0]);

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter date to find volume:");
            String date = sc.nextLine();

            //get the option from user
            getOption(array, date);
            afterOption();

        } catch (InputMismatchException i) {
            System.out.println("Wrong input");
            dialog();
        }
    }

    //get the option from user
    public void getOption(CSVFile[] array, String date) {

        try {
            System.out.println("Input 'BI' for Binary Interpolation Search or 'ABI' for Advanced Interpolation Search");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "BI", "bi" -> {

                    long startTime = System.nanoTime();
                    int indexOfBI = BISearch.binaryInterpolationSearch(array, 0, array.length - 1, date);
                    long endTime = System.nanoTime();

                    if (indexOfBI != -1) {//if method returns anything rather than -1 print the following
                        System.out.println("Date: " + date + " -> " + "Volume: " + array[indexOfBI].getVolume());
                        System.out.println("Comparisons:" + BISearch.getRun());
                        System.out.println("BISearch took:" + (double) (endTime - startTime) / 1000000000 + " seconds");

                    } else {//else if method returns -1 date doesnt exist in array
                        System.out.println("Date not found!");
                    }
                }
                case "ABI", "abi" -> {
                    long startTime = System.nanoTime();
                    int indexOfABI = AdvancedBISearch.advancedBISearch(array, 0, array.length - 1, date);
                    long endTime = System.nanoTime();

                    if (indexOfABI != -1) { //if method returns anything rather than -1 print the following
                        System.out.println("Date: " + date + " -> " + "Volume: " + array[indexOfABI].getVolume());
                        System.out.println("Comparisons:" + AdvancedBISearch.getRun());
                        System.out.println("AdvancedBISearch took:" + (double) (endTime - startTime) / 1000000000 + " seconds");

                    } else {//else if method returns -1 date doesnt exist in array
                        System.out.println("Date not found!");
                    }
                }
                default -> System.out.println("Error! wrong input");
            }

        } catch (InputMismatchException | ParseException i) {
            System.out.println("Wrong input");
            getOption(array, date);
        }
    }

    //this is for search another volume by giving date
    public void afterOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search another volume?, type yes or no to exit");
        String choice = scanner.nextLine();

        if (choice.equals("Yes") || choice.equals("yes")) {
            dialog();
        } else if (choice.equals("No") || choice.equals("no")) {
            System.exit(0);
        } else {
            System.out.println("Wrong input, try again");
            afterOption();
        }
    }
}
