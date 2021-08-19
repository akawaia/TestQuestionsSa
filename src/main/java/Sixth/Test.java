package Sixth;

public class Test {
    public static void main(String[] args) {
        Tree<Integer> tree;
        int countTree = 0;
        int countNumber = 0;
        while(countTree != 10){
            tree = new TreeImpl<>(4);
            countNumber = (int) (7 + Math.random() * 8);
            for (int i = 0; i < countNumber; i++) {
                tree.add((int)(Math.random() * 50 - 25));
            }
            countTree++;
            tree.display();
        }

    }
}
