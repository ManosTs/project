class Node {
    public Record data;
    public Node parent;
    public Node left; //points to the left child
    public Node right; //points to the right child
    public boolean color;

    public Node(Record data) {this.data = data;}

    public Node(){};

}
