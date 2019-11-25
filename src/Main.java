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
        tree.setN(N);
        tree.setM(m);

        do {
            tree.generateWithAlpha();
        } while (tree.nodesQuantity() < 10);

        System.out.println("Задание 1:\nТаблица вершин:");
        tree.printNodesTable();
        System.out.println("\nТаблица висячих вершин:");
        tree.printHangingNodesTable();
        System.out.println("\nГистограмма случайных значений числа исходящих из узлов рёбер:");
        tree.printChildQuantityDistribution();
        System.out.println("\nМат. ожидание числа исходящих из узла ребер: " + tree.expectedChildQuantity());


        System.out.println("\n\nЗадание 2:\nИсходные данные: N = " + tree.getN() + ", m = " + tree.getM() +
                "\nТаблица вершин:");
        tree.printNodesTable();
        System.out.println("\nТаблица висячих вершин:");
        tree.printHangingNodesTable();

        System.out.println("\n\nЗадание 3:\nИсходные данные: N = " + tree.getN() + ", m = " + tree.getM() +
                "\nТаблица вершин:");
        tree.generateDetermined();
        tree.printNodesTable();
        System.out.println("\nТаблица висячих вершин:");
        tree.printHangingNodesTable();

        System.out.println("\n\nЗадание 4:\nα, число вершин, число висячих вершин, высота дерева");

        double averageAlpha = 0;
        double averageNodesQuantity = 0;
        double averageHangingNodesQuantity = 0;
        double averageLevelsQuantity = 0;
        for (int i = 0; i < R; i++) {
            do {
                tree.generate();
            } while (tree.nodesQuantity() < 10);
            averageAlpha += tree.alpha();
            averageNodesQuantity += tree.nodesQuantity();
            averageHangingNodesQuantity += tree.hangingNodesQuantity();
            averageLevelsQuantity += tree.levelsQuantity();
            System.out.println(String.format("%.4f", tree.alpha()) +
                    "\t" + tree.nodesQuantity() +
                    "\t" + tree.hangingNodesQuantity() +
                    "\t" + tree.levelsQuantity());
        }
        averageAlpha /= R;
        averageNodesQuantity /= R;
        averageHangingNodesQuantity /= R;
        averageLevelsQuantity /= R;
        System.out.println("Средние значения: " +
                String.format("%.4f", averageAlpha) + " " +
                averageNodesQuantity + " " +
                averageHangingNodesQuantity + " " +
                averageLevelsQuantity);

        System.out.println("Done. " + (System.currentTimeMillis() - beginTime) + "ms");
    }
}