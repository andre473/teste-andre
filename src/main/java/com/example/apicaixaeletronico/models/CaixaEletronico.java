package com.example.apicaixaeletronico.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table
public class CaixaEletronico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private BigDecimal total;

    @OneToMany
    private List<Cedula> cedulas;

    public CaixaEletronico() {
    }

    public CaixaEletronico(List<Cedula> cedulas) {
        this.cedulas = cedulas;
    }

    public BigDecimal getTotal() {
        return total = BigDecimal.valueOf(cedulas.stream().mapToInt(cedula -> cedula.getQuantidade() * cedula.getValor()).sum());
    }

    public List<Cedula> getCedulas() {
        return cedulas;
    }

    public void calculaTotal() {
        this.total = getTotal();
    }
}

