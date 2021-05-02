import apackage.redblacktree.date.AMenu;
import bpackage.redblacktree.volume.BMenu;
import cpackage.hashchain.CMenu;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private AMenu aMenu;
    private BMenu bMenu;
    private CMenu cMenu;

    Menu(String filePath) {
        this.aMenu = new AMenu(filePath);
        this.bMenu = new BMenu(filePath);
        this.cMenu = new CMenu(filePath);
    }

    public void dialog() {
        printMenu();

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();

                //option from user
                switch (option) {
                    case 1 -> afterOption1Menu();
                    case 2 -> afterOption2();
                    case 3 -> {
                        System.out.println("Exiting");
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            printMenu();
        }
    }

    private void printMenu() {
        System.out.println("---------------MAIN MENU-------------------");
        System.out.println("Type 1 to load agn.us.txt with Binary Search Tree");
        System.out.println("Type 2 to load agn.us.txt with Hash Chaining");
        System.out.println("Type 3 to exit");
        System.out.println("------------END OF MENU----------------");
        System.out.print("Option:");
    }



    //the dialog of afterOption1Menu method
    private void afterOption1Dialog() {
        System.out.println("---------------MENU-------------------");
        System.out.println("Type 1 to load Binary Search Tree by Date");
        System.out.println("Type 2 to load Binary Search Tree by Volume");
        System.out.println("Type 3 to go to previous menu");
        System.out.println("Type 4 to exit");
        System.out.println("------------END OF MENU----------------");
        System.out.print("Option:");
    }

    //after enter 1 to the option then call this method in order to get the option if he wants to load BST by Date or By Volume
    private void afterOption1Menu() {

        afterOption1Dialog();
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();

                switch (option) {
                    case 1 -> aMenu.ADialog();//get the menu of BST by Date
                    case 2 -> bMenu.BDialog();//get the menu of BST by Volume
                    case 3 -> {
                        System.out.println("Back to menu");
                        return;
                    }
                    case 4 -> {
                        System.out.println("Exiting");
                        System.exit(1);
                    }
                    default -> System.out.println("Wrong input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                afterOption1Menu();
            }
            afterOption1Dialog();
        }
    }



    //as the option is 2 then run this method to get the Hash Chain menu
    private void afterOption2() {
        cMenu.CDialog();
    }
}
