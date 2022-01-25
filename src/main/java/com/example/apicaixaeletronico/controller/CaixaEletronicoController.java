package com.example.apicaixaeletronico.controller;

import com.example.apicaixaeletronico.service.CaixaEletronicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caixa-eletronico")
public class CaixaEletronicoController {

    @Autowired
    private CaixaEletronicoService service;

    @GetMapping("/iniciar")
    public String iniciar() {

        service.iniciaCaixa();
        return "OK";
    }
}
