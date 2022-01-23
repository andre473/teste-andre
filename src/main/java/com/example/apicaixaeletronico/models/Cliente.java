package com.example.apicaixaeletronico.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "cliente")
public class Cliente {

    @Id
    @Column
    private String cpf;
    @Column
    private int idade;
    @Column
    private String nome;



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public Cliente(String cpf, int idade, String nome) {
        this.cpf = cpf;
        this.idade = idade;
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idade=" + idade +
                ", nome='" + nome + '\'' +
                '}';
    }

}
