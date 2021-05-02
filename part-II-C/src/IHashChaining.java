import java.text.ParseException;
import java.util.Date;

public interface IHashChaining {
    void put(Date key, int value);
    void printTable();
    void search(String date) throws ParseException;
    void updateVolume(String date, int newValue) throws ParseException;
    void delete(String date) throws ParseException;
}
