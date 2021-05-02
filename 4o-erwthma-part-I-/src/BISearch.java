import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class BISearch {

    private static int run;

    public BISearch() {
        run = 0;
    }

    static int binaryInterpolationSearch(CSVFile[] array, int left, int right, String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //format string date to type Date

        Date x = format.parse(date); // the date to locate into array

        int i;
        run++;//comparison holder
        //this two if's are used to check if there is only on
        if (left > right || (left == right && !array[left].getDate().equals(x))) {//if x does not belong to array return -1
            return -1;
        } else if (left == right && array[left].getDate().equals(x)) {//else if x is in left position which is the first element of array, return left

            return left;
        }
        int p = (int) ((
                Math.abs((x.getTime() - array[left].getDate().getTime()) * (right - left))
        ) /
                Math.abs((array[right].getDate().getTime() - array[left].getDate().getTime()))
        );//returns the most near position of the date we want to search
        int mid = left + p; //the position we want to test
        i = 1;
        int next;
        if (x.after(array[mid].getDate())) {
            while (true) {
                 //comparison holder
                next = (int) (mid + (i * Math.sqrt(right - left))); //goes to the next date by making sqrt(right-left) jumps
                if (next > right || x.before(array[next].getDate())) break;
                if (x.equals(array[next].getDate())) return next;//the date has been found at next position
                i = i + 1; //increase i by one
            }

            left = (int) (mid + (i - 1) * Math.sqrt(right - left) + 1); //reducing left by making (i-1)*sqrt(right-left) jumps
            right = Math.min(right, next - 1); //right gets the minimum between right and next - 1
            return binaryInterpolationSearch(array, left, right, date); //repeat if date hasn't been found yet
        } else if (x.before(array[mid].getDate())) {
            while (true) {
                 //comparison holder
                next = (int) (mid - i * Math.sqrt(right - left));
                if (next < left || x.after(array[next].getDate())) break;
                if (x.equals(array[next].getDate())) return next; //if date is at the next pos, then found
                i = i + 1;
            }
            right = (int) (mid - (i - 1) * Math.sqrt(right - left) - 1); // reducing right by making (i-1)*sqrt(right-left) steps
            left = Math.max(left, next + 1); //left gets the maximum between left and next + 1
            return binaryInterpolationSearch(array, left, right, date); //repeat if date hasnt been located yet
        } else {
            return mid; //returns the mid position
        }
    }

    public static int getRun() {
        return run; //returns the value of comparisons
    }
}

