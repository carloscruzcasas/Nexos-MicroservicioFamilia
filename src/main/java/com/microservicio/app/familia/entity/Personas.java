package com.microservicio.app.familia.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Document(collection = "Personas")
public class Personas implements Serializable{

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private Long id;

	private String nombre;
	private String correo;
	private String telefono;
	private Familiares familiares;
	
	public Personas() {
		
	}
	
	public Personas(Long id, String nombre, String correo, String telefono, Familiares familiares) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.familiares = familiares;
		
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public Familiares getFamiliares() {
		return familiares;
	}

	public void setFamiliares(Familiares familiares) {
		this.familiares = familiares;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "]";
	}

	
}
