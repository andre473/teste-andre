package com.example.apicaixaeletronico.dto;

import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cliente;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaqueDTO {

    private BigDecimal valor;
    private Cliente cliente;
    private CaixaEletronico caixaEletronico;


}
