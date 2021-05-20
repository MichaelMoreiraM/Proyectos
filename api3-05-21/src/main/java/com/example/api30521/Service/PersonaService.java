package com.example.api30521.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.api30521.Controller.PersonaDAO;
import com.example.api30521.Controller.TareaDAO;
import com.example.api30521.Models.Personas;
import com.example.api30521.Models.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    
    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    private TareaDAO tareaDAO;

    public Optional<Personas> getOne(int id){
        return personaDAO.findById(id);
    }

    public Optional<Tarea> getOneT(int id){
        return tareaDAO.findById(id);
    }
    public boolean existsByIdT(int id){
        return tareaDAO.existsById(id);
    }

    public boolean existsById(int id){
        return personaDAO.existsById(id);
    }
     

    
}
