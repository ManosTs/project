import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinarySearch {

    private static int run;
    //paths of files
    public BinarySearch(){
        run = 0;
    }
    static int binarySearch(CSVFile[] array, int start, int end, String dateString) throws ParseException {

        try  {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(dateString); //convert string date to Date type

            int middle = (start + end) / 2; // the mid of array

            while(start<=end){
                run++; //count comparisons
                if(array[middle].getDate().before(date)){
                    start = middle + 1; //increase start
                }else if(array[middle].getDate().equals(date)){
                    return middle + 1; //date found in middle position
                }else{
                    end = middle - 1; //decrease end
                }
                middle = (start + end) / 2;
            }

        }catch (NullPointerException n ){
            System.out.println(n.getMessage());
        }
        return -1;
    }
    static int getRun() {
        return run;
    }
}


