import java.util.Date;

public interface IRedBlackTreeDate {
    void insert(Record data);
    void printTree();
    void search(Date key);
    void modifyVolume(Date key);
    void deletion(Date key);
}
