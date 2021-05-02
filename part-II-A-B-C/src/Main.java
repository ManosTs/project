import java.io.File;
import java.util.Properties;

public class Main {
    private final static String filePath = "C:\\Users\\Manos\\Desktop\\4o ΕΞΑΜΗΝΟ\\Δομές Δεδομένων\\PROJECT_2020_2021\\PARTS\\PART_II\\agn.us.txt"; //get the file path

    public static void main(String[] args) {
        Menu menu = new Menu(filePath);
        menu.dialog();
    }
}
