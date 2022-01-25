package com.example.apicaixaeletronico.service;


import com.example.apicaixaeletronico.exception.SaqueException;
import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
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
public class SaqueService  {

    private static final Logger logger = Logger.getLogger(SaqueService.class.getName());

    private CaixaEletronicoService caixaEletronicoService;

    public SaqueService() {
        this.caixaEletronicoService = new CaixaEletronicoService();
    }

    public List<Cedula> sacar(BigDecimal valor, Long caixaEletronicoId) {
        List<Cedula> cedulasRecidas = new ArrayList<>();

        isValidateValorSaque(valor);
        caixaEletronicoService.existsSaldoSuficienteEmCaixa(valor, caixaEletronicoId);
        CaixaEletronico detalhesCaixaEletronico = caixaEletronicoService.getDetalhesCaixaEletronico();
        processSelecaoCedulas(valor, detalhesCaixaEletronico, cedulasRecidas);

        return cedulasRecidas;
    }

    private void processSelecaoCedulas(BigDecimal valorOriginal, CaixaEletronico detalhesCaixaEletronico, List<Cedula> cedulasRecidas) {
        logger.info("Processo de Seleção de Células");
        int valor=valorOriginal.intValue();
        for (Cedula cedula : detalhesCaixaEletronico.getCedulas()) {
            int quantidade = valor / cedula.getValor();

            if (quantidade > 0 && cedula.getQuantidade() >= quantidade) {
                int restante = Math.subtractExact(cedula.getQuantidade(), quantidade);
                cedula.setQuantidade(restante);
                cedulasRecidas.add(new Cedula(cedula.getValor(), quantidade));

            }

            logger.info("Cédula: R$ " + cedula.getValor() + " Quantidade Restante Caixa: " + quantidade);
            valor = valor % cedula.getValor();
        }
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
