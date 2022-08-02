package io.vepo.helloworld;

import java.util.Random;

public class StacktraceHelloWorld {
    private static void m1(int x) {
        if (x % 2 == 0 && x > 100) {
            throw new RuntimeException("Primeiro número impar depois de 100");
        }
        m2(x + new Random().nextInt(2));
    }

    private static void m2(int j) {
        if (j % 2 == 0 && j > 100) {
            throw new RuntimeException("Primeiro número par depois de 100");
        }
        m1(j + new Random().nextInt(2));
    }

    public static void main(String[] args) {
        m1(0);
    }
}
