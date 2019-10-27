import graph.Tree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("N: ");
        int N = new Scanner(System.in).nextInt();
        System.out.print("m: ");
        int m = new Scanner(System.in).nextInt();
        System.out.print("R: ");
        int R = new Scanner(System.in).nextInt();

        long beginTime = System.currentTimeMillis();

        Tree tree = new Tree();
        for (int i = 0; i < R; i++) {
            do {
                tree.generate(N, m);
            } while (tree.getNodesQuantity() < 10);
            System.out.println("Tree №" + (i + 1) + ":\tn = " + tree.getNodesQuantity() + "\t\ta = " + tree.alpha());
        }

        System.out.println("Done. " + (System.currentTimeMillis() - beginTime) + "ms");
    }
}