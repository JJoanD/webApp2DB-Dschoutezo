package com.example.webapp2DB_Dschoutezo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webapp2DB_Dschoutezo.model.Persona;

public interface PersonaRepository extends JpaRepository< Persona , Long > {

    
    List<Persona> findByNome(String name);
    List<Persona> findByEta(int eta);
    List<Persona> findByNomeAndEta(String nome, int eta);

    List<Persona> findByNomeContains(String namePart);

}
