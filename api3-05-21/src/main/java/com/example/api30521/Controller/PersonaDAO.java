package com.example.api30521.Controller;



import com.example.api30521.Models.Personas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaDAO extends JpaRepository<Personas,Integer>{
    

    @Query(value="SELECT count(*) FROM personas WHERE email=:emailP", nativeQuery=true)
    int getPersonaByEmail(String emailP);

}
