package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree {
    private List<Level> levels;
    private int nodesQuantity;
    private int n;
    private int m;

    public Tree() {
        this.levels = new ArrayList<>();
        this.nodesQuantity = 0;
    }

    public int nodesQuantity() {
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
            hangingNodesQuantity += level.getNodes().stream().filter(Node::isHanging).count();
        }
        return hangingNodesQuantity;
    }

    public void generate() {
        levels = new ArrayList<>();

        //root node
        levels.add(new Level(new Node(1)));
        int nodesToGenerate = n - 1;
        nodesQuantity = 1;

        int level = 1;

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
        levels = new ArrayList<>();

        //root node
        levels.add(new Level(new Node(1)));
        int nodesToGenerate = n - 1;
        nodesQuantity = 1;

        int level = 1;

        while (nodesToGenerate > 0) {
            level++;
            levels.add(new Level());

            Level previousLevel = getLevel(level - 1);
            Level currentLevel = getLevel(level);

            //filling the level
            for (int i = 0; i < previousLevel.nodesQuantity(); i++) {
                Node currentNode = previousLevel.getNode(i);

                //generating child nodes
                currentNode.setChildQuantity(m - 1);
                for (int j = 0; j < m - 1; j++) {
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

    public double expectedChildQuantity() {
        double expectedChildQuantity = 0;
        for (int i = 1; i < childQuantities().size(); i++) {
            expectedChildQuantity += i * (double) childQuantities().get(i) / nodesQuantity;
        }
        return expectedChildQuantity;
    }

    public List<Integer> childQuantities() {
        List<Integer> childQuantities = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int nodesQuantity = 0;
            for (Level level : levels) {
                int n = i;
                nodesQuantity += level.getNodes().stream().filter(node -> node.getChildQuantity() == n).count();
            }
            childQuantities.add(nodesQuantity);
        }
        return childQuantities;
    }

    public void printChildQuantityDistribution() {
        for (int i = 0; i < childQuantities().size(); i++) {
            System.out.println(i + ":\t" + childQuantities().get(i));
        }
    }

    public void printNodesTable() {
        for (int i = 0; i < levels.size(); i++) {
            Level level = getLevel(i + 1);
            System.out.print("Уровень " + i + ": ");
            for (int j = 0; j < level.nodesQuantity(); j++) {
                System.out.print(level.getNode(j) + " ");
            }
            System.out.println();
        }
    }

    public void printHangingNodesTable() {
        for (int i = 0; i < levels.size(); i++) {
            Level level = getLevel(i + 1);
            System.out.print("Уровень " + i + ": ");
            if (level.containsHanging()) {
                for (int j = 0; j < level.nodesQuantity(); j++) {
                    Node node = level.getNode(j);
                    if (node.isHanging()) {
                        System.out.print(node + " ");
                    }
                }
                System.out.println();
            } else {
                System.out.println("нет висячих вершин");
            }
        }
    }

    private Level getLevel(int level) {
        return levels.get(level - 1);
    }
}
