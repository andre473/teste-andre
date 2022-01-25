package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.exception.CaixaEletronicoExcepetion;
import com.example.apicaixaeletronico.exception.SaqueException;
import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
import com.example.apicaixaeletronico.repositories.CaixaEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CaixaEletronicoService {

    private static final Logger logger = Logger.getLogger(CaixaEletronicoService.class.getName());

    @Autowired
    private CaixaEletronicoRepository repository;

    private List<Cedula> cedulas;

    CaixaEletronicoService() {
        this.cedulas = depositoEmCaixa(5);
    }

    public boolean existsSaldoSuficienteEmCaixa(Integer valorSaque) {
        BigDecimal total = saldoDisponivelEmCaixa(cedulas);
        /*if (valorSaque < total) {
            logger.info("Saldo Disponivel em Caixa: {}" + total);
            return true;
        }*/
        logger.warning("Saldo em Caixa Insuficiente");
        throw new SaqueException("Saldo em Caixa Insuficiente");
    }


    public CaixaEletronico getDetalhesCaixaEletronico() {
        return new CaixaEletronico(cedulas);
    }

    public BigDecimal saldoDisponivelEmCaixa(List<Cedula> cedulas) {
        return new CaixaEletronico(cedulas).getTotal();
    }

    public List<Cedula> depositoEmCaixa(Integer quantidade) {
        List<Cedula> cedulas = new ArrayList<>();
        if (Objects.nonNull(quantidade) && quantidade > 0) {
         /*   cedulas.add(new Cedula(10, quantidade));
            cedulas.add(new Cedula(20, quantidade));
            cedulas.add(new Cedula(50, quantidade));
            cedulas.add(new Cedula(100, quantidade));
          */

            cedulas = cedulas.stream().sorted(Comparator.comparingInt(Cedula::getValor)
                            .reversed())
                    .collect(Collectors.toList());
            return cedulas;
        }

        throw new CaixaEletronicoExcepetion("Deposito no caixa eletronico inválido. Quantidade de notas não informada");
    }

    public void setCedulas(List<Cedula> cedulas) {
        this.cedulas = cedulas;
    }

    public void iniciaCaixa() {

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        caixaEletronico.setCedulas(Collections.emptyList());
        caixaEletronico.getCedulas().add(new Cedula(10, 5));
        caixaEletronico.getCedulas().add(new Cedula(20, 5));
        caixaEletronico.getCedulas().add(new Cedula(50, 5));
        caixaEletronico.getCedulas().add(new Cedula(100, 5));

        repository.save(caixaEletronico);
    }

}

