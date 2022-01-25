package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String findAll() {
        return "findAll";
    }

    public Cliente getClienteByCPF(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}





