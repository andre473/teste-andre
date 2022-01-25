package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    public String findAll(){
        return "findAll";
    }

    @Service
    @RequiredArgsConstructor
    public class CadastroService {

        @Autowired
        private ClienteRepository clienteRepository;

        public Cliente salvarCliente(Cliente cliente) {
            return clienteRepository.save(cliente);
        }
    }



}

