package com.grupoatrium.cliente;

import java.util.Scanner;

import com.grupoatrium.negocio.GestionLibreria;
import com.grupoatrium.negociografico.EntornoGrafico;

/*****************************************************************************************
 * Nombre de Clase : SeleccionModo														 *
 * Autor: Miguel Rosa																	 *
 * Fecha: 07 - Noviembre - 2016															 *
 * Versi�n: 1.0																			 *
 * Descripci�n: Se escriben unas breves instrucciones por consola para que el			 *
 * 				usuario seleccione 1) modo consola o 2) modo gr�fico. La				 *
 * 				aplicaci�n permanece a la espera hasta que el usuario seleccione		 *
 * 				una u otra, o la instrucci�n para salir del programa. Si la opci�n		 *
 * 				introducida por el usuario no corresponde a ninguna de las				 *
 * 				anteriores se repetir� el mismo mensaje de selecci�n hasta que la		 *
 * 				opci�n introducida coincida.											 *
 * 				En funci�n de la selecci�n se lanzar� el entorno gr�fico, se			 *
 * 				permanecer� en modo consola o se saldr� de la aplicaci�n.				 *
 *****************************************************************************************/

public class SeleccionModo {
	
	/*************************************************************************************
	 * ATRIBUTOS																		 *
	 *************************************************************************************/
	
	private static boolean modoGrafico;
	
	// Atributo que almacenar� la selecci�n del usuario.
	private String seleccion; 	
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 *
	 *************************************************************************************/
	
	public SeleccionModo() {
		
		// Impresi�n en consola de unas breves instrucciones.
		System.out.println("Seleccione el modo en el que desea ejecutar la aplicaci�n:\n");
		System.out.println("\t1 Modo CONSOLA (por defecto)");
		System.out.println("\t2 Modo GR�FICO");
		System.out.println();
		System.out.println("\t3 SALIR");
		System.out.println();
		System.out.println("Puede seleccionar el n�mero o escribir la palabra clave.");
		
		// Se crea una instancia de la Scanner para recoger la opci�n del usuario
		Scanner sc = new Scanner(System.in);
		
		// Se asigna la opci�n del usuario al atributo "seleccion". El String devuelto
		// por el usuario se convierte a min�sculas antes de almacenarlo para que la
		// comprobaci�n sea independiente de las may�sculas o min�sculas.
		seleccion = sc.nextLine().toLowerCase();
		
		// Se invoca al m�todo comprobar().
		try {
			comprobar();
		} catch (OpcionIncorrectaException e) {
			new SeleccionModo();
		}
			
	}
	
	/*************************************************************************************
	 * METODOS																			 *
	 *************************************************************************************/
	
	/* Nombre: comprobar()
	 * Argumentos: Ninguno
	 * Devuelve: Nada
	 * Descripci�n: 
	 * Se compara el valor del atributo "seleccion" introducido por el usuario con 
	 * los siguientes valores:
	 * 		� "1", "consola" � ""  ->  Se contin�a en modo consola.
	 * 		� "2", "gr�fico" � "grafico" -> Se lanza el modo gr�fico.
	 * 		� "3" � "salir" -> Se sale de la aplicaci�n.
	 * 		� En caso de que el valor introducido no coincida con alguno de los
	 * 		anteriores se vuelve a imprimir el menu de inicio (se lanza nuevamente
	 * 		la aplicaci�n desde el principio).
	 */
	
	public void comprobar() throws OpcionIncorrectaException {
		
		// Opci�n 1. Modo Consola
		
		if (seleccion.equals("1") || (seleccion.equals("consola")) || (seleccion.equals(""))) {
			modoGrafico = false;
			System.out.println();
			System.out.println("SE CONTINUAR� EN MODO CONSOLA. Pulse INTRO para continuar.");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			new GestionLibreria();
		}
		
		// Opci�n 2. Modo Gr�fico
		
		else if (seleccion.equals("2") || (seleccion.equals("grafico")) || (seleccion.equals("gr�fico"))) {
			modoGrafico = true;
			System.out.println();
			System.out.println("SE CONTINUAR� EN MODO GR�FICO. Pulse INTRO para continuar.");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			new EntornoGrafico();
		}
		
		// Opci�n 3. Salir
		
		else if (seleccion.equals("3") || (seleccion.equals("salir"))) {
			System.out.println();
			System.out.println("SALIENDO DE LA APLICACI�N...");
			System.exit(0);
		}
		
		// Se lanza la excepci�n OpcionIncorrecta cuando la opci�n introducida por el
		// usuario no corresponde con ninguna de las anteriores
		
		else throw new OpcionIncorrectaException();
	}
	
	/* Nombre: getModoGrafico()
	 * Argumentos: Ninguno
	 * Devuelve: boolean
	 * Descripci�n: 
	 * Devuelve el valor de atributo modoGrafico
	 */
	
	public static boolean getModoGrafico() {
		return modoGrafico;
	}
	
}
