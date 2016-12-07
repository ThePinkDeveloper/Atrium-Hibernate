package com.grupoatrium.modelo;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.grupoatrium.cliente.SeleccionModo;

/*****************************************************************************************
 * Nombre de Clase : LibroNoEncontradoException											 *
 * Autor: Miguel Rosa																	 *
 * Fecha: 11 - Noviembre - 2016															 *
 * Versi�n: 1.0																			 *
 * Descripci�n: 																		 *
 * Esta Excepci�n se lanza cuando alguno de estos m�todos:								 *
 *		eliminarLibro()																	 *
 *		consultarISBN()																	 *
 *		consultarTitulo()																 *
 *		modificarPrecio()																 *
 * declarados en la interfaz ItfzLibrosDao y definidos en la Clase LibrosDAO es invocado *
 * y no se encuentra ning�n registro en la base de datos que coincida con los par�metros *
 * aportados por el usuario y, en consecuencia, se informa al mismo de que no se ha		 *
 * encontrado ning�n registro con los datos aportados.									 * 														 	*
 *****************************************************************************************/

public class LibroNoEncontradoException extends Exception {
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 *
	 *************************************************************************************/
	
	public LibroNoEncontradoException(String e) {
		mensajeConsola(e);
	}
	
	/*************************************************************************************
	 * METODOS																	 *
	 *************************************************************************************/
	public void mensajeConsola(String contenido) {
		// Se muestra un mensaje por consola indicando que no hay ning�n libro con el
		// dato proporcionado.
		System.out.println();
		System.out.println("NO SE ENCUENTRA UN LIBRO CON ESE " + contenido + " EN NUESTRA BASE DE DATOS. Pulse INTRO para continuar.");
		System.out.println();
		
		// Se espera a que el usuario pulse INTRO para continuar
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	public void mensajeAlert(String contenido) {
		// Se muestra un Alert indicando que no hay ning�n libro con el
		// dato proporcionado.
		JOptionPane.showMessageDialog(null, "NO SE ENCUENTRA UN LIBRO CON ESE " + contenido + " EN NUESTRA BASE DE DATOS.");
	}

}
