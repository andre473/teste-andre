package com.example.apicaixaeletronico.repositories;

import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cedula;
import com.example.apicaixaeletronico.service.CedulaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CedulaRepository extends JpaRepository<Cedula,Long> {


}
