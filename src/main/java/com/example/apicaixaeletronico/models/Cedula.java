package com.example.apicaixaeletronico.models;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table
@AllArgsConstructor
public class Cedula {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer valor;
    @Column
    private Integer quantidade;

    public Cedula() {
    }

    public Cedula(Integer valor, Integer quantidade) {
        this.valor=valor;
        this.quantidade=quantidade;
    }
}
