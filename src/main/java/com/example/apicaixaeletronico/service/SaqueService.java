package com.example.apicaixaeletronico.service;


import com.example.apicaixaeletronico.dto.CedulaDTO;
import com.example.apicaixaeletronico.exception.SaqueException;
import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.repositories.CaixaEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Classe responsavél por fazer o processo de saque.
 */

@Service
public class SaqueService {

    private static final Logger logger = Logger.getLogger(SaqueService.class.getName());

    @Autowired
    private CaixaEletronicoRepository repository;

    public List<CedulaDTO> sacar(BigDecimal valor, Cliente clienteByCPF, CaixaEletronico caixaEletronico) {

        isValidateValorSaque(valor);
        existsSaldoSuficienteEmCaixa(caixaEletronico, clienteByCPF, valor);

        return processSelecaoCedulas(valor, clienteByCPF, caixaEletronico);
    }

//    @TODO - ATUALIZAR SALDO DO CLIENTE
    private List<CedulaDTO> processSelecaoCedulas(BigDecimal valorOriginal, Cliente clienteByCPF, CaixaEletronico detalhesCaixaEletronico) {

        logger.info("Processo de Seleção de Células. Cedulas disponiveis: " + detalhesCaixaEletronico.getCedulas());

        List<CedulaDTO> cedulasRecidas = new ArrayList<>();
        int valor = valorOriginal.intValue();

        for (Cedula cedula : detalhesCaixaEletronico.getCedulas()) {
            int quantidade = valor / cedula.getValor();

            if (quantidade > 0 && cedula.getQuantidade() >= quantidade) {
                int restante = Math.subtractExact(cedula.getQuantidade(), quantidade);
                cedula.setQuantidade(restante);
                cedulasRecidas.add(new CedulaDTO(cedula.getValor(), quantidade));

            }

            valor = valor % cedula.getValor();
        }

        detalhesCaixaEletronico.calculaTotal();
        repository.save(detalhesCaixaEletronico);

        return cedulasRecidas;
    }

//    @TODO - IMPLEMENTAR VERIFICACAO SALDO CLIENTE
    public boolean existsSaldoSuficienteEmCaixa(CaixaEletronico caixaEletronico, Cliente clienteByCPF, BigDecimal valorSaque) {

        int total = caixaEletronico.getTotal().intValue();
        if (valorSaque.intValue() < total) {
            logger.info("Saldo Disponivel em Caixa: " + total);
            return true;
        }
        logger.warning("Saldo em Caixa Insuficiente");
        throw new SaqueException("Saldo em Caixa Insuficiente");
    }

    private boolean isValidateValorSaque(BigDecimal valorSaque) {

        if (Objects.nonNull(valorSaque) && valorSaque.intValue() % 10 == 0) {
            logger.info("Valor Válido para Saque.");
            return true;
        }

        logger.warning("Valor de Saque Inválido.");
        throw new SaqueException("Valor de Saque Inválido.");
    }

}
