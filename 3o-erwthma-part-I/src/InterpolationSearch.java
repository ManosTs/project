import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterpolationSearch {
    private static int run;
    public InterpolationSearch(){
        run = 0;
    }
    static void interpolationSearch(CSVFile[] array, int low, int high, String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //format string date to type Date

        Date item = format.parse(date);

        while (low <= high && (item.compareTo(array[high].getDate()) <= 0) && (item.compareTo(array[low].getDate()) >= 0)) {//while, item is less or equal to date in position high and item larger or equal to date in position low, is true
            run++;//comparisons
            if (low == high) { // if low and high 'meet' do the following
                if (array[high].getDate().equals(item)) {
                    System.out.println("Date: " + date + " -> " + "Volume: " + array[high].getVolume());
                    return;
                }
            }

            //we need the nearest position of where the item that needs to be searched is.
            int position = (int) (
                                    low + (
                                         (high - low) * (Math.abs(item.getTime() - array[low].getDate().getTime()))
                                          ) /
                                            (
                                                 Math.abs(array[high].getDate().getTime() - array[low].getDate().getTime())
                                             )
                                 );

            //if item exists to that position return 1. Else if item less than the value of that position increase variable low by one or if lager , increase variable high by one and repeat

            if (array[position].getDate().equals(item)) {
                System.out.println("Date: " + date + " -> " + "Volume: " + array[position].getVolume());
                return;
            } else if (array[position].getDate().before(item)) {
                low = position + 1; //increase low by one
            } else if (array[position].getDate().after(item)) {
                high = position - 1; //decrease high by one
            } else {
                System.out.println("Date not found!");
                return;
            }
        }
    }

    static int getRun() {
        return run;
    }
}
