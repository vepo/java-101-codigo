package io.vepo.helloworld;

import java.util.concurrent.atomic.AtomicInteger;

public class HelloWorldSintaxe {
    public static void main(String[] args) {
        System.out.println("Olá mundo");

        String variavel = "abc";
        System.out.println("Valor de variavel=" + variavel);

        int[] x = new int[] {};
        label1: {} // Bloco vazio

        {
            String variavel2 = "xyz";
            System.out.println("Valor de variavel2=" + variavel2);
        } // Bloco

        label2:  for(AtomicInteger i = new AtomicInteger(0); cont(i); inc(i)) {
            System.out.println("Bloco: " + i);
        }

        int[] array = new int[] {0 , 1, 2, 3, 4, 5};
        for (int i = 0; i < array.length; i++) {

        }
        // System.out.println("Valor de variavel2=" + variavel2);  // Se você

        System.out.println("Iniciando for...");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.println("Valor: " + i);
            if (i > 10) {
                break;
            }
        }

        int[][] matrix = new int[][] {
                { 2, 2, 2, 3, 4, 5 },
                { 2, 4, 8, 8, 9, 9 },
                { 1, 2, 4, 5, 6, 8 },
                { 0, 3, 4, 8, 8, 9 },
                { 3, 4, 4, 6, 6, 9 },
                { 0, 3, 6, 7, 8, 8 },
        };
        linhas: for (int linha = 0; linha < matrix.length; ++linha) {
            colunas: for (int coluna = 0; coluna < matrix[linha].length; ++coluna) {
                if (matrix[linha][coluna] == 7) {
                    System.out.println("Número 7 encontrado! (" + linha + "," + coluna + ")");
                    break linhas;
                } else if (matrix[linha][coluna] > 7) {
                    System.out.println("Desistindo da linha! (" + linha + "," + coluna + ")");
                    continue linhas;
                } else if (matrix[linha][coluna] < 7) {
                    System.out.println("Pulando para próxima coluna! (" + linha + "," + coluna + ")");
                    continue colunas;
                }
                System.out.println("Código nunca executado!");
            }
        }
    } // Bloco obrigatório

    private static void inc(AtomicInteger i) {
        System.out.println("Inc: " + i);
        i.incrementAndGet();
    }

    private static boolean cont(AtomicInteger i) {
        System.out.println("Cond: " + i);
        return i.get() < 10;
    }

    // private void x() return 1; // Bloco é obrigatório no caso de método, essa construção vai falhar 
} // Bloco também obrigatório
