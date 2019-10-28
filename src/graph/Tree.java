package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tree {
    private ArrayList<Level> levels;
    private int nodesQuantity;
    private int n;
    private int m;

    public Tree() {
        this.levels = new ArrayList<>();
        this.nodesQuantity = 0;
    }

    public int getNodesQuantity() {
        return nodesQuantity;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int levelsQuantity() {
        return levels.size();
    }

    public int hangingNodesQuantity() {
        int hangingNodesQuantity = 0;
        for (Level level : levels) {
            hangingNodesQuantity += level.nodes.stream().filter(Node::isHanging).count();
        }
        return hangingNodesQuantity;
    }

    public void generate() {
        nodesQuantity = 0;
        levels = new ArrayList<>();

        //root node
        levels.add(new Level(new Node(1)));
        int nodesToGenerate = n;
        nodesToGenerate--;

        int level = 1;
        nodesQuantity = 1;

        while (nodesToGenerate > 0) {
            level++;
            levels.add(new Level());

            Level previousLevel = getLevel(level - 1);
            Level currentLevel = getLevel(level);

            //filling the level
            for (int i = 0; i < previousLevel.nodesQuantity(); i++) {
                Node currentNode = previousLevel.getNode(i);

                //generating child nodes
                int childNodesToGenerate = new Random().nextInt(m);
                currentNode.setChildQuantity(childNodesToGenerate);
                for (int j = 0; j < childNodesToGenerate; j++) {
                    nodesQuantity++;
                    Node nodeToAdd = new Node(currentNode, nodesQuantity);
                    currentNode.setHanging(false);
                    currentLevel.addNode(nodeToAdd);

                    //stop rule
                    if (nodesToGenerate == 1) {
                        return;
                    } else {
                        nodesToGenerate--;
                    }
                }
            }
            if (currentLevel.nodesQuantity() == 0) return;
        }
    }

    public void generateDetermined() {
        nodesQuantity = 0;
        levels = new ArrayList<>();

        //root node
        levels.add(new Level(new Node(1)));
        int nodesToGenerate = n;
        nodesToGenerate--;

        int level = 1;
        nodesQuantity = 1;

        while (nodesToGenerate > 0) {
            level++;
            levels.add(new Level());

            Level previousLevel = getLevel(level - 1);
            Level currentLevel = getLevel(level);

            //filling the level
            for (int i = 0; i < previousLevel.nodesQuantity(); i++) {
                Node currentNode = previousLevel.getNode(i);

                //generating child nodes
                int childNodesToGenerate = m - 1;
                currentNode.setChildQuantity(childNodesToGenerate);
                for (int j = 0; j < childNodesToGenerate; j++) {
                    nodesQuantity++;
                    Node nodeToAdd = new Node(currentNode, nodesQuantity);
                    currentNode.setHanging(false);
                    currentLevel.addNode(nodeToAdd);

                    //stop rule
                    if (nodesToGenerate == 1) {
                        return;
                    } else {
                        nodesToGenerate--;
                    }
                }
            }
            if (currentLevel.nodesQuantity() == 0) return;

        }
    }

    public double alpha() {
        return (double)nodesQuantity / hangingNodesQuantity();
    }

    public void printBarGraph() {
        System.out.println("\nChild Nodes Quantity Graph");

        for (int i = 0; i < m; i++) {
            System.out.print(i + ": ");
            int nodesQuantity = 0;
            for (Level level : levels) {
                int n = i;
                nodesQuantity += level.nodes.stream().filter(node -> node.getChildQuantity() == n).count();
            }
            System.out.println(nodesQuantity);
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

    private static class Level {
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
