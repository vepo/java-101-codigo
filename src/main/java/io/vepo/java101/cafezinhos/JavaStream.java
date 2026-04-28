package io.vepo.java101.cafezinhos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStream {
    public static void main(String[] args) {
        System.out.println("=== EPISÓDIO 4 - java.util.stream (Programação Funcional) ===\n");
        // Criando uma lista de pessoas para os exemplos
        List<Pessoa> pessoas = Arrays.asList(new Pessoa("Alice", 30, 5200.00),
                                             new Pessoa("Bob", 17, 1800.00),
                                             new Pessoa("Carol", 25, 4700.00),
                                             new Pessoa("Bob", 30, 5500.00), // mesmo nome, idade diferente
                                             new Pessoa("David", 40, 9000.00),
                                             new Pessoa("Eva", 22, 3200.00));
        System.out.println("Lista original:");
        pessoas.forEach(System.out::println);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 1: filter + forEach (imutável)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 1] Filtrar maiores de idade:");
        var pessoaStream = pessoas.stream() //
                                  .filter(p -> p.getIdade() >= 18);
        System.out.print("Avaliando Stream");
        pessoaStream.forEach(System.out::println);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 2: map (transformar nomes em maiúsculas)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 2] Extrair nomes em maiúsculas:");
        List<String> nomesMaiusculos = pessoas.stream()
                                              .map(Pessoa::getNome)
                                              .map(String::toUpperCase)
                                              .collect(Collectors.toList());
        System.out.println(nomesMaiusculos);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 3: sorted + distinct (nomes únicos ordenados)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 3] Nomes únicos em ordem alfabética:");
        pessoas.stream()
               .map(Pessoa::getNome)
               .distinct()
               .sorted()
               .forEach(System.out::println);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 4: limit + skip (paginação)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 4] Segunda página (2 elementos, pulando os 2 primeiros):");
        pessoas.stream()
               .skip(2)
               .limit(2)
               .forEach(System.out::println);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 5: anyMatch, allMatch, noneMatch
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 5] Operações de condição:");
        boolean temMenor = pessoas.stream().anyMatch(p -> p.getIdade() < 18);
        boolean todosTemSaldo = pessoas.stream().allMatch(p -> p.getSaldo() > 1000);
        boolean nenhumIdoso = pessoas.stream().noneMatch(p -> p.getIdade() > 80);
        System.out.printf("Algum menor de idade? %b\n", temMenor);
        System.out.printf("Todos têm saldo > 1000? %b\n", todosTemSaldo);
        System.out.printf("Nenhum idoso (80+)? %b\n", nenhumIdoso);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 6: findFirst e Optional
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 6] Primeira pessoa com saldo > 8000:");
        Optional<Pessoa> primeiraRica = pessoas.stream()
                                               .filter(p -> p.getSaldo() > 8000)
                                               .findFirst();
        primeiraRica.ifPresentOrElse(
                                     System.out::println,
                                     () -> System.out.println("Ninguém com saldo > 8000"));
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 7: reduce (soma de saldo)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 7] Soma de todos os saldo com reduce:");
        double somaSalarios = pessoas.stream()
                                     .map(Pessoa::getSaldo)
                                     .reduce(0.0, Double::sum);
        System.out.printf("Total: %.2f\n", somaSalarios);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 8: Collectors.groupingBy (agrupar por idade)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 8] Agrupando pessoas por idade:");
        Map<Integer, List<Pessoa>> porIdade = pessoas.stream()
                                                     .collect(Collectors.groupingBy(Pessoa::getIdade));
        porIdade.forEach((idadex, lista) -> {
            System.out.printf("Idade %d: %s\n", idadex, lista);
        });
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 9: Collectors.joining (strings)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 9] Lista de nomes separados por vírgula:");
        String nomesConcatenados = pessoas.stream()
                                          .map(Pessoa::getNome)
                                          .collect(Collectors.joining(", "));
        System.out.println(nomesConcatenados);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 10: flatMap (achatar listas aninhadas)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 10] FlatMap: juntando várias listas em uma única stream:");
        List<List<String>> listaDeListas = Arrays.asList(
                                                         Arrays.asList("A", "B"),
                                                         Arrays.asList("C", "D"),
                                                         Arrays.asList("E"));
        List<String> listaUnica = listaDeListas.stream()
                                               .flatMap(List::stream)
                                               .collect(Collectors.toList());
        System.out.println(listaUnica);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 11: Stream de primitivos (IntStream)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 11] IntStream.range e soma:");
        int somaPrimos = IntStream.range(1, 10) // 1 a 9
                                  .filter(n -> n % 2 != 0) // ímpares
                                  .sum();
        System.out.printf("Soma dos ímpares de 1 a 9: %d\n", somaPrimos);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 12: Stream paralelo (parallelStream)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 12] ParallelStream - soma de saldo (ordem não garantida):");
        double somaParalela = pessoas.parallelStream()
                                     .mapToDouble(Pessoa::getSaldo)
                                     .sum();
        System.out.printf("Soma paralela dos saldo: %.2f\n", somaParalela);
        System.out.println();

        Arrays.stream(new Pessoa[] {});

        System.out.println("=== FIM DO EXEMPLO - TODOS OS CHECKPOINTS EXECUTADOS ===");
    }
}
