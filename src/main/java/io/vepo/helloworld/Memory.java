package io.vepo.helloworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Memory {    
    private static AtomicBoolean running = new AtomicBoolean(true);
    public static record DeepInfo(int deep) {}

    private final List<DeepInfo> STORED_OBJECTS = new ArrayList<>();

    private static void stackOverflow(int deep, Memory memory) {
        var obj = new DeepInfo(deep);
        int newDeep = deep;
        newDeep = newDeep + 5;
        // obj.deep ++;
        System.out.println("New Deep " + newDeep + " deep " + deep);
        System.out.println("OBJ " + obj + " deep " + deep); 
        if (deep % 2 == 1) {
            memory.STORED_OBJECTS.add(obj);
        }
        System.out.println("Profundida: " + deep);
        if (deep == 10) {
            while (running.get()) {
                System.out.println("Sleeping... ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return;
        }
        stackOverflow(++deep, memory);
        System.out.println("Saí!!!! " + obj + " Hash: "+  System.identityHashCode(obj));
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                running.set(false);
            }
        });
        stackOverflow(0, new Memory());
        int[] p1 = new int[4];        
        System.out.println("Hello World!!!");
    }


    
}
