import java.util.Date;

class Node {

    Date date;//key
    int volume;//value
    Node next;//points to next node because every next node is attached to its previous like a linked list.

    public Node( Date date, int volume){
        this.volume = volume;
        this.date = date;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

