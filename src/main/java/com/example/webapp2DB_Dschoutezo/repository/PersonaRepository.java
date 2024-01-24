package com.example.webapp2DB_Dschoutezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webapp2DB_Dschoutezo.model.Persona;

public interface PersonaRepository extends JpaRepository< Persona , Long > {

    
}
