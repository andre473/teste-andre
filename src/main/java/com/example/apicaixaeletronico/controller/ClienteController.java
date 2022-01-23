package com.example.apicaixaeletronico.controller;


import com.example.apicaixaeletronico.models.Cliente;
import com.example.apicaixaeletronico.service.CadastroService;
import com.example.apicaixaeletronico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public String listar() {


        return clienteService.findAll();
    }

    @PostMapping( produces = "application/json")
    public ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente){

        Cliente clienteSalvo = cadastroService.salvarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }
}

