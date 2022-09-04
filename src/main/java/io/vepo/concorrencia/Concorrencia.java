package io.vepo.concorrencia;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Concorrencia {

    static class Tarefa implements Runnable {

        private long[] tempos;

        Tarefa(long[] tempos) {
            this.tempos = tempos;
        }

        @Override
        public void run() {
            tempos[1] = System.nanoTime();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tempos[2] = System.nanoTime();            
        }
    }
    public static void main(String[] args) {
        // System.out.println(ProcessHandle.current().pid());
        // System.out.println(ProcessHandle.current().info());

        // ProcessHandle.allProcesses().forEach(System.out::println);
        long[] temposMedios = new long[] { 0l, 0l, 0l };
        for (int i = 0; i < 100; ++i) {
            long[] tempos = new long[4];
            tempos[0] = System.nanoTime();
            Thread t = new Thread(new Tarefa(tempos));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tempos[3] = System.nanoTime();
            temposMedios[0] += ((tempos[1] - tempos[0]) / 1000);
            temposMedios[1] += ((tempos[2] - tempos[1]) / 1000);
            temposMedios[2] += ((tempos[3] - tempos[0]) / 1000);
            //System.out.println(String.format("Tempo de inicialização: %dµs", (tempos[1] - tempos[0]) / 1000));
            //System.out.println(String.format("Tempo de execução     : %dµs", (tempos[2] - tempos[1]) / 1000));
            //System.out.println(String.format("Tempo total           : %dµs", (tempos[3] - tempos[0]) / 1000));
        }
        System.out.println(String.format("Tempo médio de inicialização: %dµs", temposMedios[0]));
        System.out.println(String.format("Tempo médio de execução     : %dµs", temposMedios[1]));
        System.out.println(String.format("Tempo médio total           : %dµs", temposMedios[2]));

        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        long[] temposMediosExecutor = new long[] { 0l, 0l, 0l };
        for (int i = 0; i < 100; ++i) {
            long[] temposExecutor = new long[4];
            temposExecutor[0] = System.nanoTime();
            Future<?> ft = executor.submit(new Tarefa(temposExecutor));
            try {
                ft.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            temposExecutor[3] = System.nanoTime();
            temposMediosExecutor[0] += ((temposExecutor[1] - temposExecutor[0]) / 1000);
            temposMediosExecutor[1] += ((temposExecutor[2] - temposExecutor[1]) / 1000);
            temposMediosExecutor[2] += ((temposExecutor[3] - temposExecutor[0]) / 1000);
            //System.out.println(String.format("Tempo de inicialização: %dµs", (temposExecutor[1] - temposExecutor[0]) / 1000));
            //System.out.println(String.format("Tempo de execução     : %dµs", (temposExecutor[2] - temposExecutor[1]) / 1000));
            //System.out.println(String.format("Tempo total           : %dµs", (temposExecutor[3] - temposExecutor[0]) / 1000));
        }
        System.out.println(String.format("Tempo médio de inicialização: %dµs", temposMediosExecutor[0]));
        System.out.println(String.format("Tempo médio de execução     : %dµs", temposMediosExecutor[1]));
        System.out.println(String.format("Tempo médio total           : %dµs", temposMediosExecutor[2]));
        executor.shutdown();
    }
}
