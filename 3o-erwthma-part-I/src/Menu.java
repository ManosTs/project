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

    public void getOption(CSVFile[] array, String date) {
        try {
            System.out.print("Input 'Binary' to execute BinarySearch or 'Inter' for interpolationSearch:");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            if (option.equals("Binary") || option.equals("binary")) {
                long startTime = System.nanoTime();
                int index = BinarySearch.binarySearch(array, 0, array.length - 1, date);
                if(index!=-1){
                    System.out.println("Date:"+date+" -> "+ "Volume:"+array[index].getVolume());
                }else {
                    System.out.println("Date not found");
                }
                long endTime = System.nanoTime();
                System.out.println("Comparisons:"+BinarySearch.getRun());
                System.out.println("Binary Search took:"+(double)(endTime-startTime)/1000000000+ " seconds");
            } else if (option.equals("Inter") || option.equals("inter")) {
                long startTime = System.nanoTime();
                InterpolationSearch.interpolationSearch(array, 0, array.length - 1, date);
                long endTime = System.nanoTime();
                System.out.println("Comparisons:"+InterpolationSearch.getRun());
                System.out.println("Interpolation Search took:"+(double)(endTime-startTime)/1000000000+ " seconds");
            }

        } catch (InputMismatchException i) {
            System.out.println("Wrong input");
            getOption(array, date);
        } catch (ParseException p) {
            System.out.println(p.getMessage());
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
        }
    }
}
