package com.example.api30521.Controller;

import java.util.List;

import com.example.api30521.Models.Tarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TareaDAO extends JpaRepository<Tarea,Integer> {
    
    @Query(value="SELECT * FROM tarea where id_persona=:prmT", nativeQuery=true)
    List<Tarea> getTareabyIdP(int prmT);
 
}
