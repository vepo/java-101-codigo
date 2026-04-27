package io.vepo.java101.cafezinhos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class JavaCollections {
    public static void main(String[] args) {
         System.out.println("=== EPISÓDIO 2 - java.util.Collections ===\n");
        // ------------------------------------------------------------
        // CHECKPOINT 1: Criando uma lista e ordenando (Comparable)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 1] Lista de pessoas com ordenação pela insercão:"); 
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Carol", 28, 3000.00));
        pessoas.add(new Pessoa("Alice", 30, 1250.75));
        pessoas.add(new Pessoa("Bob", 25, 850.00));
        System.out.println(pessoas);

        System.out.println("Ordenando por idade (Comparable):");
        Collections.sort(pessoas);
        System.out.println(pessoas);

        // ------------------------------------------------------------
        // CHECKPOINT 2: Ordenação com Comparator customizado (por nome)
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 2] Ordenação por nome (Comparator):");
        Comparator<Pessoa> porNome = Comparator.comparing(Pessoa::getNome).reversed();
        Collections.sort(pessoas, porNome);
        System.out.println(pessoas);

        // ------------------------------------------------------------
        // CHECKPOINT 3: Reverse da lista
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 3] Invertendo a ordem:");
        Collections.reverse(pessoas);
        System.out.println(pessoas);

        // ------------------------------------------------------------
        // CHECKPOINT 4: Shuffle (embaralhar)
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 4] Embaralhando a lista:");
        Collections.shuffle(pessoas);
        System.out.println(pessoas);

        // ------------------------------------------------------------
        // CHECKPOINT 5: min, max e frequency
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 5] Valores extremos e frequência:");
        Pessoa maisJovem = Collections.min(pessoas);       // baseado em Comparable (idade)
        Pessoa maisVelha = Collections.max(pessoas);
        

        System.out.println("Mais jovem: " + maisJovem);
        System.out.println("Mais velha: " + maisVelha);

        // frequency com uma pessoa criada com mesmo nome/idade
        Pessoa aliceClone = new Pessoa("Alice", 30, 0.0);
        int quantasAlice = Collections.frequency(pessoas, aliceClone);
        System.out.println("Quantidade de pessoas com nome Alice e idade 30: " + quantasAlice);

        // ------------------------------------------------------------
        // CHECKPOINT 6: BinarySearch (exige lista ordenada)
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 6] Busca binária por nome (usando Comparator):");
        Collections.sort(pessoas, porNome);
        System.out.println("Lista ordenada por nome: " + pessoas);
        int indice = Collections.binarySearch(pessoas,
                new Pessoa("Bob", 0, null), porNome);

        System.out.println("Posição de Bob: " + indice);

        // ------------------------------------------------------------
        // CHECKPOINT 7: Coleções imutáveis (unmodifiable)
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 7] Criando view imutável da lista:");
        List<Pessoa> protegida = Collections.unmodifiableList(pessoas);
        System.out.println("Lista imutável: " + protegida);
        try {
            protegida.add(new Pessoa("Invasor", 99, 0.0));
        } catch (UnsupportedOperationException e) {
            System.out.println("Tentativa de modificação lançou: " + e);
        }

        // ------------------------------------------------------------
        // CHECKPOINT 8: Collections.singleton... e nCopies
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 8] Coleções especiais:");
        Set<String> unico = Collections.singleton("ApenasUm");
        System.out.println("singletonSet: " + unico);
        List<String> repeticoes = Collections.nCopies(3, "Java");
        System.out.println("nCopies: " + repeticoes);

        // ------------------------------------------------------------
        // CHECKPOINT 9: Preenchimento e cópia
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 9] fill e copy:");
        List<String> destino = new ArrayList<>(Arrays.asList("", "", ""));
        Collections.fill(destino, "Vazio");
        System.out.println("Após fill: " + destino);
        List<String> origem = Arrays.asList("A", "B", "C");
        Collections.copy(destino, origem);
        System.out.println("Após copy (destino deve ter mesmo tamanho): " + destino);

        // ------------------------------------------------------------
        // CHECKPOINT 10: Sincronização para threads (demonstração conceitual)
        // ------------------------------------------------------------
        System.out.println("\n[CHECKPOINT 10] Wrapper sincronizado:");
        List<Pessoa> listaSegura = Collections.synchronizedList(new ArrayList<>());
        listaSegura.add(new Pessoa("Thread", 1, 100.0));
        System.out.println("Lista sincronizada criada. Use em ambiente multi-thread.");

        System.out.println("\n=== FIM DO EXEMPLO - TODOS OS CHECKPOINTS EXECUTADOS ===");
    }
}
