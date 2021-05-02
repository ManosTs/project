import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    //paths of files
    String agnFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\agn.us.txt";
    String ainvFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\ainv.us.txt";
    String aleFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\agn.us.txt";

    //paths of sorted files
    String agnSortedFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\sortedFilesBasedOnOpenData\\agn.us_sorted.txt";
    String ainvSortedFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\sortedFilesBasedOnOpenData\\ainv.us_sorted.txt";
    String aleSortedFilePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\sortedFilesBasedOnOpenData\\ale.us_sorted.txt";

    withOpenStock sortingWithOpenStock = new withOpenStock();

    public void dialog() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Type 1 for agn.us file");
            System.out.println("Type 2 for ainv.us file");
            System.out.println("Type 3 for ale.us file");
            System.out.println("Type 4 for exit");

            int option = sc.nextInt();

            if (option == 1) {

                sortingWithOpenStock.getOption(agnFilePath, agnSortedFilePath);

            } else if (option == 2) {
                sortingWithOpenStock.getOption(ainvFilePath, ainvSortedFilePath);
            } else if (option == 3) {
                sortingWithOpenStock.getOption(aleFilePath, aleSortedFilePath);
            } else if (option == 4) {
                System.exit(0);
            } else {
                System.out.println("wrong input, try again");
                dialog();
            }

            Scanner sc1 = new Scanner(System.in);
            System.out.println("Sort another file?, type yes or no to exit");
            String choice = sc1.nextLine();

            if (choice.equals("Yes") || choice.equals("yes")) {
                dialog();
            } else if (choice.equals("No") || choice.equals("no")) {
               System.exit(0);
            }

        } catch (InputMismatchException i) {
            System.out.println("Wrong input");
            dialog();
        }
    }
}
