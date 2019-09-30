package graph;

public class Node {
    private Node parent;
    private int number;
    private boolean isHanging;

    public Node() {
        isHanging = true;
    }

    public Node(int number) {
        this.isHanging = true;
        this.number = number;
    }

    public Node(Node parent, int number) {
        this.parent = parent;
        this.number = number;
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

    @Override
    public String toString() {
        return parent == null ? "0" : parent.getNumber() + "-" + number;
    }
}
