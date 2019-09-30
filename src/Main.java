import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("N: ");
        int N = new Scanner(System.in).nextInt();
        System.out.print("m: ");
        int m = new Scanner(System.in).nextInt();

        Tree tree = new Tree();
        tree.setM(m);
        tree.setNodesQuantity(N);
        tree.generate();
        tree.print();
    }
}