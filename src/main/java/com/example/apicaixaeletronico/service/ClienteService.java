package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
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

        Cliente byCpf = clienteRepository.findByCpf(cpf);
        System.out.println(byCpf);
        return byCpf;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}





