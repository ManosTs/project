import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

    //default constructor
    Record() {}

    //the records
    public Date date;
    public int Volume;

    //setters
    public void setDate(Date date) {
        this.date = date;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }



    //getters
    public Date getDate() {
        return this.date;
    }

    public int getVolume() {
        return Volume;
    }

    //format the Date type to yyyy-MM-dd
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //this method below, helps converting Date type to String type as we want to print date in this particular way:2005-05-21
    private String getDates() {
        return formatter.format(getDate());
    }

    @Override
    public String toString() {
        return "Date:" + getDates() + "|" + "Volume:" + getVolume()+" -> ";
    }
}
