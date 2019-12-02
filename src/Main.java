import graph.Tree;

import java.util.ArrayList;
import java.util.List;
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
            tree.generate(false, true);
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
        double alphaRandom = tree.alpha();

        System.out.println("\n\nЗадание 3:\nИсходные данные: N = " + tree.getN() + ", m = " + tree.getM() +
                "\nТаблица вершин:");
        tree.generate(true, false);
        tree.printNodesTable();
        System.out.println("\nТаблица висячих вершин:");
        tree.printHangingNodesTable();

        if (tree.alpha() > alphaRandom) {
            System.out.println("Ошибка: Значение структурного параметра α для случайного дерева всегда должны быть больше, чем для детерминированного дерева.");
            return;
        }

        System.out.println("\n\nЗадание 4:\nα, число вершин, число висячих вершин, высота дерева");

        List<Double> alphas = new ArrayList<>();
        List<Integer> nodesQuantities = new ArrayList<>();
        List<Integer> hangingNodesQuantities = new ArrayList<>();
        List<Integer> levelsQuantities = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            do {
                tree.generate(false, false);
            } while (tree.nodesQuantity() < 10);
            alphas.add(tree.alpha());
            nodesQuantities.add(tree.nodesQuantity());
            hangingNodesQuantities.add(tree.hangingNodesQuantity());
            levelsQuantities.add(tree.levelsQuantity());
            System.out.println(String.format("%.4f", tree.alpha()) +
                    "\t" + tree.nodesQuantity() +
                    "\t" + tree.hangingNodesQuantity() +
                    "\t" + tree.levelsQuantity());
        }

        double averageAlpha = alphas.stream().mapToDouble(Double::doubleValue).sum() / R;
        double averageNodesQuantity = nodesQuantities.stream().mapToDouble(Integer::doubleValue).sum() / R;
        double averageHangingNodesQuantity = hangingNodesQuantities.stream().mapToDouble(Integer::doubleValue).sum() / R;
        double averageLevelsQuantity = levelsQuantities.stream().mapToDouble(Integer::doubleValue).sum() / R;

        System.out.println("Средние значения: " +
                String.format("%.4f", averageAlpha) + " " +
                averageNodesQuantity + " " +
                averageHangingNodesQuantity + " " +
                averageLevelsQuantity);

        System.out.println("Done. " + (System.currentTimeMillis() - beginTime) + "ms");

        double alphaDispersion = alphas.stream().mapToDouble(x -> x * x / R).sum() -
                alphas.stream().mapToDouble(x -> x / R).sum();
        double nodesQuantityDispersion = nodesQuantities.stream().mapToDouble(x -> x * x / (double) R).sum() -
                Math.pow(nodesQuantities.stream().mapToDouble(x -> x / (double) R).sum(), 2);
        double hangingNodesQuantityDispersion = hangingNodesQuantities.stream().mapToDouble(x -> x * x / (double) R).sum() -
                Math.pow(hangingNodesQuantities.stream().mapToDouble(x -> x / (double) R).sum(), 2);
        double levelsDispersion = levelsQuantities.stream().mapToDouble(x -> x * x / (double) R).sum() -
                Math.pow(levelsQuantities.stream().mapToDouble(x -> x / (double) R).sum(), 2);

        System.out.println("Дисперсии: " +
                alphaDispersion + " " +
                nodesQuantityDispersion + " " +
                hangingNodesQuantityDispersion + " " +
                levelsDispersion);
    }
}