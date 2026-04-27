package io.vepo.java101.cafezinhos;

import java.util.Objects;

public class Pessoa implements Comparable<Pessoa> {
    private String nome;
    private int idade;
    private Double saldo; // Wrapper Double (permite null)

    public Pessoa(String nome, int idade, Double saldo) {
        this.nome = nome;
        this.idade = idade;
        this.saldo = saldo;
    }

    // Getters para uso nos checkpoints
    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    // Implementação de Comparable: ordenação natural por idade
    @Override
    public int compareTo(Pessoa outra) {
        return Integer.compare(this.idade, outra.idade);
    }

    // equals e hashCode baseados em nome e idade (critério definido)
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pessoa pessoa = (Pessoa) o;
        return idade == pessoa.idade && Objects.equals(nome, pessoa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }

    // toString reescrito
    @Override
    public String toString() {
        return String.format("Pessoa{nome='%s', idade=%d, saldo=%.2f}",
                             nome, idade, saldo != null ? saldo : 0.0);
    }
}
