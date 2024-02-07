package com.example.webapp2DB_Dschoutezo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp2DB_Dschoutezo.model.Persona;
import com.example.webapp2DB_Dschoutezo.repository.PersonaRepository;

@RestController
@RequestMapping("/persona")
public class myController {

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hello")
    public String helloParam(@RequestParam(value = "name" , defaultValue = "World")String name){
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name){
        return String.format("Hello , %s!", name);
    }
    
    @PostMapping()
    public Persona persona (@RequestBody Persona p){
        return personaRepository.save(p);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable long id , @RequestBody Persona personaUpdate){
        Persona personaToUpdate = personaRepository.getReferenceById(id);

        personaToUpdate.setNome(personaUpdate.getNome());
        personaToUpdate.setCognome(personaUpdate.getCognome());
        personaToUpdate.setEta(personaUpdate.getEta());

        personaRepository.save(personaToUpdate);

        return ResponseEntity.ok("Utente aggiornato");
    }


    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam(name = "id") long id){
        personaRepository.deleteById(id);
        return ResponseEntity.ok("Utente cancellato");
    }

    //chiedere al prof se ci sono differenze tra getReferenceById e getById

    @GetMapping("/list")
    public List<Persona> list(@RequestParam(name = "nome", required = false) String name, @RequestParam(name = "eta", required = false) Integer eta){

        if (name == null && eta == null) {

            return personaRepository.findAll();
        } else if (name != null && eta == null) 
        {
            return personaRepository.findByNome(name);
        } else if (eta != null && name == null) {
            return personaRepository.findByEta(eta);
        } else {
            return personaRepository.findByNomeAndEta(name, eta);
        }
    }
    
    @GetMapping("/search")
    public List<Persona> trova(@RequestParam(name = "nome") String name){
       return personaRepository.findByNomeContains(name);
    }

}

    