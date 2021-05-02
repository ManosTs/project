package bpackage.redblacktree.volume;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

class RedBlackTreeVolume implements IRedBlackTreeVolume {

    //colors
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private RBNode leaf;
    private RBNode root;


    public RedBlackTreeVolume() {
        leaf = new RBNode(); //leaf is an empty node
        leaf.color = BLACK; //color of leaf is always black
        leaf.left = null; //the left of leaf is null
        leaf.right = null;//the right of leaf is null as well
        root = leaf; //initialize root as an empty node
    }

    //rotate the left node
    private void rotateLeft(RBNode node) {
        RBNode y = node.right; //y is the right node of NODE
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

    private void rotateRight(RBNode node) {
        RBNode y = node.left; //y is the left node of NODE
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
        RBNode y = leaf;//y is an empty node
        RBNode x = this.root;//x is the root
        RBNode node = new RBNode(data);//node is a node with a record
        node.data = data;//data of node is the data that we want to insert
        node.left = leaf;//the left node of Node is null
        node.right = leaf;//the right node of Node is null
        node.color = RED;//every new node is red

        while (x != leaf) {//while root(x) is not null
            y = x;//assign value of x to y
            if (node.data.getVolume() < x.data.getVolume()) { //left subtree
                x = x.left;//insert data to left subtree
            } else {
                x = x.right;//insert data to right subtree
            }
        }

        node.parent = y; //the parent of node is y
        if (y == leaf) {//if y is null
            root = node;//then make the node as root
        } else if (node.data.getVolume() < y.data.getVolume()) { //else if the volume of node is less than y volume
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

    private void BalanceAfterInsert(RBNode node) {
        RBNode y; //help node

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

    //get the minimum volume
    private RBNode min(RBNode node) {
        while (node.left != leaf) {
            node = node.left;//the most left
        }
        return node;
    }

    public void minRecord() {
        RBNode node = min(this.root);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String minDate = dateFormat.format(node.data.getDate());
        System.out.println("Date with minimum value of Volume is:" + minDate + " with Volume:" + node.data.getVolume());
    }

    public void maxRecord() {
        RBNode node = max(this.root);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String maxDate = dateFormat.format(node.data.getDate());
        System.out.println("Date with maximum value of Volume is:" + maxDate + " with Volume:" + node.data.getVolume());
    }

    //get the maximum volume
    private RBNode max(RBNode node) {
        while (node.right != leaf) {
            node = node.right;//the most right
        }
        return node;
    }

}


