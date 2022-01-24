package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.exception.CaixaEletronicoExcepetion;
import com.example.apicaixaeletronico.exception.SaqueException;
import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CaixaEletronicoService  {

    private static final Logger logger = Logger.getLogger(CaixaEletronicoService.class.getName());

    private List<Cedula> cedulas;

    CaixaEletronicoService() {
        this.cedulas = depositoEmCaixa(5);
    }

    public boolean existsSaldoSuficienteEmCaixa(Integer valorSaque) {
        int total = saldoDisponivelEmCaixa(cedulas);
        if (valorSaque < total) {
            logger.info("Saldo Disponivel em Caixa: {}" + total);
            return true;
        }
        logger.warning("Saldo em Caixa Insuficiente");
        throw new SaqueException("Saldo em Caixa Insuficiente");
    }


    public CaixaEletronico getDetalhesCaixaEletronico() {
        return new CaixaEletronico(cedulas);
    }

    public Integer saldoDisponivelEmCaixa(List<Cedula> cedulas) {
        return new CaixaEletronico(cedulas).getTotal();
    }

    public List<Cedula> depositoEmCaixa(Integer quantidade) {
        List<Cedula> cedulas = new ArrayList<>();
        if (Objects.nonNull(quantidade) && quantidade > 0) {
            cedulas.add(new Cedula(10, quantidade));
            cedulas.add(new Cedula(20, quantidade));
            cedulas.add(new Cedula(50, quantidade));
            cedulas.add(new Cedula(100, quantidade));

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
}

