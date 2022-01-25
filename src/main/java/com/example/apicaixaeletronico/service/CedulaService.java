package com.example.apicaixaeletronico.service;

import com.example.apicaixaeletronico.models.Cedula;
import com.example.apicaixaeletronico.repositories.CedulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CedulaService {

    @Autowired
    private CedulaRepository repository;

    public Cedula save(Cedula cedula) {

        return repository.save(cedula);
    }
}
