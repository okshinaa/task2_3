package ru.vsu.cs.okshina_v_a;

import java.util.Arrays;

public class ConsoleMain {
    public static void main(String[] args) throws Exception {
        MySimpleQueue<Integer> X = new MySimpleQueue<>();
        MySimpleQueue<Integer> Y = new MySimpleQueue<>();

        X.addLast(1);
        X.addLast(2);
        X.addLast(3);

        Y.addLast(4);
        Y.addLast(5);
        Y.addLast(6);

        System.out.println(Arrays.toString(ExtractingQueue.transformMyQueue(X, Y)));
    }
}