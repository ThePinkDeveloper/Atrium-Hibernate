package com.grupoatrium.cliente;

import java.util.Scanner;

/*****************************************************************************************
 * Nombre de Clase : OpcionIncorrectaException											 *
 * Autor: Miguel Rosa																	 *
 * Fecha: 07 - Noviembre - 2016															 *
 * Versi�n: 1.0																			 *
 * Descripci�n: Esta clase se lanza cuando la opci�n introducida por el usuario			 *
 * 				no se parece a ninguna de las descritas y se lanza un mensaje de		 *
 * 				error.																	 *
 * 				Cualquier dato introducido por el usuario en la consola antes de		 *
 * 				pulsar INTRO es ignorado. 												 *
 *****************************************************************************************/

public class OpcionIncorrectaException extends Exception {
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 *
	 *************************************************************************************/
	
	public OpcionIncorrectaException() {
		System.out.println();
		System.out.println("OPCI�N NO RECONOCIDA. Pulse INTRO para continuar.");
		
		// Se espera a que el usuario pulse INTRO para continuar
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

}
