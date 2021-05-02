package cpackage.hashchain;


import java.util.Date;

class Record {

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

}
