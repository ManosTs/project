import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashChaining implements IHashChaining {
    private int sizeOfTable;

    private int size;

    //get size of the elements that goes in the Table
    public int getSize() {
        return size;
    }

    private Node[] table;

    HashChaining(int size) {
        sizeOfTable = size; //initialize the size of table
        table = new Node[sizeOfTable]; //table points to a node with key,value and next head

        //initialize the table with null value
        for (int i = 0; i < sizeOfTable; i++) {
            table[i] = null;
        }
    }

    //method to insert elements with Date as Key and Volume as Value
    public void put(Date key, int value) {

        //format the date to yyyy-MM-dd: 2005-01-05
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(key); //get the format date string
        int hash = hashCode(stringDate); //calculate its hash

        //if there is no key and value in hash position, create a node with key and value
        if (table[hash] == null) {
            table[hash] = new Node(key, value);
        } else {                        //this checks if there are key and values with the same HASH.
            Node entry = table[hash]; // If so then get the node which is in hash position and while it has NEXT,write it
            while (!entry.date.equals(key) && entry.next != null ) {
                entry = entry.next;
            }

            //every Volume has a Date so this assigns every volume to a date
            if (entry.date.equals(key)) {
                entry.volume = value;
            } else {//else the next of ENTRY will be a new node with key and value
                entry.next = new Node(key, value);
            }
        }
        size++;//increasing by one when a new key and value goes in in Table
    }


    //change the volume method
    public void updateVolume(String date, int newVolume) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToDate = dateFormat.parse(date);

        put(dateToDate,newVolume);
    }

    //method to search a Date and print its Volume
    private int searchInternal(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToDate = dateFormat.parse(date);

        int hash = hashCode(date);
        if(table[hash] == null){ //if the hash position has zero records, Date is not in Table
            return -1;
        }else {
            Node node = table[hash];//else get the hash position and
            while (node != null && !node.date.equals(dateToDate)) {//while node is not null and the date of node is not equal to the given Date
                node = node.next;//search the next node
            }
            if (node == null) {//if node null return -1
                return -1;
            } else {//else node has been found and return the Volume
                return node.volume;
            }
        }
    }

    public void search(String date) throws ParseException {
        int volumeFound = searchInternal(date);
        if(volumeFound != -1)
        System.out.println("Found: Date:" + date + " -> " + "Volume: " + volumeFound);
        else System.out.println("Date not found!");
    }


    //method to delete a Record
    public void delete(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToDate = dateFormat.parse(date);

        int hash = hashCode(date);

        if(table[hash]!=null){ //if hash position is not null
            Node previousNode = null; //helps to get the previous node if there is a next node
            Node node = table[hash]; //get the hash position

            while(node.next!=null && !node.date.equals(dateToDate)){//while there is a next node and date of node does not equal to the given date
                previousNode = node; //previous node is now the NODE
                node = node.next;//search the next node till it finds the right node
            }
            if(node.date.equals(dateToDate)){//if date of node equals to the given Date
                if(previousNode == null){//if there is no next node
                    table[hash] = node.next;//assign the hash position to the next position of node
                }else{//else assign the next node of previous node to the next node
                    previousNode.next = node.next;
                }
                size--;
            }

            //shows the deleted stats
            System.out.println("Deleted stats: Date:"+dateFormat.format(node.date)+" -> Volume:"+node.volume);

        }
    }

    //method to print Table
    public void printTable() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //shows the the size of table
        System.out.print("Size of Table:"+getSize());

        for (int index = 0; index < sizeOfTable; index++) {
            System.out.print("\nBucket " + (index + 1) + " : ");
            Node entry = table[index];
            while (entry != null) {//while node is not null print the next node
                String date = dateFormat.format(entry.date);//convert type Date to string in yyyy-MM-dd way.
                System.out.print("Date:"+date+"|" +"Volume:"+entry.volume+" -> ");//show the records
                entry = entry.next;//get the next node
            }
        }
    }

    //method to get the hash code of dates
    private int hashCode(String date) {
        int asciiValue = 0; //this will help to get the ascii code(it will make a cast to the char which returns the hashcode)
        int m = sizeOfTable; //get the size of table in order to commit the module
        for (int i = 0; i < date.length(); i++) {
            asciiValue += date.charAt(i); //get the ascii code of every char of date and then sum
        }
        return asciiValue % (m);//return the hashCode of the Date
    }
}
