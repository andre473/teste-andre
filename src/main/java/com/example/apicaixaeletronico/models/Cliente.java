package com.example.apicaixaeletronico.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table (name = "cliente")
public class Cliente {

    @Id
    @Column
    private String cpf;
    @Column
    private Integer idade;
    @Column
    private String nome;

    @Column
    private BigDecimal saldo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return cpf != null && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
