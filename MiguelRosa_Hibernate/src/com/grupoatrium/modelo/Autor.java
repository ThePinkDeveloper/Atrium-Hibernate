package com.grupoatrium.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Autor implements Serializable{
	
	/*************************************************************************************
	 * ATRIBUTOS																		 
	 *************************************************************************************/
	
	private Integer IDAutor;
	
	private String nombre;
	
	private String nacionalidad;
	
	private String comentarios;
	
	private Set<Libro> libros = new HashSet<Libro>();
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 
	 *************************************************************************************/
	
	public Autor() {}
	
	public Autor(String nombre, String nacionalidad, String comentarios) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.comentarios = comentarios;
	}

	
	/*************************************************************************************
	 * METODOS																			 
	 *************************************************************************************/
	
	// Método de sincronización de la colección libros
	
	public void addLibro(Libro l) {
		libros.add(l);
		l.getAutores().add(this);
	}
	
	/*************************************************************************************
	 * GETTERS Y SETTERS																 
	 *************************************************************************************/
	
	public Integer getIDAutor() {
		return IDAutor;
	}

	public void setIDAutor(int IDAutor) {
		this.IDAutor = IDAutor;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
	


	/*************************************************************************************
	 * TOSTRING															 
	 *************************************************************************************/

	@Override
	public String toString() {
		return "Autor [ID=" + IDAutor + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", comentarios="
				+ comentarios + ", libros=" + libros + "]";
	}



	
	
}
