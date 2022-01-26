package com.example.apicaixaeletronico.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CaixaEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private BigDecimal total;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cedula> cedulas;

    public List<Cedula> getCedulas() {
        return nonNull(cedulas)
                ? cedulas.stream().sorted(Comparator.comparingInt(Cedula::getValor).reversed()).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public void calculaTotal() {
        this.total = BigDecimal.valueOf(cedulas.stream().mapToInt(cedula -> cedula.getQuantidade() * cedula.getValor()).sum());
    }
}

