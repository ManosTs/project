package apackage.redblacktree.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RedBlackTreeDate implements IRedBlackTreeDate {


    //colors
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node leaf;
    private Node root;


    public RedBlackTreeDate() {
        leaf = new Node(); //leaf is an empty node
        leaf.color = BLACK; //color of leaf is always black
        leaf.left = null; //the left of leaf is null
        leaf.right = null;//the right of leaf is null as well
        root = leaf; //initialize root as an empty node
    }

    //rotate the left node
    private void rotateLeft(Node node) {
        Node y = node.right; //y is the right node of NODE
        node.right = y.left;//the right node of NODE assigns to the left node of Y

        /*
             NODE                   Y
            /    \                 / \
           A      Y     ->      NODE  C
                 / \           /    \
                B   C         A      B

         */


        if (y.left != leaf) {//if left node of y is not null
            y.left.parent = node; //go the B node to NODE and make NODE parent of B
        }
        y.parent = node.parent; //y is now the parent of NODE

        if (node.parent == leaf) {//if NODE parent is null
            root = y;               //make y as root
        } else if (node == node.parent.left) {//else if node is C
            node.parent.left = y; //get C to the left of Y
        } else {
            node.parent.right = y;//get B to the right of NODE
        }
        y.left = node;//the left node of Y is NODE
        node.parent = y; //parent of NODE is Y
    }

    private void rotateRight(Node node) {
        Node y = node.left; //y is the left node of NODE
        node.left = y.right; //the left node of NODE assigns to the right node of Y

        /*
             NODE                   Y
            /    \                 / \
           A      Y     <-      NODE  C
                 / \           /    \
                B   C         A      B

         */


        if (y.right != leaf) {//if right node of y is not null
            y.right.parent = node;//move Y to the right of NODE
        }
        y.parent = node.parent; //NODE is the parent of y

        if (node.parent == leaf) {//if NODE parent is null
            root = y;        //make y as root
        } else if (node == node.parent.right) {//else if node is C
            node.parent.right = y; //get C to the right of Y
        } else {
            node.parent.left = y;//get B to the left of Y
        }
        y.right = node;//move node to the left of Y
        node.parent = y;//the parent of Y is NODE
    }

    public void insert(Record data) {
        Node y = leaf; //y is an empty node
        Node x = this.root; //x is the root
        Node node = new Node(data); //node is a node with a record
        node.data = data; //data of node is the data that we want to insert
        node.left = leaf; //the left node of Node is null
        node.right = leaf; //the right node of Node is null
        node.color = RED; //every new node is red

        while (x != leaf) {//while root(x) is not null
            y = x;              //assign value of x to y
            if (node.data.getDate().before(x.data.getDate())) { //left subtree
                x = x.left;         //insert data to left subtree
            } else {//right subtree
                x = x.right;        //insert data to right subtree
            }
        }

        node.parent = y; //the parent of node is y
        if (y == leaf) {//if y is null
            root = node;//then make the node as root
        } else if (node.data.getDate().before(y.data.getDate())) { //else if the date of node is less than y data
            y.left = node; //then the left node of Node is node
        } else {
            y.right = node;//else is the right node of Node is node
        }

        if (node.parent == null) { //if there is no parent of node then
            node.color = BLACK; //color it with BLACK
            return;
        }
        if (node.parent.parent == null) { //if there is no grand return
            return;
        }
        BalanceAfterInsert(node); //balance after every insertion of node
    }

    private void printInorder(Node root) {
        if (root != leaf) {
            String color;
            if (root.color == RED) {
                color = "RED";
            } else {
                color = "BLACK";
            }

            printInorder(root.left);
            System.out.println(root == this.root ? "ROOT--" + root.data + "(" + color + ")" : root.data + "(" + color + ")");
            printInorder(root.right);
        }
    }


    public void printTree() {
        printInorder(this.root);
    }


    private void BalanceAfterInsert(Node node) {
        Node y; //help node

        while (node.parent.color == RED) { //while parent of node is Red do the following

            if (node.parent == node.parent.parent.left) { //if parent of node is uncle
                y = node.parent.parent.right; //then y is the right node of grand

                if (y.color == RED) { //if y has color Red then
                    y.color = BLACK; //change it to black
                    node.parent.color = BLACK; //parent of node will be black
                    node.parent.parent.color = RED; //and grand red
                    node = node.parent.parent;//node is grand
                } else {
                    if (node == node.parent.right) { //if node is the right of node's parent then
                        node = node.parent; //node will be parent
                        rotateLeft(node);//do the left rotate for node
                    }
                    node.parent.color = BLACK; //and now parent of node will be colored black
                    node.parent.parent.color = RED; //grand of parent RED and
                    rotateRight(node.parent.parent); //do the right rotate for grand
                }
            } else if (node.parent == node.parent.parent.right) { //this is the opposite of previous 'if'. We change left to right and right to left

                y = node.parent.parent.left;

                if (y.color == RED) {
                    y.color = BLACK;
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            } else if (node == root) { //if the node is root then break
                break;
            } else {
                break;
            }

        }
        root.color = BLACK; //color root black
    }

    //method to search inside the tree
    private Node searchByDate(Node root, Date key) {

        if (root == leaf) {//if there is no root then there is nothing to search so return leaf(null)
            return leaf;
        }else if (key.equals(root.data.getDate())) { //if the key is the root then return root
            return root;
        }else if (key.before(root.data.getDate())) {//if key is in the left tree then do the search for left tree , recursively
            return searchByDate(root.left, key);
        }else if (key.after(root.data.getDate())) {//if key is in the right tree then do the search for left tree , recursively
            return searchByDate(root.right, key);
        }
        return root;
    }
    public void search(Date key) {
        Node node = searchByDate(this.root, key);

        try {
            Date date = node.data.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFound = dateFormat.format(date);

            System.out.println("Date:" + dateFound + " -> " + "Volume:" + node.data.getVolume());
        } catch (NullPointerException n) {
            System.out.println("Date not found");
        }
    }


    //method to change volume
    public void modifyVolume(Date key) {
        Node node = searchByDate(this.root, key);//we get the node of where the date is
        try {
            Date date = node.data.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFound = dateFormat.format(date);//convert date to string

            //show the current volume and date
            System.out.println(dateFound + " has as volume |" + node.data.getVolume() + "|");

            Scanner sc = new Scanner(System.in);

            //give new volume for given Date
            System.out.print("Give new volume:");
            int getVolume = sc.nextInt();

            node.data.setVolume(getVolume);

            //show the change
            System.out.println("The Volume of " + dateFound + " has changed to " + getVolume);

            //show the date and the new volume
            System.out.println("Date:" + dateFound + " -> " + "Volume:" + node.data.getVolume());

        } catch (NullPointerException n) {
            System.out.println("Date not found");
        } catch (IllegalArgumentException | InputMismatchException illegal) {
            System.out.println("Wrong input, try again");
            modifyVolume(key);
        }
    }

    //method to balance tree after deletion
    private void BalanceAfterDelete(Node node) {
        Node y;

        while (node != root && node.color == BLACK) { //while node is not root and node color is BLACK do the following
            if (node == node.parent.left) { //if node is the left node of node's parent then
                y = node.parent.right; //assign y as right node of node's parent
                if (y.color == RED) { //if y color is RED then
                    y.color = BLACK; //change it to black
                    node.parent.color = RED; //now the parent of node has RED color
                    rotateLeft(node.parent);//do the left rotate for the node's parent
                    y = node.parent.right; //and assign y as the right node of node's parent
                }
                if (y.left.color == BLACK && y.right.color == BLACK) { //if the left node of y is black and the right node of y is black
                    y.color = RED;//then y must be RED
                    node = node.parent; //and node is node's parent
                } else {
                    if (y.right.color == BLACK) {//if right node of y is BLACK then
                        y.left.color = BLACK;//change the left node of y to BLACK
                        y.color = RED;//and y color is now RED
                        rotateRight(y);//do the right rotate for y
                        y = node.parent.right;//and assign y as the right node of node's parent
                    }
                    y.color = node.parent.color;//y color is the same with node's parent color
                    node.parent.color = BLACK;//and node's parent color is now BLACK
                    rotateLeft(node.parent);//do the left rotate for node's parent
                    node = root;//and node is root
                }
            } else {//else do the opposite of above 'if'
                y = node.parent.left;
                if (y.color == RED) {
                    y.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    y = node.parent.left;
                }
                if (y.right.color == BLACK && y.left.color == BLACK) {
                    y.color = RED;
                    node = node.parent;
                } else {
                    if (y.left.color == BLACK) {
                        y.right.color = BLACK;
                        y.color = RED;
                        rotateLeft(y);
                        y = node.parent.left;
                    }
                    y.color = node.parent.color;
                    node.parent.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
            node.color = BLACK;
        }
    }

    //the method for deletion
    private void delete(Node node, Date key) {
        Node z = leaf; //z is null
        Node x, y;

        while (node != leaf) {//while node is not leaf do the following
            if (node.data.getDate().equals(key)) {//if node's date equals to the given date then
                z = node;//z is the NODE
            }

            if (node.data.getDate().compareTo(key) <= 0) {//if node's date is less than given date then
                node = node.right;//node is right node
            } else {
                node = node.left;//node is the left node
            }
        }

        if (z.left == leaf || z.right == leaf) {//if left node of Z is null or right node of Z is null
            y = z;//y is z
        } else y = treeSuccessor(z);//else get the minimum node and assign it to y
        if (y.left != leaf) {//if left node of y is not null then
            x = y.left;//x is the left node of y
        } else {//else ...
            x = y.right;//x is the right node of y
        }
        x.parent = y.parent;//x's parent to y's parent

        if (y.parent == leaf) {//if y's parent is null then
            root = x;//root is x
        } else if (y == y.parent.left) {//else if y is the y's parent left node then
            y.parent.left = x; // y's parent left node goes to X
        } else {
            y.parent.right = x;//else y's parent right node goes to X
        }
        if (y != z) {//if y is not equals to z then
            z.data = y.data; //z's data to y's data
        }
        if (y.color == BLACK) { //if y has Black color then
            BalanceAfterDelete(x);//balance it
        }
    }

    public void deletion(Date key) {
        Node node = searchByDate(this.root,key); //get the right record if exists. If so then do the following
            if(node!=null) {

                //this convert type Date to type String in order to print date in this particular way (2005-01-05)
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String foundDate = dateFormat.format(key);

                //show the deleted stats
                System.out.println("The following stats are being deleted");
                System.out.println("Date:"+foundDate+" -> "+ "Volume:"+node.data.getVolume());
                delete(this.root,key); //call method delete to delete the node
            }else{
                System.out.println("No date detected");
            }
    }

    //get the minimum node
    private Node treeSuccessor(Node z) {
        if (z.right != leaf) {//if z's right node is not null
            return min(z.right);//then return it as minimum node
        }
        Node y = z.parent;//y is the z's parent
        while (y != leaf && z == y.right) {//while z has parent and z is the right node of its parent do
            z = y;//z to y
            y = y.parent; //y as parent
        }
        return y;//return y node
    }

    //get the minimum Date
    private Node min(Node node) {

        //go down to find the left most node
        while (node.left != leaf) {
            node = node.left;
        }
        return node;//return the most left node
    }
}


