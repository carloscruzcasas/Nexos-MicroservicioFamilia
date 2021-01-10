package com.microservicio.app.familia.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class Familiares implements Serializable{


	private String nombre;
	private String correo;
	private String parentezco;
	
	public Familiares() {
		
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

	public String getParentezco() {
		return parentezco;
	}

	public void setParentezco(String parentezco) {
		this.parentezco = parentezco;
	}

	@Override
	public String toString() {
		return "Familiares [nombre=" + nombre + ", correo=" + correo + ", parentezco=" + parentezco + "]";
	}
	
}
