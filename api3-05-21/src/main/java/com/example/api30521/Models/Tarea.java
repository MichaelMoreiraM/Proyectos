package com.example.api30521.Models;







import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarea {
    

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private Date fecha;


    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_persona")
    private Personas personas;


    public Tarea() {
    }

    public Tarea(String descripcion, Date fecha, Personas personas) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.personas = personas;
    }
   

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return Date return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    /**
     * @return Personas return the personas
     */
    public Personas getPersonas() {
        return personas;
    }

    /**
     * @param personas the personas to set
     */
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

}
