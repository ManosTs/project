import java.util.Date;

public class CSVFile {

    //default constructor
    CSVFile() {
    }

    //Headers
    private java.util.Date date;
    private double Open;
    private double High;
    private double Low;
    private double Close;
    private int Volume;
    private int OpenInt;


    //setters
    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public void setOpen(double open) {
        Open = open;
    }

    public void setHigh(double high) {
        High = high;
    }

    public void setLow(double low) {
        Low = low;
    }

    public void setClose(double close) {
        Close = close;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }

    public void setOpenInt(int openInt) {
        OpenInt = openInt;
    }

    //getters
    public java.util.Date getDate() {
        return this.date;
    }

    public Double getOpen() {
        return Open;
    }

    public Double getHigh() {
        return High;
    }

    public Double getLow() {
        return Low;
    }

    public Double getClose() {
        return Close;
    }

    public Integer getVolume() {
        return Volume;
    }

    public int getOpenInt() {
        return OpenInt;
    }

    @Override
    public String toString() {
        return getDate() + "\t" + getOpen() + "\t" + getHigh() + "\t" + getLow() + "\t" + getClose() + "\t" + getVolume() + "\t " + getOpenInt();
    }
}
