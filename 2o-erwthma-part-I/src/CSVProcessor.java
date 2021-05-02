import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CSVProcessor {
    //default constructor
    CSVProcessor() {
    }

    ArrayList<CSVFile> csvTable = new ArrayList<>(); // Create an ArrayList object in order to add the columns with parsed type

    //read CSV file
    public ArrayList<CSVFile> CsvReader(String FILE_PATH) {

        String delimiter = ",";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            reader.readLine(); // this will read the first line
            String line1 = null; //skip first line of file which has the headers

            // Reading tokens one by one in a String array;

            while ((line1 = reader.readLine()) != null) {
                String[] tokens = line1.split(delimiter); //store the appearances of delimiter
                CSVFile csv = new CSVFile();
                //set the rows
                csv.setDate(tokens[0]);
                csv.setOpen(Double.parseDouble(tokens[1]));
                csv.setHigh(Double.parseDouble(tokens[2]));
                csv.setLow(Double.parseDouble(tokens[3]));
                csv.setClose(Double.parseDouble(tokens[4]));
                csv.setVolume(Integer.parseInt(tokens[5]));
                csv.setOpenInt(Integer.parseInt(tokens[6]));

                //add the above rows into arraylist
                csvTable.add(csv);
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return csvTable; //returns the array list
    }


    private static final String CSV_SEPARATOR = ",";

    public void csvWriter(List<CSVFile> list, String FILE_PATH) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8));
            StringBuilder headers = new StringBuilder();
            //get the header of each column
            for (String header
                    : Arrays.stream(CSVFile
                    .class
                    .getDeclaredFields())
                    .map(Field::getName)
                    .collect(Collectors
                            .toList())) {
                headers.append(header);
                headers.append(CSV_SEPARATOR);
            }
            //write the headers in first line
            bw.write(headers.toString());
            bw.newLine();

            //write data line by line
            for (CSVFile csvRow : list) {
                String oneLine = csvRow.getDate() +
                        CSV_SEPARATOR +
                        csvRow.getOpen() +
                        CSV_SEPARATOR +
                        csvRow.getHigh() +
                        CSV_SEPARATOR +
                        csvRow.getLow() +
                        CSV_SEPARATOR +
                        csvRow.getClose() +
                        CSV_SEPARATOR +
                        csvRow.getVolume() +
                        CSV_SEPARATOR +
                        csvRow.getOpenInt();
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            System.out.println("Sorted File has been created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
