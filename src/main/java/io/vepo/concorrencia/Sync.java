package io.vepo.concorrencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Sync {
    private int counter;

    public Sync() {
        counter = 0;
    }

    public synchronized void printAndIncrement() {
        counter++;
        System.err.println(String.format("1 - Thread [%s] valor:%d", Thread.currentThread().getName(), counter));
    }

    public synchronized void printAndIncrement2() {
        counter++;
        System.err.println(String.format("2 - Thread [%s] valor:%d", Thread.currentThread().getName(), counter));
    }

    public static void main(String[] args) {
        List<Integer> valores = new LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(15);
        List<Future<?>> allFuture = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        // Sync sync = new Sync();
        for (int i = 0; i < 1000; ++i) {
            //final int x = i;
            // allFuture.add(executor.submit(sync::printAndIncrement));
            // allFuture.add(executor.submit(sync::printAndIncrement2));
            allFuture.add(executor.submit(() -> System.out.println("Contador: " + counter.incrementAndGet())));
        }
        //allFuture.forEach(f -> {
        //    try {
        //        f.get();
        //    } catch (InterruptedException | ExecutionException e) {
        //        Thread.currentThread().interrupt();
        //    }
        //});
        //System.out.println(valores);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
