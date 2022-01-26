package com.example.apicaixaeletronico.repositories;

import com.example.apicaixaeletronico.models.CaixaEletronico;
import com.example.apicaixaeletronico.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaEletronicoRepository extends JpaRepository<CaixaEletronico, Long> {


}
