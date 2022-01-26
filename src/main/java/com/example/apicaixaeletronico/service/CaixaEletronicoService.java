package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.dto.CedulaDTO;
import com.example.apicaixaeletronico.dto.DepositoDTO;
import com.example.apicaixaeletronico.dto.SaqueDTO;
import com.example.apicaixaeletronico.exception.CaixaEletronicoExcepetion;
import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.repositories.CaixaEletronicoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class CaixaEletronicoService {

    private static final Logger logger = Logger.getLogger(CaixaEletronicoService.class.getName());

    @Autowired
    private SaqueService saqueService;

    @Autowired
    private CaixaEletronicoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CedulaService cedulaService;

    private List<Cedula> cedulas;

    CaixaEletronicoService() {
        this.cedulas = depositoEmCaixa(5);
    }

    private CaixaEletronico getCaixaEletronicoById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public List<Cedula> depositoEmCaixa(Integer quantidade) {
        List<Cedula> cedulas = new ArrayList<>();
        if (Objects.nonNull(quantidade) && quantidade > 0) {
         /* cedulas.add(new Cedula(10, quantidade));
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

    public void iniciaCaixa() {

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        caixaEletronico.setCedulas(new ArrayList<Cedula>());
        caixaEletronico.getCedulas().add(cedulaService.save(new Cedula(10, 5)));
        caixaEletronico.getCedulas().add(cedulaService.save(new Cedula(20, 5)));
        caixaEletronico.getCedulas().add(cedulaService.save(new Cedula(50, 5)));
        caixaEletronico.getCedulas().add(cedulaService.save(new Cedula(100, 5)));

        caixaEletronico.calculaTotal();

        repository.save(caixaEletronico);
    }

    public String depositar(DepositoDTO dto) {

        Cliente clienteByCPF = clienteService.getClienteByCPF(dto.getCliente().getCpf());

        return "ok";
    }

    public String sacar(SaqueDTO dto) {

        Cliente clienteByCPF = clienteService.getClienteByCPF(dto.getCliente().getCpf());
        CaixaEletronico caixaEletronicoById = getCaixaEletronicoById(dto.getCaixaEletronico().getId());
        List<CedulaDTO> saque = saqueService.sacar(dto.getValor(), clienteByCPF, caixaEletronicoById);
        return new Gson().toJson(saque);
    }
}

