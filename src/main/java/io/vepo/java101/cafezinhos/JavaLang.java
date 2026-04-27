package io.vepo.java101.cafezinhos;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

// Clase que demonstra equals, hashCode, toString, wrappers e operações de sistema
class JavaLang {

    public static void main(String[] args) {
        System.out.println("=== EPISÓDIO 1 - API java.lang (e operações de sistema) ===\n");

        // ------------------------------------------------------------
        // CHECKPOINT 1: Criação e toString
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 1] Criando pessoas e exibindo toString:");
        Pessoa p1 = new Pessoa("Alice", 30, 1250.75);
        Pessoa p2 = new Pessoa("Bob", 25, 850.00);
        Pessoa p3 = new Pessoa("Alice", 30, 9999.99); // mesmo nome/idade que p1
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 2: equals e hashCode
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 2] Comparando objetos com equals:");
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // false
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // true (mesmo nome/idade)
        System.out.println("HashCode p1: " + p1.hashCode());
        System.out.println("HashCode p3: " + p3.hashCode());   // deve ser igual
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 3: StringBuilder para relatório eficiente
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 3] Montando relatório com StringBuilder:");
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("RELATÓRIO DE PESSOAS\n");
        relatorio.append("--------------------\n");
        relatorio.append(p1).append("\n");
        relatorio.append(p2).append("\n");
        relatorio.append(p3).append("\n");
        relatorio.append("Total de pessoas: ").append(3);
        System.out.println(relatorio.toString());
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 4: Autoboxing / Unboxing (wrapper Double + operações)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 4] Autoboxing e operações com Double:");
        Double saldoAlice = p1.getSaldo();   // autoboxing: double -> Double
        double saldoBob = p2.getSaldo();     // unboxing: Double -> double
        double soma = saldoAlice + saldoBob; // unboxing automático
        System.out.printf("Saldo Alice (wrapper): %.2f\n", saldoAlice);
        System.out.printf("Saldo Bob (primitivo após unbox): %.2f\n", saldoBob);
        System.out.printf("Soma dos saldos: %.2f\n", soma);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 5: Classe Math
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 5] Usando Math para arredondar e potência:");
        double media = soma / 2;
        long mediaArredondada = Math.round(media);
        double quadradoMedia = Math.pow(media, 2);
        System.out.printf("Média dos saldos: %.2f\n", media);
        System.out.printf("Média arredondada (Math.round): %d\n", mediaArredondada);
        System.out.printf("Quadrado da média (Math.pow): %.2f\n", quadradoMedia);
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 6: System.arraycopy
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 6] Copiando array de pessoas com System.arraycopy:");
        Pessoa[] original = {p1, p2, p3};
        Pessoa[] destino = new Pessoa[5]; // espaço maior
        System.arraycopy(original, 0, destino, 0, original.length);
        System.out.println("Array original: " + java.util.Arrays.toString(original));
        System.out.println("Array destino após cópia: " + java.util.Arrays.toString(destino));
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 7: System.currentTimeMillis (medir tempo simples)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 7] Medindo tempo de uma operação fictícia:");
        long inicio = System.currentTimeMillis();
        long somaLong = 0;
        for (int i = 0; i < 10_000_000; i++) {
            somaLong += i;
        }
        long fim = System.currentTimeMillis();
        System.out.printf("Tempo do loop de 10M iterações: %d ms\n", (fim - inicio));
        System.out.println("Soma calculada (irrelevante): " + somaLong);
        System.out.println();

        // ================= NOVOS CHECKPOINTS (System Operations) =================

        // ------------------------------------------------------------
        // CHECKPOINT 8: System Properties e Environment Variables
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 8] Consultando propriedades e variáveis de ambiente do sistema:");
        Properties props = System.getProperties();
        System.out.println("Java version: " + props.getProperty("java.version"));
        System.out.println("OS name: " + props.getProperty("os.name"));
        System.out.println("User home: " + props.getProperty("user.home"));

        Map<String, String> env = System.getenv();
        System.out.println("PATH (ou PATH): " + env.getOrDefault("PATH", env.getOrDefault("Path", "não definido")));
        System.out.println("JAVA_HOME: " + env.getOrDefault("JAVA_HOME", "não definido"));
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 9: Runtime (memória, processadores, execução de comandos)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 9] Usando Runtime para info do sistema e execução básica:");
        Runtime rt = Runtime.getRuntime();
        System.out.printf("Processadores disponíveis: %d\n", rt.availableProcessors());
        System.out.printf("Memória total (MB): %.2f\n", rt.totalMemory() / (1024.0 * 1024));
        System.out.printf("Memória livre (MB): %.2f\n", rt.freeMemory() / (1024.0 * 1024));

        // Exemplo: executar comando "java -version" (funciona em qualquer SO com java no PATH)
        try {
            Process p = rt.exec("java -version");
            System.out.println("Saída de 'java -version' (error stream, pois version escreve em stderr):");
            p.getErrorStream().transferTo(System.out);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao executar comando via Runtime: " + e.getMessage());
        }
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 10: ProcessBuilder (controle mais fino de processos externos)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 10] Usando ProcessBuilder para listar arquivos/diretórios:");
        ProcessBuilder pb;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            pb = new ProcessBuilder("cmd.exe", "/c", "dir");
        } else {
            pb = new ProcessBuilder("ls", "-la");
        }
        pb.redirectErrorStream(true); // mescla stderr com stdout
        try {
            Process process = pb.start();
            System.out.println("Saída do comando de listagem:");
            process.getInputStream().transferTo(System.out);
            int exitCode = process.waitFor();
            System.out.println("\nCódigo de saída: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro com ProcessBuilder: " + e.getMessage());
        }
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 11: ClassLoader (carregamento dinâmico de classes)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 11] Explorando ClassLoader:");
        ClassLoader cl = Pessoa.class.getClassLoader();
        System.out.println("ClassLoader da classe Pessoa: " + cl);
        System.out.println("ClassLoader do sistema (System ClassLoader): " + ClassLoader.getSystemClassLoader());
        System.out.println("ClassLoader pai (se houver): " + (cl != null ? cl.getParent() : "null (bootstrap)"));
        
        // Carregar uma classe dinamicamente (opcional)
        try {
            Class<?> stringClass = ClassLoader.getSystemClassLoader().loadClass("java.lang.String");
            System.out.println("Classe 'java.lang.String' carregada via ClassLoader: " + stringClass.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a classe: " + e.getMessage());
        }
        System.out.println();

        // ------------------------------------------------------------
        // CHECKPOINT 12: Process (representação do processo atual)
        // ------------------------------------------------------------
        System.out.println("[CHECKPOINT 12] Informações do processo atual (ProcessHandle):");
        ProcessHandle current = ProcessHandle.current();
        System.out.println("PID do processo atual: " + current.pid());
        System.out.println("Comando executado: " + current.info().command().orElse("desconhecido"));
        System.out.println("Argumentos: " + current.info().arguments().map(_args -> String.join(" ", _args)).orElse("nenhum"));
        System.out.println("Tempo de início: " + current.info().startInstant().orElse(null));
        System.out.println("Tempo de CPU total: " + current.info().totalCpuDuration().orElse(null));

        System.out.println("\n=== FIM DO EXEMPLO - TODOS OS CHECKPOINTS (1 a 12) EXECUTADOS ===");
    }
}