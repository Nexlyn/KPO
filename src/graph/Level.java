package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {
    private ArrayList<Node> nodes;

    Level() {
        this.nodes = new ArrayList<>();
    }

    Level(Node... nodes) {
        this();
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    void addNode(Node node) {
        nodes.add(node);
    }

    Node getNode(int index) {
        return nodes.get(index);
    }

    int nodesQuantity() {
        return nodes.size();
    }

    boolean containsHanging() {
        return nodes.stream().anyMatch(Node::isHanging);
    }
}
