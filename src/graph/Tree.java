package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tree {
    private ArrayList<Level> levels;
    private int edgesQuantity;
    private int nodesQuantity;

    public Tree() {
        levels = new ArrayList<>();
    }

    public int getEdgesQuantity() {
        return edgesQuantity;
    }

    public void setEdgesQuantity(int edgesQuantity) {
        this.edgesQuantity = edgesQuantity;
    }

    public int getNodesQuantity() {
        return nodesQuantity;
    }

    public void setNodesQuantity(int nodesQuantity) {
        this.nodesQuantity = nodesQuantity;
    }

    public void generate() {
        //root node
        levels.add(new Level(new Node(1)));
        nodesQuantity--;

        int level = 1;
        int number = 2;

        while (nodesQuantity > 0) {
            level++;
            levels.add(new Level());

            Level previousLevel = getLevel(level - 1);
            Level currentLevel = getLevel(level);

            //filling the level
            for (int i = 0; i < previousLevel.nodesQuantity(); i++) {
                Node currentNode = previousLevel.getNode(i);

                //generating child nodes
                int nodesToGenerate = new Random().nextInt(edgesQuantity);
                for (int j = 0; j < nodesToGenerate; j++) {
                    Node nodeToAdd = new Node(currentNode, number);
                    currentNode.setHanging(false);
                    number++;

                    //stop rule
                    currentLevel.addNode(nodeToAdd);
                    if (nodesQuantity == 1) {
                        return;
                    } else {
                        nodesQuantity--;
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println("Table");

        for (int i = 0; i < levels.size(); i++) {
            Level level = getLevel(i + 1);
            for (int j = 0; j < level.nodesQuantity(); j++) {
                System.out.print(level.getNode(j) + " ");
            }
            System.out.println();
        }

        System.out.println("\nHanging Table");

        for (int i = 0; i < levels.size(); i++) {
            Level level = getLevel(i + 1);

            if (level.containsHanging()) {
                System.out.print("Level " + (i + 1) + ": ");

                for (int j = 0; j < level.nodesQuantity(); j++) {
                    Node node = level.getNode(j);
                    if (node.isHanging()) {
                        System.out.print(node + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    private Level getLevel(int level) {
        return levels.get(level - 1);
    }

    private class Level {
        private ArrayList<Node> nodes;

        Level() {
            this.nodes = new ArrayList<>();
        }

        Level(Node... nodes) {
            this();
            this.nodes.addAll(Arrays.asList(nodes));
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
}
