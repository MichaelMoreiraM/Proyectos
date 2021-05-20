package com.example.api30521.Rest;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.api30521.Controller.EstadoDAO;
import com.example.api30521.Controller.PersonaDAO;
import com.example.api30521.Controller.TareaDAO;
import com.example.api30521.Models.Estado;
import com.example.api30521.Models.Personas;
import com.example.api30521.Models.Tarea;
import com.example.api30521.Service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;



@RestController
@RequestMapping("home")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaRest {
    
@Autowired
private PersonaDAO personaDAO;

@Autowired
private EstadoDAO estadoDAO;

@Autowired
private TareaDAO tareaDAO;

@Autowired
private PersonaService personaService;

@PostMapping("/save")
public ResponseEntity<String> guardar(@RequestBody Personas persona){
    if(StringUtils.isBlank(persona.getNombre()))
    return new ResponseEntity<String>("el nombre es obligatorio", HttpStatus.BAD_REQUEST);
    if(persona.getEdad()<=0 || persona.getEdad()>150 )
    return new ResponseEntity<String>("Rango edad no permitido", HttpStatus.BAD_REQUEST);    
    Pattern pattern = Pattern
    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    Matcher mather = pattern.matcher(persona.getEmail());
    if (!mather.find())
    return new ResponseEntity<String>("Email No Valido", HttpStatus.BAD_REQUEST); 
   // System.out.print(personaDAO.getPersonaByEmail(persona.getEmail()));
    int emailG=personaDAO.getPersonaByEmail(persona.getEmail());
    if(emailG>0){
    return new ResponseEntity<String>("Email Ya Registrado", HttpStatus.BAD_REQUEST);}
    int longi;
    String cadena="0"+persona.getTelefono();
    longi=cadena.length();
    //System.out.print("Rango de Numero"+longi);
    if(longi>10 || longi<10){
        return new ResponseEntity<String>("Telefono Invalido", HttpStatus.BAD_REQUEST);}

    persona.setTelefono(cadena);
    personaDAO.save(persona);
    return null;

}


@PostMapping("/saveTarea")
public ResponseEntity<String> guardarTarea(@RequestBody Tarea tarea){
    int valor=tarea.getPersonas().getId();
    if(valor==0){ return new ResponseEntity<String>("No hay usuario diponible o no seleccionado", HttpStatus.BAD_REQUEST);}
    tareaDAO.save(tarea);
    return null;
}

@GetMapping("/listar")
public List<Personas> listar(){
    return personaDAO.findAll();
}

@GetMapping("/listarEstado")
public List<Estado> listarE(){
    return estadoDAO.findAll();
}


@GetMapping("/listarTarea")
public List<Tarea> listarT(){
    return tareaDAO.findAll();
}

@DeleteMapping("/eliminar/{id}")
public void eliminar(@PathVariable("id")Integer id){
    personaDAO.deleteById(id);
}

@GetMapping("/detail/{id}")
public ResponseEntity<Personas> getById(@PathVariable("id") int id){
    Personas producto = personaService.getOne(id).get();
    return new ResponseEntity<Personas> (producto, HttpStatus.OK);
}

@PutMapping("/actualizar/{id}")
public ResponseEntity<String> actualizar(@PathVariable("id")int id,@RequestBody Personas persona){
    Personas personas =  personaService.getOne(id).get();
    if(StringUtils.isBlank(persona.getNombre()))
    return new ResponseEntity<String>("el nombre es obligatorio", HttpStatus.BAD_REQUEST);
    if(StringUtils.isBlank(persona.getNombre()))
    return new ResponseEntity<String>("el nombre es obligatorio", HttpStatus.BAD_REQUEST);
    if(persona.getEdad()<=0 || persona.getEdad()>150 )
    return new ResponseEntity<String>("Rango edad no permitido", HttpStatus.BAD_REQUEST); 
    Pattern pattern = Pattern
    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    Matcher mather = pattern.matcher(persona.getEmail());
    if (!mather.find())
    return new ResponseEntity<String>("Email No Valido", HttpStatus.BAD_REQUEST); 
    //System.out.print(personaDAO.getPersonaByEmail(persona.getEmail()));
    int emailG=personaDAO.getPersonaByEmail(persona.getEmail());
    if(emailG>1){
    return new ResponseEntity<String>("Email Ya Registrado", HttpStatus.BAD_REQUEST);}
    int longi;
    
    String cadena="0"+persona.getTelefono();
    System.out.print("numer: "+cadena);
    longi=cadena.length();
    System.out.print("Rango de Numero"+longi);
    if(longi>10 || longi<10){
        return new ResponseEntity<String>("Telefono Invalido", HttpStatus.BAD_REQUEST);}
    

    personas.setNombre(persona.getNombre());
    personas.setApellido(persona.getApellido());
    personas.setEdad(persona.getEdad());
    personas.setTelefono(cadena);
    personas.setEmail(persona.getEmail());
    //persona.setEstado(persona.getEstado());
    personaDAO.save(personas);
    return null;
}

//TAREA

@PutMapping("/actualizarT/{id}")
public ResponseEntity<String> actualizarT(@PathVariable("id")int id,@RequestBody Tarea tarea){
    Tarea tareas =  personaService.getOneT(id).get();
    tareas.setDescripcion(tarea.getDescripcion());
    tareas.setFecha(tarea.getFecha());
    tareaDAO.save(tareas);
    return null;
}

@GetMapping("/detaT/{id}")
public ResponseEntity<Tarea> getByIdT(@PathVariable("id") int id){
    Tarea producto = personaService.getOneT(id).get();
    return new ResponseEntity<Tarea> (producto, HttpStatus.OK);
}

@DeleteMapping("/eliminarT/{id}")
public void eliminarT(@PathVariable("id")Integer id){
    tareaDAO.deleteById(id);
}



@GetMapping("/listarT/{prmT}")
public List<Tarea>mostrarTa(@PathVariable("prmT") int prmT){
    return tareaDAO.getTareabyIdP(prmT);
    
}

}
