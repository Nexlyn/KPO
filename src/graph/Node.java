package graph;

public class Node {
    private Node parent;
    private int number;
    private boolean isHanging;
    private int childQuantity;

    public Node() {
        isHanging = true;
        number = 0;
        parent = null;
        childQuantity = 0;
    }

    public Node(int number) {
        this();
        this.number = number;
    }

    public Node(Node parent, int number) {
        this(number);
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isHanging() {
        return isHanging;
    }

    public void setHanging(boolean hanging) {
        isHanging = hanging;
    }

    public int getChildQuantity() {
        return childQuantity;
    }

    public void setChildQuantity(int childQuantity) {
        this.childQuantity = childQuantity;
    }

    @Override
    public String toString() {
        return (parent == null ? "0" : parent.getNumber()) + "-" + number;
    }
}
