package bpackage.redblacktree.volume;

class RBNode {
    public Record data;
    public RBNode parent;
    public RBNode left; //points to the left child
    public RBNode right; //points to the right child
    public boolean color;

    public RBNode(Record data) {this.data = data;}

    public RBNode(){};

}

