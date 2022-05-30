package ru.vsu.cs.okshina_v_a;

public class ExtractingQueue {
    public static int[] transformMyQueue(MySimpleQueue<Integer> X, MySimpleQueue<Integer> Y) throws Exception {

        int counter = 0;
        while (!X.isEmpty() && !Y.isEmpty()) {
            counter++;
            int x = X.getFirst();
            int y = Y.getFirst();
            if (x <= y) {
                int max = x + y;
                X.addLast(max);
                Y.removeFirst();
            } else {
                int min = x - y;
                Y.addLast(min);
                X.removeFirst();
            }


        }

        return new int[]{counter};
    }
}
