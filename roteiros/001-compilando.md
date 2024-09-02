# Java 101: Compilando seu código Java

Para mais informações:
 - https://blog.vepo.dev/posts/java-101
 - https://blog.vepo.dev/java-101/

- [X] O que é um programa de computador?
  - [X] Linguagem de Máquina vs Linguagem Assembly
  - [X] Linguagem formal
  - [X] Porque linguagens de programação são formais e não naturais
  - [X] Léxico
  - [X] Gramática
- [X] O que é o Java? (Write Once Run Anywhere)
  - [X] Java SPEC https://docs.oracle.com/javase/specs/jls/se22/html/index.html
  - [X] JVM  SPEC https://docs.oracle.com/javase/specs/jvms/se22/html/index.html
- [X] Relação JVM e Java
  - [X] Java Virtual Machina
  - [X] Adição de novas linguagens ao ecossistema Java
- [X] Código Java
- [X] Como compilar um programa Java
  - [X] `javac`
  ```bash
  $ javac src/main/java/io/vepo/helloworld/HelloWorld.java -d target/classes
  $ ls --all --recursive target/classes/
  $ java -cp target/classes/ io.vepo.helloworld.HelloWorld
  ```
  - [X] IDE (Falhou)
  - [X] Maven ou Gradle

- [X] Bytecode
- [X] JDK, JVM e Java
      - [X] Java Development Kit
