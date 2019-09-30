import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tree {
    private class Level {
        private ArrayList<Node> nodes;

        public Level() {
            this.nodes = new ArrayList<>();
        }

        public Level(Node... nodes) {
            this.nodes = new ArrayList<>();
            this.nodes.addAll(Arrays.asList(nodes));
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public Node getNode(int index) {
            return nodes.get(index);
        }

        public int nodesQuantity() {
            return nodes.size();
        }

        public boolean containsHanging() {
            return nodes.stream().anyMatch(Node::isHanging);
        }
    }

    private ArrayList<Level> levels;
    private int m;
    private int nodesQuantity;

    public Tree() {
        levels = new ArrayList<>();
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getNodesQuantity() {
        return nodesQuantity;
    }

    public void setNodesQuantity(int nodesQuantity) {
        this.nodesQuantity = nodesQuantity;
    }

    private Level getLevel(int level) {
        return levels.get(level - 1);
    }

    public void generate() {
        levels.add(new Level(new Node(1)));
        nodesQuantity--;
        int level = 2;
        int number = 2;

        Loop:
        while(nodesQuantity > 0) {
            levels.add(new Level());
            Level previousLevel = getLevel(level - 1);
            Level currentLevel = getLevel(level);

            //fill level
            for (int i = 0; i < previousLevel.nodesQuantity(); i++) {
                Node currentNode = previousLevel.getNode(i);

                //generate kids
                int random = new Random().nextInt(m);
                for (int j = 0; j < random; j++) {
                    Node nodeToAdd = new Node();
                    nodeToAdd.setParent(currentNode);
                    currentNode.setHanging(false);
                    nodeToAdd.setNumber(number);
                    number++;

                    currentLevel.addNode(nodeToAdd);
                    if (nodesQuantity == 1) {
                        break Loop;
                    } else {
                        nodesQuantity--;
                    }
                }
            }
            level++;
        }
    }

    public void print() {
        System.out.println("Table");
        for (int i = 0; i < levels.size(); i++) {
            System.out.print("Level " + (i + 1) + ": ");
            Level level = getLevel(i + 1);
            for (int j = 0; j < level.nodesQuantity(); j++) {
                System.out.print(level.getNode(j) + "  ");
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
                        System.out.print(node + "  ");
                    }
                }
                System.out.println();
            }
        }
    }
}
